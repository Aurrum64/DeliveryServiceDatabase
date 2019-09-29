package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendUsersToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.UserService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RolesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Map<String, Object> userList) {
        userList.put("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable UsersEntity user,
                               Map<String, Object> model) {
        model.put("user", user);
        model.put("roles", RolesEntity.values());
        return "userEditor";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String saveUser(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") UsersEntity user) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping(value = "/usersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendUsersList() {

        SendUsersToAjax usersList = new SendUsersToAjax();
        Iterable<UsersEntity> users = userService.findAll();
        if (!users.iterator().hasNext()) {
            usersList.setMessage("Users list is empty!");
        } else {
            usersList.setMessage("success");
        }
        usersList.setResult(users);
        return ResponseEntity.ok(usersList);
    }
}
