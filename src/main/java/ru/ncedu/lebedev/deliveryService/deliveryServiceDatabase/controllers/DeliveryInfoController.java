package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.DeliveryInfo;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.DeliveryRepository;

import java.util.Date;

@Controller
public class DeliveryInfoController {

    private DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryInfoController(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping("/addDeliveryInfo")
    public @ResponseBody
    String addNewDeliveryInfo(@RequestParam @DateTimeFormat(pattern = "dd-mm-yyyy") Date deliveryDate,
                              @RequestParam String address,
                              @RequestParam(required = false, defaultValue = "Доставить как можно скорее") String comment) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setDeliveryDate(deliveryDate);
        deliveryInfo.setDeliveryAddress(address);
        deliveryInfo.setComment(comment);
        deliveryRepository.save(deliveryInfo);
        return "Delivery info saved.";
    }
}
