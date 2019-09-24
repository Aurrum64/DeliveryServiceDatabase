package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.ControllerAnswerToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.UsersRequestsMessage;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRequestsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RequestsStatutesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersRequestsEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class NotificationsController {

    private OrderDetailsRepository orderDetailsRepository;
    private UsersRequestsRepository usersRequestsRepository;

    @Autowired
    public NotificationsController(OrderDetailsRepository orderDetailsRepository,
                                   UsersRequestsRepository usersRequestsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.usersRequestsRepository = usersRequestsRepository;
    }

    @GetMapping("/notifications")
    public String notifications(Map<String, Object> model) {

        final int RECENT_ORDERS_LIST_SIZE = 3;
        final int RECENT_REQUESTS_LIST_SIZE = 3;

        Iterable<OrderDetailsEntity> orders = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> recentOrderDetails = new ArrayList<>();
        for (OrderDetailsEntity element : orders) {
            if (element.getOrderDetailsId() > orderDetailsRepository.count() - RECENT_ORDERS_LIST_SIZE) {
                recentOrderDetails.add(element);
            }
        }
        Iterable<UsersRequestsEntity> usersRequests = usersRequestsRepository.findAll();
        List<UsersRequestsEntity> recentUsersRequests = new ArrayList<>();
        for (UsersRequestsEntity element : usersRequests) {
            if (element.getRequestId() > orderDetailsRepository.count() - RECENT_REQUESTS_LIST_SIZE) {
                recentUsersRequests.add(element);
            }
        }
        model.put("recentOrders", recentOrderDetails);
        model.put("recentRequests", recentUsersRequests);
        return "notifications";
    }

    @GetMapping("/requests")
    public String usersRequestsList(Map<String, Object> model) {
        Iterable<UsersRequestsEntity> usersRequests = usersRequestsRepository.findAll();
        model.put("usersRequests", usersRequests);
        return "requests";
    }

    @PostMapping(value = "/addUsersRequests",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax addUsersRequests(@AuthenticationPrincipal UsersEntity user,
                                                   @RequestBody UsersRequestsMessage userMessage) {
        UsersRequestsEntity userRequest = new UsersRequestsEntity();
        if (userMessage.getUserChoice().equals("courierRequest")) {
            userRequest.setCourierRequest(true);
            userRequest.setManagerRequest(false);
        }
        if (userMessage.getUserChoice().equals("managerRequest")) {
            userRequest.setCourierRequest(false);
            userRequest.setManagerRequest(true);
        }
        userRequest.setRequestStatuses(Collections.singleton(RequestsStatutesEntity.CONSIDERATION));
        userRequest.setAuthor(user);
        usersRequestsRepository.save(userRequest);
        return new ControllerAnswerToAjax("OK", "");
    }

    @PostMapping(value = "/approveRequest")
    public String addUsersRequests(@RequestParam String requestId,
                                   @RequestParam String authorName) {
        System.out.println(requestId + " " + authorName);
        return "redirect:/notifications";
    }
}
