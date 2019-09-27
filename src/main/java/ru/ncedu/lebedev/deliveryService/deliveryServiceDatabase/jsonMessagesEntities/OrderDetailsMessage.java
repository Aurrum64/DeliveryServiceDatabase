package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class OrderDetailsMessage {

    private Integer orderDetailsId;
    private Date orderDate;
    private String firstOrderAddressPoint;
    private String secondOrderAddressPoint;
    private String comment;
}
