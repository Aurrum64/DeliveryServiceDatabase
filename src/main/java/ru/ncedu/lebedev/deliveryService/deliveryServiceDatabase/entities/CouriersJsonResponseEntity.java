package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouriersJsonResponseEntity {

    private String msg;
    private Iterable<CouriersEntity> result;
}
