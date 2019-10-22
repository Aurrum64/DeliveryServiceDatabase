package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class UsersEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String imageUrl;
    private String emailVerification;
    private String password;
    private String activationCode;
    private boolean active;
    private boolean verified;
    private String filename;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;
    @ElementCollection(targetClass = RolesEntity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RolesEntity> roles;

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

    public boolean isAccountActivated() {
        return isVerified();
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
