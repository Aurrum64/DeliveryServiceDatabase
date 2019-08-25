package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Orders;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrdersRepository;

import java.util.List;
import java.util.Map;

@Controller
public class OrdersController {

    private OrdersRepository ordersRepository;

    @Autowired
    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    @GetMapping("/orders")
    public String ordersView(Map<String, Object> model) {
        Iterable<Orders> orders = ordersRepository.findAll();
        model.put("orders", orders);
        return "orders";
    }
    @PostMapping("/orders")
    public String addNewOrder(@RequestParam Integer departmentId,
                       @RequestParam Integer managerId,
                       @RequestParam Integer courierId,
                       @RequestParam String paymentMethod,
                       @RequestParam Integer orderPrice,
                       @RequestParam Integer discount) {
        Orders orders = new Orders();
        orders.setDepartmentId(departmentId);
        orders.setManagerId(managerId);
        orders.setCourierId(courierId);
        orders.setPaymentMethod(paymentMethod);
        orders.setOrderPrice(orderPrice);
        orders.setDiscount(discount);
        ordersRepository.save(orders);
        return "redirect:/orders";
    }
    @PostMapping("/ordersFilter")
    public String findByOrderPayment(@RequestParam String paymentMeth, Map<String, Object> model) {
        Iterable<Orders> orders;
        if (paymentMeth != null && !paymentMeth.isEmpty()) {
            orders = ordersRepository.findByPaymentMethod(paymentMeth);
        } else {
            orders = ordersRepository.findAll();
        }
        model.put("orders", orders);
        return "orders";
    }
}
