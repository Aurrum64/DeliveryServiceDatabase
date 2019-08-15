package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import javax.persistence.*;

@Entity
public class Orders {

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer ordersId) {
        this.orderId = ordersId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
