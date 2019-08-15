package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Orders;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrdersRepository;

@Controller
public class OrdersController {

    //Эта аннотация устарела, Сонар на нее жалуется, лучше делать по другому, позже переделаю
    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/addOrder")
    public @ResponseBody
    String addNewOrder(@RequestParam Integer departmentId,
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
        return "Order saved.";
    }
}
