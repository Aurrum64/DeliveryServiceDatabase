package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendOrderDetailsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderDeliveryController {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDeliveryController(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/orderDelivery")
    public String orderDelivery() {
        return "orderDelivery";
    }

    @GetMapping(value = "/activeOrdersListForUser", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveOrdersListForUser(@AuthenticationPrincipal UsersEntity user) {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> activeOrdersListForCurrentUser = new ArrayList<>();
        for (OrderDetailsEntity element : orderDetails) {
            if (user.getUsername().equals(element.getAuthorName()) &&
                    element.getStatus().equals("Заказ не доставлен")) {
                activeOrdersListForCurrentUser.add(element);
            }
        }
        if (activeOrdersListForCurrentUser.isEmpty()) {
            result.setMsg("Active orders list for this user is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(activeOrdersListForCurrentUser);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/archiveOrdersListForUser", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendArchiveOrdersListForUser(@AuthenticationPrincipal UsersEntity user) {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> activeOrdersListForCurrentUser = new ArrayList<>();
        for (OrderDetailsEntity element : orderDetails) {
            if (user.getUsername().equals(element.getAuthorName()) &&
                    element.getStatus().equals("Заказ доставлен")) {
                activeOrdersListForCurrentUser.add(element);
            }
        }
        if (activeOrdersListForCurrentUser.isEmpty()) {
            result.setMsg("Active orders list for this user is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(activeOrdersListForCurrentUser);
        return ResponseEntity.ok(result);
    }
}
