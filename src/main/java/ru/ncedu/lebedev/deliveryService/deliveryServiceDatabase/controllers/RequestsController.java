package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRequestsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersRequestsEntity;

import java.util.Map;

@Controller
public class RequestsController {

    private UsersRequestsRepository usersRequestsRepository;

    @Autowired
    public RequestsController(UsersRequestsRepository usersRequestsRepository) {
        this.usersRequestsRepository = usersRequestsRepository;
    }

    @GetMapping("/requests")
    public String usersRequestsList(Map<String, Object> model) {
        Iterable<UsersRequestsEntity> usersRequests = usersRequestsRepository.findAll();
        model.put("usersRequests", usersRequests);
        return "requests";
    }
}
