package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class OrderDetailsMessage {

    private Integer orderDetailsId;
    private Date orderDate;
    private String orderAddress;
    private String comment;
}
