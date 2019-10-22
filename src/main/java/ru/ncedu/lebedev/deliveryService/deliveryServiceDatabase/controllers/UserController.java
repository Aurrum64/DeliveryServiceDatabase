package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.exception.ResourceNotFoundException;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.security.CurrentUser;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.security.UserPrincipal;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

@RestController
public class UserController {
    @Autowired
    private UsersRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UsersEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
