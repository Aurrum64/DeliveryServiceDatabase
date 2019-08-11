package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Delivery_Info")
public class DeliveryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Delivery_ID")
    private Integer deliveryId;

    @OneToOne
    @JoinColumn(name = "Orders_ID")
    private Orders order;

/*    @OneToOne
    private Orders delivery;*/

/*    @OneToOne(mappedBy = "Orders")
    private Orders owner;*/

/*    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Orders_ID")
    private Orders orders;*/

    @Temporal(TemporalType.DATE)
    @Column(name = "Delivery_Date", nullable = false)
    private Date deliveryDate;

    @Column(name = "Delivery_Address", nullable = false)
    private String deliveryAddress;

    @Column(name = "Comment")
    private String comment;

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
