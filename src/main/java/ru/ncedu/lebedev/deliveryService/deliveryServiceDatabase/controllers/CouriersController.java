package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Couriers;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;

import java.util.Date;
import java.util.Map;

@Controller
public class CouriersController {

    private CouriersRepository couriersRepository;

    @Autowired
    public CouriersController(CouriersRepository couriersRepository) {
        this.couriersRepository = couriersRepository;
    }

    @GetMapping("/couriers")
    public String couriersView(Map<String, Object> model) {
        Iterable<Couriers> couriers = couriersRepository.findAll();
        model.put("couriers", couriers);
        return "couriers";
    }

    @PostMapping("/couriers")
    public String addCourier(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false, defaultValue = "+7(000)-000-00-00") String phoneNumber,
                             @RequestParam(required = false, defaultValue = "0") Integer rating,
                             @RequestParam(required = false, defaultValue = "0") Integer salary,
                             @RequestParam(required = false, defaultValue = "01-01-2000") @DateTimeFormat(pattern = "dd-mm-yyyy") Date hireDate,
                             @RequestParam(required = false, defaultValue = "0") Integer premium,
                             @RequestParam(required = false, defaultValue = "0") Integer departmentId) {
        Couriers courier = new Couriers();
        courier.setFirstName(firstName);
        courier.setLastName(lastName);
        courier.setEmail(email);
        courier.setPhoneNumber(phoneNumber);
        courier.setRating(rating);
        courier.setSalary(salary);
        courier.setHireDate(hireDate);
        courier.setPremium(premium);
        courier.setDepartmentId(departmentId);
        couriersRepository.save(courier);
        return "redirect:/couriers";
    }

    @PostMapping("/couriersFilter")
    public String findCourier(@RequestParam(required = false) Integer courierId, Map<String, Object> model) {
        Iterable<Couriers> couriers;
        if (courierId != null) {
            couriers = couriersRepository.findByCourierId(courierId);
        } else {
            couriers = couriersRepository.findAll();
        }
        model.put("couriers", couriers);
        return "couriers";
    }

    @Transactional
    @PostMapping("/couriersDelete")
    public String deleteCourier(@RequestParam Integer courierId) {
        couriersRepository.deleteByCourierId(courierId);
        return "redirect:/couriers";
    }

    @Transactional
    @PostMapping("/couriersUpdate")
    public String updateCourier(@RequestParam String firstName, Integer courierId) {
        couriersRepository.setFirstnameFor(firstName, courierId);
        return "redirect:/couriers";
    }
}
