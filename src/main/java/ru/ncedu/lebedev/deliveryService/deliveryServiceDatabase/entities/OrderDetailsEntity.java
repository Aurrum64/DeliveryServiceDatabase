package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private Integer id;

/*    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;*/

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "comment")
    private String comment;
}
