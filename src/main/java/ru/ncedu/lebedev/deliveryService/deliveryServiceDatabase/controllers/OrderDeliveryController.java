package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class OrderDeliveryController {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDeliveryController(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/orderDelivery")
    public String orderDeliveryView() {
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
                    element.getStatus().equals("Заказ доставляется")) {
                activeOrdersListForCurrentUser.add(element);
            }
        }
        if (activeOrdersListForCurrentUser.isEmpty()) {
            result.setMsg("Active orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(activeOrdersListForCurrentUser);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/waitingOrdersListForUser", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendWaitingOrdersListForUser(@AuthenticationPrincipal UsersEntity user) {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> waitingOrdersListForCurrentUser = new ArrayList<>();
        for (OrderDetailsEntity element : orderDetails) {
            if (user.getUsername().equals(element.getAuthorName()) &&
                    element.getStatus().equals("Заказ в ожидании")) {
                Calendar orderDate = Calendar.getInstance();
                orderDate.setTime(element.getOrderDate());
                orderDate.add(Calendar.HOUR, -3);
                Calendar currentDate = Calendar.getInstance();
                currentDate.setTime(new Date());
                if (orderDate.before(currentDate)) {
                    element.setStatus("Заказ доставляется");
                    orderDetailsRepository.save(element);
                } else {
                    waitingOrdersListForCurrentUser.add(element);
                }
            }
        }
        if (waitingOrdersListForCurrentUser.isEmpty()) {
            result.setMsg("Active orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(waitingOrdersListForCurrentUser);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/archiveOrdersListForUser", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendArchiveOrdersListForUser(@AuthenticationPrincipal UsersEntity user) {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> archiveOrdersListForCurrentUser = new ArrayList<>();
        for (OrderDetailsEntity element : orderDetails) {
            if (user.getUsername().equals(element.getAuthorName()) &&
                    element.getStatus().equals("Заказ доставлен")) {
                archiveOrdersListForCurrentUser.add(element);
            }
        }
        if (archiveOrdersListForCurrentUser.isEmpty()) {
            result.setMsg("Active orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(archiveOrdersListForCurrentUser);
        return ResponseEntity.ok(result);
    }
}
