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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UsersEntity author;

    public String getAuthorName() {
        return author.getUsername();
    }

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review")
    private String review;
}
