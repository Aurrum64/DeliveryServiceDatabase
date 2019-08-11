package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Courier;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.DeliveryInfo;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Orders;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.DeliveryRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrdersRepository;

import java.util.Date;

@Controller
public class DatabaseController {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private CouriersRepository couriersRepository;

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
        return "Order saved.";
    }

    @GetMapping("/addDeliveryInfo")
    public @ResponseBody
    String addNewDeliveryInfo(@RequestParam @DateTimeFormat(pattern = "dd-mm-yyyy") Date date,
                              @RequestParam String address,
                              @RequestParam String comment) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setDeliveryDate(date);
        deliveryInfo.setDeliveryAddress(address);
        deliveryInfo.setComment(comment);
        deliveryRepository.save(deliveryInfo);
        return "Delivery info saved.";
    }

    @GetMapping("/addCourier")
    public @ResponseBody
    String addNewCourier(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String eMail,
                         @RequestParam Integer phoneNumber,
                         @RequestParam Integer rating,
                         @RequestParam Integer salary,
                         @RequestParam @DateTimeFormat(pattern = "dd-mm-yyyy") Date hireDate,
                         @RequestParam Double premium,
                         @RequestParam Integer departmentId) {
        Courier courier = new Courier();
        courier.setFirstName(firstName);
        courier.setLastName(lastName);
        courier.setEmail(eMail);
        courier.setPhoneNumber(phoneNumber);
        courier.setRating(rating);
        courier.setSalary(salary);
        courier.setHireDate(hireDate);
        courier.setPremium(premium);
        courier.setDepartmentId(departmentId);
        couriersRepository.save(courier);
        return "Courier saved.";
    }
}
