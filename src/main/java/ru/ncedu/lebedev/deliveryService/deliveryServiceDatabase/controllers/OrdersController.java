package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrdersEntity;
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
        Iterable<OrdersEntity> orders = ordersRepository.findAll();
        model.put("orders", orders);
        return "orders";
    }
    @PostMapping("/orders")
    public String addNewOrder(@RequestParam (required = false, defaultValue = "0") Integer departmentId,
                              @RequestParam (required = false, defaultValue = "0") Integer managerId,
                              @RequestParam (required = false, defaultValue = "0") Integer courierId,
                              @RequestParam String paymentMethod,
                              @RequestParam Integer orderPrice,
                              @RequestParam Integer discount) {

        OrdersEntity orders = new OrdersEntity();
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
    public String findByOrderPayment(@RequestParam String paymentMethod, Map<String, Object> model) {
        Iterable<OrdersEntity> ordersEntities;
        if (!paymentMethod.isEmpty()) {
            ordersEntities = ordersRepository.findByPaymentMethod(paymentMethod);
        }
        else {
            ordersEntities = ordersRepository.findAll();
        }
        if (!ordersEntities.iterator().hasNext()) {
            model.put("filterCheck", "No orders with such price!");
            return "orders";
        } else {
            model.put("orders", ordersEntities);
        }
        return "orders";

    }
    @Transactional
    @PostMapping("/ordersDelete")
    public String deleteOrders(@RequestParam Integer orderId, Map<String, Object> model) {
        List<OrdersEntity> ordersEntities = ordersRepository.findByOrderId(orderId);
        if (ordersEntities.isEmpty()) {
            model.put("deleteIdCheck", "No order with such index!");
            return "orders";
        } else {
            ordersRepository.deleteByOrderId(orderId);
        }
        return "redirect:/orders";
    }

    @Transactional
    @PostMapping("/ordersUpdate")
    public String updateOrders(@RequestParam Integer orderId,
                               @RequestParam (required = false) Integer departmentId,
                               @RequestParam (required = false) Integer managerId,
                               @RequestParam (required = false) Integer courierId,
                               @RequestParam (required = false) String paymentMethod,
                               @RequestParam (required = false) Integer orderPrice,
                               @RequestParam (required = false) Integer discount,
                                   Map<String, Object> model) {
        List<OrdersEntity> ordersEntities = ordersRepository.findByOrderId(orderId);
        if (ordersEntities.isEmpty()) {
            model.put("updateIdCheck", "Order with such index does not exist!");
            return "orders";
        } else {
            if (departmentId!=null) {
                ordersRepository.setDepartmentIdFor(departmentId,orderId);
            }
            if (managerId!=null) {
                ordersRepository.setManagerIdFor(managerId, orderId);
            }
            if (courierId != null) {
                ordersRepository.setCourierIdFor(courierId, orderId);
            }
            if (!paymentMethod.isEmpty()) {
                ordersRepository.setPaymentMethodFor(paymentMethod, orderId);
            }
            if (orderPrice!=null) {
                ordersRepository.setOrderPriceFor(orderPrice, orderId);
            }
            if (discount!=null) {
                ordersRepository.setDiscountFor(discount, orderId);
            }
        }
        return "redirect:/orders";
    }
}
