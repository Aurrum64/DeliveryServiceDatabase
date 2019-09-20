package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RolesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.Collections;
import java.util.UUID;

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
        Iterable<UsersEntity> usersList = usersRepository.findAll();
        if (usersList.iterator().hasNext()) {
            user.setRoles(Collections.singleton(RolesEntity.USER));
        } else {
            user.setRoles(Collections.singleton(RolesEntity.ADMIN));
        }
        user.setActivationCode(UUID.randomUUID().toString());
        usersRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to delivery service! Please, visit next link: " +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    public boolean activateUser(String code) {

        UsersEntity user = usersRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        usersRepository.save(user);

        return true;
    }
}
