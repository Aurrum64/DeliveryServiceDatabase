package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Delivery;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Orders;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.DeliveryRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrdersRepository;

import java.util.Date;

@Controller
public class DatabaseController {

    @Autowired
    private OrdersRepository ordersRepository;
    private DeliveryRepository deliveryRepository;

    @GetMapping
    public String defaultInformation() {
        return "default";
    }

    @GetMapping("/addOrder")
    public @ResponseBody
    String addNewOrder(@RequestParam Integer departmentId,
                       @RequestParam Integer managerId,
                       @RequestParam Integer courierId,
                       @RequestParam String paymentMethod,
                       @RequestParam Integer orderPrice,
                       @RequestParam Integer discount) {
        Orders order = new Orders();
        order.setDepartmentId(departmentId);
        order.setManagerId(managerId);
        order.setCourierId(courierId);
        order.setPaymentMethod(paymentMethod);
        order.setOrderPrice(orderPrice);
        order.setDiscount(discount);
        ordersRepository.save(order);
        return "Saved";
    }

    @GetMapping("/addDeliveryInfo")
    public @ResponseBody
    String addNewDelivery(@RequestParam Date date,
                          @RequestParam String address,
                          @RequestParam String comment) {
        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(date);
        delivery.setDeliveryAddress(address);
        delivery.setComment(comment);
        deliveryRepository.save(delivery);
        return "saved";
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
