package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetailsEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private OrdersEntity ordersEntity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private Integer orderDetailsId;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "order_address", nullable = false)
    private String orderAddress;

    @Column(name = "comment")
    private String comment;
}