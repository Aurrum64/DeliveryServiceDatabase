package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrdersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrdersRepository;

@Controller
public class OrdersController {

    private OrdersRepository ordersRepository;

    @Autowired
    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/addOrder")
    public @ResponseBody
    String addNewOrder(@RequestParam Integer departmentId,
                       @RequestParam Integer managerId,
                       @RequestParam Integer courierId,
                       @RequestParam String paymentMethod,
                       @RequestParam Integer orderPrice,
                       @RequestParam Integer discount) {
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setDepartmentId(departmentId);
        ordersEntity.setManagerId(managerId);
        ordersEntity.setCourierId(courierId);
        ordersEntity.setPaymentMethod(paymentMethod);
        ordersEntity.setOrderPrice(orderPrice);
        ordersEntity.setDiscount(discount);
        ordersRepository.save(ordersEntity);
        return "Order saved.";
    }
}
