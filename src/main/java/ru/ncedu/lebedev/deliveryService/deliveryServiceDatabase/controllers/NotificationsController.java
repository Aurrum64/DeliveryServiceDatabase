package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.ControllerAnswerToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.UsersRequestsMessage;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.RandomCoordinates;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.UserService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class NotificationsController {

    private OrderDetailsRepository orderDetailsRepository;
    private UsersRequestsRepository usersRequestsRepository;
    private UserService userService;
    private UsersRepository usersRepository;
    private CouriersRepository couriersRepository;
    private ManagersRepository managersRepository;

    @Autowired
    public NotificationsController(OrderDetailsRepository orderDetailsRepository,
                                   UsersRequestsRepository usersRequestsRepository,
                                   UserService userService,
                                   UsersRepository usersRepository,
                                   CouriersRepository couriersRepository,
                                   ManagersRepository managersRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.usersRequestsRepository = usersRequestsRepository;
        this.userService = userService;
        this.usersRepository = usersRepository;
        this.couriersRepository = couriersRepository;
        this.managersRepository = managersRepository;
    }

    @GetMapping("/notifications")
    public String notifications(Map<String, Object> model) {

        final int RECENT_ORDERS_LIST_SIZE = 3;
        final int RECENT_REQUESTS_LIST_SIZE = 3;

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
    public String approveRequest(@AuthenticationPrincipal UsersEntity whoApprovedRequest,
                                 @RequestParam Integer requestId,
                                 @RequestParam String professionChoice,
                                 @RequestParam String source,
                                 @RequestParam String authorName) throws ParseException {
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

        userService.sendHiredEmail(user, professionChoice);

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
        } else {
            ManagersEntity manager = new ManagersEntity();
            manager.setFirstName(user.getUsername());
            manager.setLastName("");
            manager.setEmail(user.getEmail());
            manager.setHireDate(currentDate);
            manager.setAuthor(whoApprovedRequest);
            managersRepository.save(manager);
        }

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

        UsersEntity user = usersRepository.findByUsername(authorName);

        userService.sendRejectedEmail(user, professionChoice);

        if (source.equals("notifications")) {
            return "redirect:/notifications";
        } else {
            return "redirect:/requests";
        }
    }
}
