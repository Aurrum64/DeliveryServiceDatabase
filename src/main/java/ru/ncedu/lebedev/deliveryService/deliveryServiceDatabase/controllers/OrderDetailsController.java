package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;

import java.util.Date;
import java.util.Map;

@Controller
public class OrderDetailsController {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsController(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/orderDetails")
    public String orderDetailsView(Map<String, Object> model) {
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        model.put("orderDetails", orderDetails);
        return "orderDetails";
    }

    @PostMapping("/orderDetails")
    public String add(@RequestParam @DateTimeFormat(pattern = "dd-mm-yyyy") Date orderDate,
                      @RequestParam String orderAddress,
                      @RequestParam(required = false, defaultValue = "Доставить как можно скорее") String comment) {
        OrderDetailsEntity orderDetail = new OrderDetailsEntity();
        orderDetail.setOrderDate(orderDate);
        orderDetail.setOrderAddress(orderAddress);
        orderDetail.setComment(comment);
        orderDetailsRepository.save(orderDetail);
        return "redirect:/orderDetails";
    }

    @PostMapping("/orderDetailsFilter")
    public String findByAddress(@RequestParam String address, Map<String, Object> model) {
        Iterable<OrderDetailsEntity> orderDetails;
        if (address != null && !address.isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderAddress(address);
        } else {
            orderDetails = orderDetailsRepository.findAll();
        }
        model.put("orderDetails", orderDetails);
        return "orderDetails";
    }
}
