package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.ControllerAnswerToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.UsersRequestsMessage;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.UsersRequestsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.UserService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class NotificationsController {

    private OrderDetailsRepository orderDetailsRepository;
    private UsersRequestsRepository usersRequestsRepository;
    private UserService userService;
    private UsersRepository usersRepository;

    @Autowired
    public NotificationsController(OrderDetailsRepository orderDetailsRepository,
                                   UsersRequestsRepository usersRequestsRepository,
                                   UserService userService,
                                   UsersRepository usersRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.usersRequestsRepository = usersRequestsRepository;
        this.userService = userService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/notifications")
    public String notifications(Map<String, Object> model) {

        final int RECENT_ORDERS_LIST_SIZE = 3;
        final int RECENT_REQUESTS_LIST_SIZE = 3;

        Iterable<OrderDetailsEntity> orders = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> recentOrderDetails = new ArrayList<>();
        if (orderDetailsRepository.count() > 3) {
            for (OrderDetailsEntity element : orders) {
                if (element.getOrderDetailsId() > orderDetailsRepository.count() - RECENT_ORDERS_LIST_SIZE) {
                    recentOrderDetails.add(element);
                }
            }
            model.put("recentOrders", recentOrderDetails);
        } else {
            model.put("recentOrders", orders);
        }
        Iterable<UsersRequestsEntity> usersRequests = usersRequestsRepository.findAll();
        List<UsersRequestsEntity> recentUsersRequests = new ArrayList<>();
        if (usersRequestsRepository.count() > 3) {
            for (UsersRequestsEntity element : usersRequests) {
                if (element.getRequestId() > orderDetailsRepository.count() - RECENT_REQUESTS_LIST_SIZE) {
                    recentUsersRequests.add(element);
                }
            }
            model.put("recentRequests", recentUsersRequests);
        } else {
            model.put("recentRequests", usersRequests);
        }
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
    public String approveRequest(@RequestParam Integer requestId,
                                 @RequestParam String professionChoice,
                                 @RequestParam String authorName) {
        UsersEntity user = usersRepository.findByUsername(authorName);

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

        UsersRequestsEntity userRequest = usersRequestsRepository.findByRequestId(requestId);
        userRequest.getRequestStatuses().clear();

        Set<String> requestStatuses = Arrays.stream(RequestsStatutesEntity.values())
                .map(RequestsStatutesEntity::name)
                .collect(Collectors.toSet());

        Map<String, String> newRequestStatus = new HashMap<>();
        newRequestStatus.put("APPROVED", "");

        for (String key : newRequestStatus.keySet()) {
            if (requestStatuses.contains(key)) {
                userRequest.getRequestStatuses().add(RequestsStatutesEntity.valueOf(key));
            }
        }
        usersRequestsRepository.save(userRequest);
        return "redirect:/notifications";
    }

    @PostMapping(value = "/rejectRequest")
    public String rejectRequest(@RequestParam Integer requestId) {
        UsersRequestsEntity userRequest = usersRequestsRepository.findByRequestId(requestId);
        userRequest.getRequestStatuses().clear();

        Set<String> requestStatuses = Arrays.stream(RequestsStatutesEntity.values())
                .map(RequestsStatutesEntity::name)
                .collect(Collectors.toSet());

        Map<String, String> newRequestStatus = new HashMap<>();
        newRequestStatus.put("REJECTED", "");

        for (String key : newRequestStatus.keySet()) {
            if (requestStatuses.contains(key)) {
                userRequest.getRequestStatuses().add(RequestsStatutesEntity.valueOf(key));
            }
        }
        usersRequestsRepository.save(userRequest);
        return "redirect:/notifications";
    }
}
