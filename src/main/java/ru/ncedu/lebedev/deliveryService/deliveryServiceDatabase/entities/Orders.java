package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_ID")
    private Integer orderId;

/*    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Delivery_ID")
    private DeliveryInfo deliveryInfo;*/

/*    @OneToOne(mappedBy = "delivery")
    private Delivery delivery;*/

/*    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Delivery_ID")
    private Delivery delivery;*/

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
