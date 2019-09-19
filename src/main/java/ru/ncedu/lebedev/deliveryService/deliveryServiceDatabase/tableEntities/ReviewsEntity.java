package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Data
public class ReviewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "review_subject")
    private String reviewSubject;

    @Column(name = "review")
    private String review;
}
