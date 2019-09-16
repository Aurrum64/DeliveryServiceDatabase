package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;

import java.util.Date;

@Getter
public class CouriersMessage {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer rating;
    private Integer salary;
    private Date hireDate;
    private Integer premium;
    private Integer departmentId;
    private Double latitude;
    private Double longitude;
}
