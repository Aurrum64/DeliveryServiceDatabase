package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private boolean active;

    @ElementCollection(targetClass = RolesEntity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RolesEntity> roles;
}
