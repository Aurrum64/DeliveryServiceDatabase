package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.UserService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.Map;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UsersEntity user, Map<String, Object> model) {
        if (!userService.addUser(user)) {
            model.put("errorMessage", "Имя пользователя уже занято!");
            return "registration";
        } else {
            model.put("successMessage", "Вы успешно зарегистрировались!");
        }
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(Map<String, Object> model, @PathVariable String code) {

        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.put("message", "Почтовый адрес подтверждён!");
        } else {
            model.put("message", "Ссылка подтверждения почты не действительна!");
        }
        return "login";
    }
}
