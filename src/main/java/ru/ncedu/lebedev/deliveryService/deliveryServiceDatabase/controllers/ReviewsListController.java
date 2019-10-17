package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendReviewsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ReviewsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ReviewsEntity;

@Controller
public class ReviewsListController {

    private ReviewsRepository reviewsRepository;

    @Autowired
    public ReviewsListController(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @GetMapping("/reviewsList")
    public String reviewsList() {
        return "reviewsList";
    }

    @GetMapping(value = "/reviewsList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendReviewsList() {

        SendReviewsToAjax reviewsList = new SendReviewsToAjax();
        Iterable<ReviewsEntity> reviews = reviewsRepository.findAll();
        if (!reviews.iterator().hasNext()) {
            reviewsList.setMessage("Reviews list is empty!");
        } else {
            reviewsList.setMessage("success");
        }
        reviewsList.setResult(reviews);
        return ResponseEntity.ok(reviewsList);
    }
}
