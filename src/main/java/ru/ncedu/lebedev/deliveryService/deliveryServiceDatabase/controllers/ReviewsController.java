package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ReviewsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service.CouriersRatingSystem;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ReviewsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.Map;

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
    public String reviewsView() {
        return "reviews";
    }

    @PostMapping("/reviews")
    public String reviewsView(@RequestParam Integer orderDetailsId,
                              @RequestParam String authorName,
                              Map<String, Object> model) {
        model.put("orderDetailsId", orderDetailsId);
        model.put("authorName", authorName);
        return "reviews";
    }

    @PostMapping(value = "/addReviews")
    public String addReviews(@AuthenticationPrincipal UsersEntity user,
                             @RequestParam Integer orderDetailsId,
                             @RequestParam String authorName,
                             @RequestParam Integer ratingFromClient,
                             @RequestParam String reviewFromClient) {
        OrderDetailsEntity order = orderDetailsRepository.findByOrderDetailsId(orderDetailsId);
        order.setReviewWritten(true);
        orderDetailsRepository.save(order);

        CouriersEntity courier = couriersRepository.findByCourierId(order.getCourier().getCourierId());
        couriersRating.setCourierRating(courier, ratingFromClient);

        ReviewsEntity review = new ReviewsEntity();
        review.setOrderId(orderDetailsId);
        review.setClientName(authorName);
        review.setRating(ratingFromClient);
        review.setReview(reviewFromClient);
        review.setAuthor(user);
        reviewsRepository.save(review);

        return "redirect:/orderDelivery";
    }
}
