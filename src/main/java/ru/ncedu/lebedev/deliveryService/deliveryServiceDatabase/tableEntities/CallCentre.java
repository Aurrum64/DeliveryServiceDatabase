package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "call_centre")
@Data
public class CallCentre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "manager_id", nullable = false)
    private Integer managerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "delivery_region",nullable = false)
    private String deliveryRegion;

    @Column(name = "location_id", nullable = false)
    private Integer locationId;

}
