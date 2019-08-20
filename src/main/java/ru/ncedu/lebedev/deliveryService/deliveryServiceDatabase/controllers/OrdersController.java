package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Orders;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrdersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrdersController {

    //Эта аннотация устарела, Сонар на нее жалуется, лучше делать по другому, позже переделаю
    @Autowired
    private OrdersRepository ordersRepository;
    @GetMapping("/orderslist")
    public String getAllOrders(Map<String,Object> model) {
        System.out.println("Get all Orders...");
        Iterable<Orders> orders = ordersRepository.findAll();
        model.put("orders", orders);
        return "orders";
    }
    @PostMapping("/orders")
    public String postOrder(@RequestParam Integer departmentId,
                            @RequestParam Integer managerId,
                            @RequestParam Integer courierId,
                            @RequestParam String paymentMethod,
                            @RequestParam Integer orderPrice,
                            @RequestParam Integer discount,
                            Model model) {
        Orders orders = new Orders(departmentId, managerId, courierId, paymentMethod, orderPrice, discount);
        ordersRepository.save(orders);
        return "main";
    }
}
