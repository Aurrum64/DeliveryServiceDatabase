package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Column(name = "manager_id", nullable = false)
    private Integer managerId;

    @Column(name = "courier_id", nullable = false)
    private Integer courierId;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "order_price", nullable = false)
    private Integer orderPrice;

    @Column(name = "discount")
    private Integer discount;
}
