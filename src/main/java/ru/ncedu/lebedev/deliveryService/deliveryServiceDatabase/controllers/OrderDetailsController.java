package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;

import java.util.Map;

@Controller
public class OrderDetailsController {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/")
    public String mainView(Map<String, Object> model) {
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        model.put("orderDetails", orderDetails);
        return "main";
    }

    @PostMapping("/")
    public String add(@RequestParam String orderAddress,
                      @RequestParam String comment,
                      Map<String, Object> model) {
        OrderDetailsEntity orderDetail = new OrderDetailsEntity();
        orderDetail.setOrderAddress(orderAddress);
        orderDetail.setComment(comment);
        orderDetailsRepository.save(orderDetail);
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        model.put("orderDetails", orderDetails);
        return "main";
    }
}
