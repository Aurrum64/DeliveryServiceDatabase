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

    @GetMapping(value = "/activeOrdersListForLogisticPage", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveOrdersListForManager() {

        final int FIRST_ACTIVE_ORDERS_LIST_SIZE = 3;

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> activeOrders = orderDetailsRepository.findAllByStatus("Заказ не доставлен");
        if (orderDetailsRepository.count() > FIRST_ACTIVE_ORDERS_LIST_SIZE) {
            List<OrderDetailsEntity> firstActiveOrders = activeOrders.subList(0, FIRST_ACTIVE_ORDERS_LIST_SIZE);
            if (firstActiveOrders.isEmpty()) {
                result.setMsg("Active orders list is empty!");
            } else {
                result.setMsg("success");
            }
            result.setResult(firstActiveOrders);
        } else {
            if (activeOrders.isEmpty()) {
                result.setMsg("Active orders list is empty!");
            } else {
                result.setMsg("success");
            }
            result.setResult(activeOrders);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/archiveOrdersListForLogisticPage", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendArchiveOrdersListForManager() {

        final int RECENT_ARCHIVE_ORDERS_LIST_SIZE = 10;

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> archiveOrders = orderDetailsRepository.findAllByStatus("Заказ доставлен");
        if (orderDetailsRepository.count() > 10) {
            List<OrderDetailsEntity> recentArchiveOrders = archiveOrders.subList(archiveOrders.size() - RECENT_ARCHIVE_ORDERS_LIST_SIZE,
                    archiveOrders.size());
            if (recentArchiveOrders.isEmpty()) {
                result.setMsg("Archive orders list is empty!");
            } else {
                result.setMsg("success");
            }
            result.setResult(recentArchiveOrders);
        } else {
            if (archiveOrders.isEmpty()) {
                result.setMsg("Archive orders list is empty!");
            } else {
                result.setMsg("success");
            }
            result.setResult(archiveOrders);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/allArchiveOrdersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendAllArchiveOrdersList() {
        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> archiveOrders = orderDetailsRepository.findAllByStatus("Заказ доставлен");
        if (archiveOrders.isEmpty()) {
            result.setMsg("Archive orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(archiveOrders);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/allActiveOrdersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendAllActiveOrdersList() {
        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> archiveOrders = orderDetailsRepository.findAllByStatus("Заказ не доставлен");
        if (archiveOrders.isEmpty()) {
            result.setMsg("Archive orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(archiveOrders);
        return ResponseEntity.ok(result);
    }
}
