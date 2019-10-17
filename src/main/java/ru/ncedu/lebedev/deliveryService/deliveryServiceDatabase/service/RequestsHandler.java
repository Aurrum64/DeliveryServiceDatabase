package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRequestsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequestsHandler {

    private UsersRepository usersRepository;
    private UsersRequestsRepository usersRequestsRepository;
    private CouriersRepository couriersRepository;

    @Autowired
    public RequestsHandler(UsersRepository usersRepository,
                           UsersRequestsRepository usersRequestsRepository,
                           CouriersRepository couriersRepository) {
        this.usersRepository = usersRepository;
        this.usersRequestsRepository = usersRequestsRepository;
        this.couriersRepository = couriersRepository;
    }

    public void handleAndSetUserRoles(UsersEntity user, String professionChoice) {

        Set<String> roles = Arrays.stream(RolesEntity.values())
                .map(RolesEntity::name)
                .collect(Collectors.toSet());

        Map<String, String> newRoles = new HashMap<>();

        if (user.getRoles().contains(RolesEntity.ADMIN)) {
            newRoles.put("ADMIN", "");
        } else if (user.getRoles().contains(RolesEntity.USER)) {
            newRoles.put("USER", "");
        } else if (user.getRoles().contains(RolesEntity.COURIER)) {
            newRoles.put("COURIER", "");
        } else if (user.getRoles().contains(RolesEntity.MANAGER)) {
            newRoles.put("MANAGER", "");
        }
        if (professionChoice.equals("courier") &&
                !user.getRoles().contains(RolesEntity.COURIER)) {
            newRoles.put("COURIER", "");
        } else if (professionChoice.equals("manager") &&
                !user.getRoles().contains(RolesEntity.MANAGER)) {
            newRoles.put("MANAGER", "");
        }
        for (String key : newRoles.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(RolesEntity.valueOf(key));
            }
        }
        usersRepository.save(user);
    }

    public void setRequestStatus(String status, UsersRequestsEntity userRequest) {

        userRequest.getRequestStatuses().clear();

        Set<String> requestStatuses = Arrays.stream(RequestsStatutesEntity.values())
                .map(RequestsStatutesEntity::name)
                .collect(Collectors.toSet());

        Map<String, String> newRequestStatus = new HashMap<>();
        newRequestStatus.put(status, "");

        for (String key : newRequestStatus.keySet()) {
            if (requestStatuses.contains(key)) {
                userRequest.getRequestStatuses().add(RequestsStatutesEntity.valueOf(key));
            }
        }
        usersRequestsRepository.save(userRequest);
    }

    public void createCourierBasedOnUser(UsersEntity whoApprovedRequest, UsersEntity user, String professionChoice) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date currentDate = format.parse(currentDateString);

        if (professionChoice.equals("courier")) {
            CouriersEntity courier = new CouriersEntity();
            courier.setFirstName(user.getUsername());
            courier.setLastName("");
            courier.setEmail(user.getEmail());
            courier.setHireDate(currentDate);
            courier.setAuthor(whoApprovedRequest);
            courier.setReadiness(false);
            courier.setFired(false);
            courier.setPhoneNumber("");
            courier.setPremium(0);
            courier.setSalary(19_351);
            courier.setDepartmentId(1);
            courier.setRating(10);
            courier.setLatitude(RandomCoordinates.getRandomLatitude());
            courier.setLongitude(RandomCoordinates.getRandomLongitude());
            couriersRepository.save(courier);

            user.setCourier(courier);
            usersRepository.save(user);
        }
    }
}
