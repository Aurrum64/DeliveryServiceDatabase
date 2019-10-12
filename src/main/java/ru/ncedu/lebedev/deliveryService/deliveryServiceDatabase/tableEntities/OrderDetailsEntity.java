package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;
import org.springframework.stereotype.Controller;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrdersEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private Integer orderDetailsId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UsersEntity author;

    public String getAuthorName() {
        return author.getUsername();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courier_id")
    private CouriersEntity courier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specification_id")
    private OrderSpecificationEntity orderSpecification;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "fist_order_address_point", nullable = false)
    private String firstOrderAddressPoint;

    @Column(name = "second_order_address_point", nullable = false)
    private String secondOrderAddressPoint;

    @Column(name = "comment")
    private String comment;

    @Column(name = "delivery_status")
    private String status;

    @Column(name = "review_written")
    private Boolean reviewWritten;

    public boolean isReviewWritten() {
        return reviewWritten;
    }

    @Column(name = "already_in_progress")
    private Boolean alreadyInProgress;

    public boolean isAlreadyInProgress() {
        return alreadyInProgress;
    }
}