package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsJsonResponseEntity {

    String msg;
    Iterable<OrderDetailsEntity> result;
}
