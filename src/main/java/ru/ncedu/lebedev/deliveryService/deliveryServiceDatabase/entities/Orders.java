package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ordersId;

    @Column(name = "Department_ID", nullable = false)
    private Integer departmentId;

    @Column(name = "Manager_ID", nullable = false)
    private Integer managerId;

    @Column(name = "Courier_ID", nullable = false)
    private Integer courierId;

    @Column(name = "Payment_method")
    private String paymentMethod;

    @Column(name = "Order_price", nullable = false)
    private Integer orderPrice;

    @Column(name = "Discount")
    private Integer discount;

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
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
