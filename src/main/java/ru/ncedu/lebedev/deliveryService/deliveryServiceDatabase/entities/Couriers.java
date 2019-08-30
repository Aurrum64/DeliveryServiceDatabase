package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "couriers")
@Data
public class Couriers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private Integer courierId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "e_mail")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "salary")
    private Integer salary;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "premium")
    private Integer premium;

    @Column(name = "department_id")
    private Integer departmentId;
}
