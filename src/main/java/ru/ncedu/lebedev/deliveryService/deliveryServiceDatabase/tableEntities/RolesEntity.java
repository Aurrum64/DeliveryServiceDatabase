package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities;

import org.springframework.security.core.GrantedAuthority;

public enum RolesEntity implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
