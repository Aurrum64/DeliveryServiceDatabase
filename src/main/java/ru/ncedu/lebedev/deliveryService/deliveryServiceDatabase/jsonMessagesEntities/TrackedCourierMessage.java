package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;

@Getter
public class TrackedCourierMessage {

    private Integer courierId;
    private Integer orderDetailsId;
}