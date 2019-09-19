package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendOrderDetailsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendUsersToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RolesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
/*@PreAuthorize("hasAuthority('ADMIN')")*/
public class UsersController {

    private UsersRepository usersRepository;

    @Autowired
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public String userList(Map<String, Object> userList) {
        userList.put("users", usersRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable UsersEntity user,
                               Map<String, Object> model) {
        model.put("user", user);
        model.put("roles", RolesEntity.values());
        return "userEditor";
    }

    @PostMapping
    public String saveUser(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") UsersEntity user) {
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
        return "redirect:/user";
    }

    @GetMapping(value = "/usersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendUsersList() {

        SendUsersToAjax usersList = new SendUsersToAjax();
        Iterable<UsersEntity> users = usersRepository.findAll();
        if (!users.iterator().hasNext()) {
            usersList.setMessage("Users list is empty!");
        } else {
            usersList.setMessage("success");
        }
        usersList.setResult(users);
        return ResponseEntity.ok(usersList);
    }
}
