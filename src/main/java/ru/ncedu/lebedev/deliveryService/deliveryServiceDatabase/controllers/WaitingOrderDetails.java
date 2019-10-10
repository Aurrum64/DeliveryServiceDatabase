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

import java.util.List;

@Controller
public class WaitingOrderDetails {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public WaitingOrderDetails(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/waitingOrderDetails")
    public String waitingOrderDetails() {
        return "waitingOrderDetails";
    }

    @GetMapping(value = "/waitingOrdersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendWaitingOrdersList(@AuthenticationPrincipal UsersEntity user) {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> waitingOrders = orderDetailsRepository.findAllByStatus("Заказ в ожидании");
        if (waitingOrders.isEmpty()) {
            result.setMsg("Active orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(waitingOrders);
        return ResponseEntity.ok(result);
    }
}
