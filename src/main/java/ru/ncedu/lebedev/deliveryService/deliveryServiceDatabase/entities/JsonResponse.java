package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResponse {

    private String status = "";
    private String errorMessage = "";

    public JsonResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
