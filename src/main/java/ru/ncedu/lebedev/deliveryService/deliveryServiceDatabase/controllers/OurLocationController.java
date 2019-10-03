package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OurLocationController {

    @GetMapping("/ourLocation")
    public String ourLocationPage() {
        return "ourLocation";
    }
}
