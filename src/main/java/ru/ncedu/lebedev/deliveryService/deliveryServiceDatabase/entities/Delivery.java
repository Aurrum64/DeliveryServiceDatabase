package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer deliveryId;

    @Temporal(TemporalType.DATE)
    @Column(name = "Delivery_Date", nullable = false)
    private Date deliveryDate;

    @Column(name = "Delivery_Address", nullable = false)
    private String deliveryAddress;

    @Column(name = "Comment")
    private String comment;

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
