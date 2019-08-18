package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "couriers")
@Data
public class Couriers implements Serializable {

    //Это свзять One To Many с таблицой Orders
    //http://javastudy.ru/hibernate/hibernate-one-to-many/ пример брал отсюда, завести не удалось
    //Можете попробовать еще над этим поколдовать

/*    private Set<Orders> orders = new HashSet<>();

    @OneToMany(mappedBy = "couriers", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Orders> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private Integer courierId;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "e_mail")
    private String email;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "salary")
    private Integer salary;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "premium")
    private Integer premium;
}
