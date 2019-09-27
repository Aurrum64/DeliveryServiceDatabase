package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users_requests")
@Data
public class UsersRequestsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @ElementCollection(targetClass = RequestsStatutesEntity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "request_status", joinColumns = @JoinColumn(name = "request_id"))
    @Enumerated(EnumType.STRING)
    private Set<RequestsStatutesEntity> requestStatuses;

    public boolean isApproved() {
        return requestStatuses.contains(RequestsStatutesEntity.APPROVED);
    }

    public boolean isRejected() {
        return requestStatuses.contains(RequestsStatutesEntity.REJECTED);
    }

    public boolean isConsidering() {
        return requestStatuses.contains(RequestsStatutesEntity.CONSIDERATION);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UsersEntity author;

    public String getAuthorName() {
        return author.getUsername();
    }

    @Column(name = "courier_request")
    private boolean courierRequest;

    @Column(name = "manager_request")
    private boolean managerRequest;
}
