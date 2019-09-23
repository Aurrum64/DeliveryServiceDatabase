package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class NotificationsController {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public NotificationsController(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/notifications")
    public String notifications(Map<String, Object> model) {
        Iterable<OrderDetailsEntity> orders = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> recentOrderDetails = new ArrayList<>();
        for (OrderDetailsEntity element : orders) {
            if (element.getOrderDetailsId() > orderDetailsRepository.count() - 3) {
                recentOrderDetails.add(element);
            }
        }
        model.put("recentOrders", recentOrderDetails);
        return "notifications";
    }
}
