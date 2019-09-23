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

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "fist_order_address_point", nullable = false)
    private String firstOrderAddressPoint;

    @Column(name = "second_order_address_point", nullable = false)
    private String secondOrderAddressPoint;

    @Column(name = "comment")
    private String comment;

    @Column(name = "delivery_status")
    private String status;
}