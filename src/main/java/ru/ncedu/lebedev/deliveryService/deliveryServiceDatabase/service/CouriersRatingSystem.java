package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RolesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CouriersRatingSystem {

    private CouriersRepository couriersRepository;
    private UsersRepository usersRepository;

    @Autowired
    public CouriersRatingSystem(CouriersRepository couriersRepository,
                                UsersRepository usersRepository) {
        this.couriersRepository = couriersRepository;
        this.usersRepository = usersRepository;
    }

    public void setCourierRating(CouriersEntity courier, Integer rating) {

        Integer currentCourierRating = courier.getRating();
        Integer courierRatingAfterReview;

        if (rating == 1) {
            courierRatingAfterReview = currentCourierRating - 2;
        } else if (rating == 2) {
            courierRatingAfterReview = currentCourierRating - 1;
        } else if (rating == 3) {
            courierRatingAfterReview = currentCourierRating;
        } else if (rating == 4) {
            courierRatingAfterReview = currentCourierRating + 1;
        } else {
            courierRatingAfterReview = currentCourierRating + 2;
        }
        courier.setRating(courierRatingAfterReview);
        couriersRepository.save(courier);

        if (courier.getRating() <= 0) {
            /*mailSender.sendFiredEmail(courier);*/
            takeBackCourierAuthorities(courier);
        }
    }

    private void takeBackCourierAuthorities(CouriersEntity courier) {

        courier.setFired(true);

        UsersEntity user = usersRepository.findByUsername(courier.getFirstName());
        user.setWasFired(true);

        Set<String> roles = Arrays.stream(RolesEntity.values())
                .map(RolesEntity::name)
                .collect(Collectors.toSet());

        Map<String, String> newRoles = new HashMap<>();

        if (user.getRoles().contains(RolesEntity.ADMIN)) {
            newRoles.put("ADMIN", "");
        } else if (user.getRoles().contains(RolesEntity.USER)) {
            newRoles.put("USER", "");
        } else if (user.getRoles().contains(RolesEntity.MANAGER)) {
            newRoles.put("MANAGER", "");
        }
        user.getRoles().clear();

        for (String key : newRoles.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(RolesEntity.valueOf(key));
            }
        }
        usersRepository.save(user);
    }
}
