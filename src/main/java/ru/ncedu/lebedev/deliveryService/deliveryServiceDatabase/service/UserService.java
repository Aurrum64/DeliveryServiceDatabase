package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RolesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UsersRepository usersRepository;
    private MailSender mailSender;

    @Autowired
    public UserService(UsersRepository usersRepository,
                       MailSender mailSender) {
        this.usersRepository = usersRepository;
        this.mailSender = mailSender;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username);
    }

    public boolean addUser(UsersEntity user) {
        UsersEntity userFromDb = usersRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setVerified(true);
        user.setWasFired(false);
        Iterable<UsersEntity> usersList = usersRepository.findAll();
        if (usersList.iterator().hasNext()) {
            user.setRoles(Collections.singleton(RolesEntity.USER));
        } else {
            user.setRoles(Collections.singleton(RolesEntity.ADMIN));
        }
        user.setEmailVerification("Не подтверждена");
        user.setActivationCode(UUID.randomUUID().toString());
        usersRepository.save(user);

        /*sendEmailForActivation(user);*/
        return true;
    }

    private void sendEmailForActivation(UsersEntity user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Привет, %s!\n" +
                            "Добро пожаловать в нашу службу доставки Delivery Service!\n" +
                            "Пожалуйста, перейдите по следующей ссылке:\n" +
                            "http://localhost:8080/activate/%s,\n" +
                            "чтобы активировать вашу учётную запись!",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Активация учётной записи Delivery Service!", message);
        }
    }

    public void sendHiredEmail(UsersEntity user, String professionChoice) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String job;
            if (professionChoice.equals("courier")) {
                job = "курьера";
            } else {
                job = "менеджера";
            }
            String message = String.format(
                    "Привет, %s!\n" +
                            "Наши поздравления! Мы принимаем вас на должность %s в нашу службу доставки.\n" +
                            "Мы добавили вас в нашу базу курьеров, теперь вы можете приступать к работе\n" +
                            "в любое удобное для вас время.\n" +
                            "Для этого подтвердите готовность работать в своём профиле:\n" +
                            "http://localhost:8080/profile.",
                    user.getUsername(),
                    job
            );
            mailSender.send(user.getEmail(), "О принятии на работу в Delivery Service!", message);
        }
    }

    public void sendRejectedEmail(UsersEntity user, String professionChoice) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String job;
            if (professionChoice.equals("courier")) {
                job = "курьера";
            } else {
                job = "менеджера";
            }
            String message = String.format(
                    "Привет, %s :(\n" +
                            "Мы вынуждены отказать вам в принятии на должность %s.\n" +
                            "Если вы считаете, что мы отказали вам неправомерно,\n" +
                            "вы можете оставить еще одну заявку в своем профиле:\n" +
                            "http://localhost:8080/profile для повторного рассмотрения.",
                    user.getUsername(),
                    job
            );
            mailSender.send(user.getEmail(), "О принятии на работу в Delivery Service :(", message);
        }
    }

    public boolean activateUser(String code) {

        UsersEntity user = usersRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setVerified(true);
        user.setEmailVerification("Подтверждена");
        user.setActivationCode(null);
        usersRepository.save(user);
        return true;
    }

    public List<UsersEntity> findAll() {
        return usersRepository.findAll();
    }

    public void saveUser(UsersEntity user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(RolesEntity.values())
                .map(RolesEntity::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(RolesEntity.valueOf(key));
            }
        }
        usersRepository.save(user);
    }

    public void updateProfile(UsersEntity user, String password,
                              String email, MultipartFile avatar) throws IOException {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        if (avatar != null && !avatar.getOriginalFilename().isEmpty()) {
            File uploadDirectory = new File(uploadPath);

            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + avatar.getOriginalFilename();

            avatar.transferTo(new File(uploadPath + "/" + resultFilename));
            user.setFilename(resultFilename);
        }
        usersRepository.save(user);

        if (isEmailChanged) {
            sendEmailForActivation(user);
        }
    }
}
