package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RolesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private UsersRepository usersRepository;

    @Autowired
    public RegistrationController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UsersEntity user, Map<String, Object> model) {
        UsersEntity userFromDb = usersRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("errorMessage", "User already exists!");
            return "registration";
        } else {
            model.put("successMessage", "You are successfully Sign Up!");
        }
        user.setActive(true);
        Iterable<UsersEntity> usersList = usersRepository.findAll();
        if (usersList.iterator().hasNext()) {
            user.setRoles(Collections.singleton(RolesEntity.USER));
        } else {
            user.setRoles(Collections.singleton(RolesEntity.ADMIN));
        }
        usersRepository.save(user);
        return "registration";
    }
}
