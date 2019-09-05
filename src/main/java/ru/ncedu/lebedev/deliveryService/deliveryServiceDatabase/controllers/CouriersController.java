package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.AjaxResponseEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;

import java.util.*;

@Controller
public class CouriersController {

    private CouriersRepository couriersRepository;

    @Autowired
    public CouriersController(CouriersRepository couriersRepository) {
        this.couriersRepository = couriersRepository;
    }

    @GetMapping("/couriers")
    public String couriersView(Map<String, Object> model) {
        Iterable<CouriersEntity> couriers = couriersRepository.findAll();
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
                             @RequestParam(required = false, defaultValue = "0") Integer departmentId,
                             @RequestParam Double latitude,
                             @RequestParam Double longitude) {
        CouriersEntity courier = new CouriersEntity();
        courier.setFirstName(firstName);
        courier.setLastName(lastName);
        courier.setEmail(email);
        courier.setPhoneNumber(phoneNumber);
        courier.setRating(rating);
        courier.setSalary(salary);
        courier.setHireDate(hireDate);
        courier.setPremium(premium);
        courier.setDepartmentId(departmentId);
        courier.setLatitude(latitude);
        courier.setLongitude(longitude);
        couriersRepository.save(courier);
        return "redirect:/couriers";
    }

    @PostMapping("/couriersFilter")
    public String findCourier(@RequestParam(required = false) Integer courierId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              Map<String, Object> model) {
        Iterable<CouriersEntity> couriers;
        if (courierId != null & firstName.isEmpty() & lastName.isEmpty()) {
            couriers = couriersRepository.findByCourierId(courierId);
        } else if (courierId == null & !firstName.isEmpty() & !lastName.isEmpty()) {
            couriers = couriersRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (courierId == null & !firstName.isEmpty() & lastName.isEmpty()) {
            couriers = couriersRepository.findByFirstName(firstName);
        } else if (courierId != null & !firstName.isEmpty() & lastName.isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndFirstName(courierId, firstName);
        } else if (courierId != null & firstName.isEmpty() & !lastName.isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndLastName(courierId, lastName);
        } else if (courierId == null & firstName.isEmpty() & !lastName.isEmpty()) {
            couriers = couriersRepository.findByLastName(lastName);
        } else if (courierId != null & !firstName.isEmpty() & !lastName.isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndFirstNameAndLastName(courierId, firstName, lastName);
        } else {
            couriers = couriersRepository.findAll();
        }
        if (!couriers.iterator().hasNext()) {
            model.put("filterCheck", "No courier with such index!");
            return "couriers";
        } else {
            model.put("couriers", couriers);
        }
        return "couriers";
    }

    @Transactional
    @PostMapping("/couriersDelete")
    public String deleteCourier(@RequestParam Integer courierId, Map<String, Object> model) {
        List<CouriersEntity> courier = couriersRepository.findByCourierId(courierId);
        if (courier.isEmpty()) {
            model.put("deleteIdCheck", "No courier with such index!");
            return "couriers";
        } else {
            couriersRepository.deleteByCourierId(courierId);
        }
        return "redirect:/couriers";
    }

    @Transactional
    @PostMapping("/couriersUpdate")
    public String updateCourier(@RequestParam Integer courierId,
                                @RequestParam(required = false) String firstName,
                                @RequestParam(required = false) String lastName,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String phoneNumber,
                                @RequestParam(required = false) Integer rating,
                                @RequestParam(required = false) Integer salary,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "dd-mm-yyyy") Date hireDate,
                                @RequestParam(required = false) Integer premium,
                                @RequestParam(required = false) Integer departmentId,
                                Map<String, Object> model) {
        List<CouriersEntity> courier = couriersRepository.findByCourierId(courierId);
        if (courier.isEmpty()) {
            model.put("updateIdCheck", "Courier with such index does not exist!");
            return "couriers";
        } else {
            if (!firstName.isEmpty()) {
                couriersRepository.setFirstNameFor(firstName, courierId);
            }
            if (!lastName.isEmpty()) {
                couriersRepository.setLastNameFor(lastName, courierId);
            }
            if (!email.isEmpty()) {
                couriersRepository.setEmailFor(email, courierId);
            }
            if (!phoneNumber.isEmpty()) {
                couriersRepository.setPhoneNumberFor(phoneNumber, courierId);
            }
            if (rating != null) {
                couriersRepository.setRatingFor(rating, courierId);
            }
            if (salary != null) {
                couriersRepository.setSalaryFor(salary, courierId);
            }
            if (hireDate != null) {
                couriersRepository.setHireDateFor(hireDate, courierId);
            }
            if (premium != null) {
                couriersRepository.setPremiumFor(premium, courierId);
            }
            if (departmentId != null) {
                couriersRepository.setDepartmentFor(departmentId, courierId);
            }
        }
        return "redirect:/couriers";
    }

    @GetMapping(value = "/couriersCoordinates", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendCouriersCoordinates() {

        AjaxResponseEntity result = new AjaxResponseEntity();
        Iterable<CouriersEntity> couriers = couriersRepository.findAll();
        if (!couriers.iterator().hasNext()) {
            result.setMsg("Couriers list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(couriers);
        return ResponseEntity.ok(result);
    }
}
