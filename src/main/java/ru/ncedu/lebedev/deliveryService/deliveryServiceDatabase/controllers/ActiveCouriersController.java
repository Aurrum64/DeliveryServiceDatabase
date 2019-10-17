package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendCouriersToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;

import java.util.List;

@Controller
public class ActiveCouriersController {

    private CouriersRepository couriersRepository;

    @Autowired
    public ActiveCouriersController(CouriersRepository couriersRepository) {
        this.couriersRepository = couriersRepository;
    }

    @GetMapping("/activeCouriers")
    public String activeCouriers() {
        return "activeCouriers";
    }

    @GetMapping(value = "/activeCouriersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveCouriersList() {

        SendCouriersToAjax activeCouriersList = new SendCouriersToAjax();
        List<CouriersEntity> activeCouriers = couriersRepository.findAllByReadinessAndFired(true, false);
        if (activeCouriers.isEmpty()) {
            activeCouriersList.setMsg("Rest couriers list is empty!");
        } else {
            activeCouriersList.setMsg("success");
        }
        activeCouriersList.setResult(activeCouriers);
        return ResponseEntity.ok(activeCouriersList);
    }
}
