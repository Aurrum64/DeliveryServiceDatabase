package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Couriers;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;

import java.util.Date;

@Controller
public class CouriersController {

    //Эта аннотация устарела, Сонар на нее жалуется, лучше делать по другому, позже переделаю
    @Autowired
    private CouriersRepository couriersRepository;

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
