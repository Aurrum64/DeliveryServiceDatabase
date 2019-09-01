package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrdersEntity {

    //Это связь Many to One с таблицей Couriers
    //http://javastudy.ru/hibernate/hibernate-one-to-many/ пример брал отсюда, завести не удалось
    //Можете попробовать еще над этим поколдовать

/*    private Couriers couriers;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    public Couriers getCourier() {
        return this.couriers;
    }

    public void setCourier(Couriers couriers) {
        this.couriers = couriers;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Column(name = "manager_id", nullable = false)
    private Integer managerId;

    @Column(name = "courier_id", nullable = false)
    private Integer courierId;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "order_price", nullable = false)
    private Integer orderPrice;

    @Column(name = "discount")
    private Integer discount;
}
