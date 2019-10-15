package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.ControllerAnswerToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.UsersRequestsMessage;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRequestsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.RequestsHandler;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.RequestsStatutesEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersRequestsEntity;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class NotificationsController {

    private OrderDetailsRepository orderDetailsRepository;
    private UsersRequestsRepository usersRequestsRepository;
    private UsersRepository usersRepository;
    private RequestsHandler requestsHandler;

    @Autowired
    public NotificationsController(OrderDetailsRepository orderDetailsRepository,
                                   UsersRequestsRepository usersRequestsRepository,
                                   UsersRepository usersRepository,
                                   RequestsHandler requestsHandler) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.usersRequestsRepository = usersRequestsRepository;
        this.usersRepository = usersRepository;
        this.requestsHandler = requestsHandler;
    }

    @GetMapping("/notifications")
    public String notifications(Map<String, Object> model) {

        final int RECENT_ORDERS_LIST_SIZE = 6;
        final int RECENT_REQUESTS_LIST_SIZE = 6;

        List<OrderDetailsEntity> orders = orderDetailsRepository.findAll();
        if (orderDetailsRepository.count() > RECENT_ORDERS_LIST_SIZE) {
            List<OrderDetailsEntity> recentOrders = orders.subList(orders.size() -
                    RECENT_ORDERS_LIST_SIZE, orders.size());
            model.put("recentOrders", recentOrders);
        } else {
            model.put("recentOrders", orders);
        }
        List<UsersRequestsEntity> usersRequests = usersRequestsRepository.findAll();
        if (usersRequestsRepository.count() > RECENT_REQUESTS_LIST_SIZE) {
            List<UsersRequestsEntity> recentUsersRequests = usersRequests.subList(usersRequests.size() -
                    RECENT_REQUESTS_LIST_SIZE, usersRequests.size());
            model.put("recentRequests", recentUsersRequests);
        } else {
            model.put("recentRequests", usersRequests);
        }
        return "notifications";
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
    public String approveRequest(@AuthenticationPrincipal UsersEntity whoApprovedRequest,
                                 @RequestParam Integer requestId,
                                 @RequestParam String professionChoice,
                                 @RequestParam String source,
                                 @RequestParam String authorName) throws ParseException {
        UsersEntity user = usersRepository.findByUsername(authorName);
        requestsHandler.handleAndSetUserRoles(user, professionChoice);

        UsersRequestsEntity userRequest = usersRequestsRepository.findByRequestId(requestId);
        requestsHandler.setRequestStatus("APPROVED", userRequest);

        requestsHandler.createCourierBasedOnUser(whoApprovedRequest, user, professionChoice);

        if (source.equals("notifications")) {
            return "redirect:/notifications";
        } else {
            return "redirect:/requests";
        }
    }

    @PostMapping(value = "/rejectRequest")
    public String rejectRequest(@RequestParam Integer requestId,
                                @RequestParam String professionChoice,
                                @RequestParam String source,
                                @RequestParam String authorName) {
        UsersRequestsEntity userRequest = usersRequestsRepository.findByRequestId(requestId);
        requestsHandler.setRequestStatus("REJECTED", userRequest);

        UsersEntity user = usersRepository.findByUsername(authorName);
        /*userService.sendRejectedEmail(user, professionChoice);*/

        if (source.equals("notifications")) {
            return "redirect:/notifications";
        } else {
            return "redirect:/requests";
        }
    }
}
