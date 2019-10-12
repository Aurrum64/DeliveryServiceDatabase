package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderSpecificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_specification_id")
    private Integer orderSpecificationId;

    @Column(name = "courier_found")
    private Boolean courierFound;

    public boolean isCourierFound() {
        return courierFound;
    }

    @Column(name = "courier_went")
    private Boolean courierWent;

    public boolean isCourierWent() {
        return courierWent;
    }

    @Column(name = "order_picked_up")
    private Boolean orderPickedUp;

    public boolean isOrderPickedUp() {
        return orderPickedUp;
    }

    @Column(name = "order_delivered")
    private Boolean orderDelivered;

    public boolean isOrderDelivered() {
        return orderDelivered;
    }

    @Column(name = "order_confirmed")
    private Boolean orderConfirmed;

    public boolean isOrderConfirmed() {
        return orderConfirmed;
    }

    @Column(name = "route_blocked")
    private Boolean routeBlocked;

    public boolean isRouteBlocked() {
        return routeBlocked;
    }
}
