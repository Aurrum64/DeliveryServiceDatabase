package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "couriers")
@Data
public class CouriersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private Integer courierId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UsersEntity author;

    public String getAuthorName() {
        return author.getUsername();
    }

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

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "readiness")
    private boolean readiness;

    @Column(name = "fired")
    private boolean fired;
}
