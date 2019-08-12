package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Couriers;
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

    @GetMapping("/addDeliveryInfo")
    public @ResponseBody
    String addNewDeliveryInfo(@RequestParam @DateTimeFormat(pattern = "dd-mm-yyyy") Date deliveryDate,
                              @RequestParam String address,
                              @RequestParam String comment) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setDeliveryDate(deliveryDate);
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
                         @RequestParam Integer premium,
                         @RequestParam Integer departmentId) {
        Couriers couriers = new Couriers();
        couriers.setFirstName(firstName);
        couriers.setLastName(lastName);
        couriers.setEmail(eMail);
        couriers.setPhoneNumber(phoneNumber);
        couriers.setRating(rating);
        couriers.setSalary(salary);
        couriers.setHireDate(hireDate);
        couriers.setPremium(premium);
        couriers.setDepartmentId(departmentId);
        couriersRepository.save(couriers);
        return "Courier saved.";
    }
}
