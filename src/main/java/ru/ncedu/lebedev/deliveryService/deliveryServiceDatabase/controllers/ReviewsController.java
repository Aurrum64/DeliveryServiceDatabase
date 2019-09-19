package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ReviewsController {

    @GetMapping("/reviews")
    public String orderDetailsView(Map<String, Object> model) {
        return "reviews";
    }
}
