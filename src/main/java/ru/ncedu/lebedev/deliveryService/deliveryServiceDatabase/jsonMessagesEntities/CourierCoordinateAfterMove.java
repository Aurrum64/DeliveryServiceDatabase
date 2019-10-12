package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;

@Getter
public class CourierCoordinateAfterMove {

    private Integer courierId;
    private Integer orderId;
    private Double lat;
    private Double lng;
    private Double firstOrderPointLat;
    private Double firstOrderPointLng;
    private Double secondOrderPointLat;
    private Double secondOrderPointLng;
}
