package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

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

/*    @PostMapping("/couriers")
    public String adCourier(@AuthenticationPrincipal UsersEntity user,
                            @RequestParam String firstName,
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
        courier.setAuthor(user);
        couriersRepository.save(courier);
        return "redirect:/couriers";
    }*/

    @GetMapping(value = "/couriersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendCouriersList() {

        SendCouriersToAjax couriersList = new SendCouriersToAjax();
        Iterable<CouriersEntity> couriers = couriersRepository.findAll();
        if (!couriers.iterator().hasNext()) {
            couriersList.setMsg("Couriers list is empty!");
        } else {
            couriersList.setMsg("success");
        }
        couriersList.setResult(couriers);
        return ResponseEntity.ok(couriersList);
    }

    @PostMapping(value = "/addCouriers",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax addCourier(@AuthenticationPrincipal UsersEntity user,
                                             @RequestBody CouriersMessage courierMessage) {
        CouriersEntity courier = new CouriersEntity();
        courier.setFirstName(courierMessage.getFirstName());
        courier.setLastName(courierMessage.getLastName());
        courier.setEmail(courierMessage.getEmail());
        if (courierMessage.getPhoneNumber().isEmpty()) {
            courier.setPhoneNumber("+7(916)000-00-00");
        } else {
            courier.setPhoneNumber(courierMessage.getPhoneNumber());
        }
        if (courierMessage.getRating() == null) {
            courier.setRating(0);
        } else {
            courier.setRating(courierMessage.getRating());
        }
        if (courierMessage.getSalary() == null) {
            courier.setSalary(19_351);
        } else {
            courier.setSalary(courierMessage.getSalary());
        }
        courier.setHireDate(courierMessage.getHireDate());
        if (courierMessage.getPremium() == null) {
            courier.setPremium(0);
        } else {
            courier.setPremium(courierMessage.getPremium());
        }
        if (courierMessage.getDepartmentId() == null) {
            courier.setDepartmentId(0);
        } else {
            courier.setDepartmentId(courierMessage.getDepartmentId());
        }
        courier.setLatitude(courierMessage.getLatitude());
        courier.setLongitude(courierMessage.getLongitude());
        courier.setAuthor(user);
        couriersRepository.save(courier);
        return new ControllerAnswerToAjax("OK", "");
    }

    @PostMapping(value = "/searchCouriers",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity<?> findCouriers(@RequestBody CouriersMessage courierMessage) {
        Iterable<CouriersEntity> couriers;
        if (courierMessage.getCourierId() != null & courierMessage.getFirstName().isEmpty() & courierMessage.getLastName().isEmpty()) {
            couriers = couriersRepository.findByCourierId(courierMessage.getCourierId());
        } else if (courierMessage.getCourierId() == null & !courierMessage.getFirstName().isEmpty() & !courierMessage.getLastName().isEmpty()) {
            couriers = couriersRepository.findByFirstNameAndLastName(courierMessage.getFirstName(), courierMessage.getLastName());
        } else if (courierMessage.getCourierId() == null & !courierMessage.getFirstName().isEmpty() & courierMessage.getLastName().isEmpty()) {
            couriers = couriersRepository.findByFirstName(courierMessage.getFirstName());
        } else if (courierMessage.getCourierId() != null & !courierMessage.getFirstName().isEmpty() & courierMessage.getLastName().isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndFirstName(courierMessage.getCourierId(), courierMessage.getFirstName());
        } else if (courierMessage.getCourierId() != null & courierMessage.getFirstName().isEmpty() & !courierMessage.getLastName().isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndLastName(courierMessage.getCourierId(), courierMessage.getLastName());
        } else if (courierMessage.getCourierId() == null & courierMessage.getFirstName().isEmpty() & !courierMessage.getLastName().isEmpty()) {
            couriers = couriersRepository.findByLastName(courierMessage.getLastName());
        } else if (courierMessage.getCourierId() != null & !courierMessage.getFirstName().isEmpty() & !courierMessage.getLastName().isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndFirstNameAndLastName(courierMessage.getCourierId(), courierMessage.getFirstName(), courierMessage.getLastName());
        } else {
            couriers = couriersRepository.findAll();
        }
        SendCouriersToAjax couriersSearchList = new SendCouriersToAjax();
        if (!couriers.iterator().hasNext()) {
            couriersSearchList.setMsg("Nothing found!");
        } else {
            couriersSearchList.setMsg("success");
        }
        couriersSearchList.setResult(couriers);
        return ResponseEntity.ok(couriersSearchList);
    }

    @Transactional
    @PostMapping(value = "/deleteCouriers",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax deleteCourier(@RequestBody CouriersMessage courierMessage) {
        List<CouriersEntity> courier = couriersRepository.findByCourierId(courierMessage.getCourierId());
        if (courier.isEmpty()) {
            return new ControllerAnswerToAjax("NOT EXISTS", "");
        } else {
            couriersRepository.deleteByCourierId(courierMessage.getCourierId());
        }
        return new ControllerAnswerToAjax("OK", "");
    }

    @Transactional
    @PostMapping(value = "/updateCouriers",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax updateCourier(@RequestBody CouriersMessage couriersMessage) {
        List<CouriersEntity> couriersList = couriersRepository.findByCourierId(couriersMessage.getCourierId());
        if (couriersList.isEmpty()) {
            return new ControllerAnswerToAjax("NOT EXISTS", "");
        } else {
            if (!couriersMessage.getFirstName().isEmpty()) {
                couriersRepository.setFirstNameFor(couriersMessage.getFirstName(), couriersMessage.getCourierId());
            }
            if (!couriersMessage.getLastName().isEmpty()) {
                couriersRepository.setLastNameFor(couriersMessage.getLastName(), couriersMessage.getCourierId());
            }
            if (!couriersMessage.getEmail().isEmpty()) {
                couriersRepository.setEmailFor(couriersMessage.getEmail(), couriersMessage.getCourierId());
            }
            if (!couriersMessage.getPhoneNumber().isEmpty()) {
                couriersRepository.setPhoneNumberFor(couriersMessage.getPhoneNumber(), couriersMessage.getCourierId());
            }
            if (couriersMessage.getRating() != null) {
                couriersRepository.setRatingFor(couriersMessage.getRating(), couriersMessage.getCourierId());
            }
            if (couriersMessage.getSalary() != null) {
                couriersRepository.setSalaryFor(couriersMessage.getSalary(), couriersMessage.getCourierId());
            }
            if (couriersMessage.getHireDate() != null) {
                couriersRepository.setHireDateFor(couriersMessage.getHireDate(), couriersMessage.getCourierId());
            }
            if (couriersMessage.getPremium() != null) {
                couriersRepository.setPremiumFor(couriersMessage.getPremium(), couriersMessage.getCourierId());
            }
            if (couriersMessage.getDepartmentId() != null) {
                couriersRepository.setDepartmentFor(couriersMessage.getDepartmentId(), couriersMessage.getCourierId());
            }
        }
        return new ControllerAnswerToAjax("OK", "");
    }

    @GetMapping(value = "/couriersCoordinates", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendCouriersCoordinates() {

        SendCouriersToAjax result = new SendCouriersToAjax();
        Iterable<CouriersEntity> couriers = couriersRepository.findAll();
        if (!couriers.iterator().hasNext()) {
            result.setMsg("Couriers list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(couriers);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @RequestMapping(value = "/movingCourierCoordinates",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax courierMove(@RequestBody CourierCoordinateAfterMove courier) {
        couriersRepository.setLatitudeFor(courier.getLat(), courier.getCourierId());
        couriersRepository.setLongitudeFor(courier.getLng(), courier.getCourierId());
        return new ControllerAnswerToAjax("OK", "");
    }
}
