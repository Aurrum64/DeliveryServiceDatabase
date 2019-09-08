package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControllerAnswerToAjax {

    private String status = "";
    private String errorMessage = "";

    public ControllerAnswerToAjax(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
