package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class UsersEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String emailVerification;
    private String password;
    private String activationCode;
    private boolean active;

    @ElementCollection(targetClass = RolesEntity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RolesEntity> roles;

    public boolean isAccountActivated() {
        return emailVerification.equals("Подтверждена");
    }

    public boolean isAdmin() {
        return roles.contains(RolesEntity.ADMIN);
    }

    public boolean isUser() {
        return roles.contains(RolesEntity.USER);
    }

    public boolean isManager() {
        return roles.contains(RolesEntity.MANAGER);
    }

    public boolean isCourier() {
        return roles.contains(RolesEntity.COURIER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
