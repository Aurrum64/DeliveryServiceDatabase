package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.UserService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RolesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ProfileController {

    private CouriersRepository couriersRepository;
    private UserService userService;

    @Autowired
    public ProfileController(UserService userService, CouriersRepository couriersRepository) {
        this.userService = userService;
        this.couriersRepository = couriersRepository;
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UsersEntity user,
                          Map<String, Object> model) {
        if (user.getRoles().contains(RolesEntity.COURIER)) {
            List<CouriersEntity> couriers = couriersRepository.findByFirstName(user.getUsername());
            CouriersEntity courier = couriers.get(0);
            model.put("courier", courier);
        }
        model.put("user", user);
        model.put("avatar", user.getFilename());
        model.put("username", " " + user.getUsername());
        model.put("email", user.getEmail());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal UsersEntity user,
                                @RequestParam String password,
                                @RequestParam String email,
                                @RequestParam("file") MultipartFile avatar) throws IOException {
        userService.updateProfile(user, password, email, avatar);
        return "redirect:/profile";
    }
}
