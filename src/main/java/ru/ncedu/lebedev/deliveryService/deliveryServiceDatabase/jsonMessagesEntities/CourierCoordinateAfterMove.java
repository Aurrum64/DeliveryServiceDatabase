package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;

@Getter
public class CourierCoordinateAfterMove {

    private Integer courierId;
    private Double lat;
    private Double lng;
}
