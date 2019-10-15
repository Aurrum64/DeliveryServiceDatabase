package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendOrderDetailsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;

import java.util.List;

@Controller
public class OrderDetailsHistoryController {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsHistoryController(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/orderDetailsHistory")
    public String orderDetailsHistory() {
        return "orderDetailsHistory";
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
}
