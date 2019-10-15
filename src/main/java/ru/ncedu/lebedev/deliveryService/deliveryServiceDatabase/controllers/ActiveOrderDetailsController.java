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
public class ActiveOrderDetailsController {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public ActiveOrderDetailsController(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/activeOrderDetails")
    public String activeOrderDetails() {
        return "activeOrderDetails";
    }

    @GetMapping(value = "/allActiveOrdersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendAllActiveOrdersList() {
        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> archiveOrders = orderDetailsRepository.findAllByStatus("Заказ доставляется");
        if (archiveOrders.isEmpty()) {
            result.setMsg("Archive orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(archiveOrders);
        return ResponseEntity.ok(result);
    }
}
