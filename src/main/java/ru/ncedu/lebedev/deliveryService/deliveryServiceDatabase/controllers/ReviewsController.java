package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.ControllerAnswerToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.ReviewMessage;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendReviewsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ReviewsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.CouriersRatingSystem;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ReviewsEntity;

@Controller
public class ReviewsController {

    private ReviewsRepository reviewsRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private CouriersRepository couriersRepository;
    private CouriersRatingSystem couriersRating;

    @Autowired
    public ReviewsController(ReviewsRepository reviewsRepository,
                             OrderDetailsRepository orderDetailsRepository,
                             CouriersRepository couriersRepository,
                             CouriersRatingSystem couriersRating) {
        this.reviewsRepository = reviewsRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.couriersRepository = couriersRepository;
        this.couriersRating = couriersRating;
    }

    @GetMapping("/reviews")
    public String orderDetailsView() {
        return "reviews";
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

    @PostMapping(value = "/addReviews",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax addReviews(@RequestBody ReviewMessage reviewMessage) {

        OrderDetailsEntity order = orderDetailsRepository.findByOrderDetailsId(reviewMessage.getOrderId());

        CouriersEntity courier = couriersRepository.findByCourierId(order.getCourier().getCourierId());

        couriersRating.setCourierRating(courier, reviewMessage.getRating());

        ReviewsEntity review = new ReviewsEntity();
        review.setOrderId(reviewMessage.getOrderId());
        review.setClientName(reviewMessage.getClientName());
        review.setRating(reviewMessage.getRating());
        review.setReviewSubject(reviewMessage.getReviewSubject());
        review.setReview(reviewMessage.getReview());
        reviewsRepository.save(review);
        return new ControllerAnswerToAjax("OK", "");
    }
}
