package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ProductsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Boolean existsByEmail(String email);
    Optional<UsersEntity> findByEmail(String email);
    UsersEntity findByUsername(String username);
    UsersEntity findByActivationCode(String code);
}
