package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;

@Getter
public class ReviewMessage {

    private Integer orderId;
    private String clientName;
    private Integer rating;
    private String reviewSubject;
    private String review;
}