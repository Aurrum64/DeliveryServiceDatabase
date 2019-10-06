package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ProductsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {


    UsersEntity findByUsername(String username);
    UsersEntity findByGoogleId(String googleId);
    UsersEntity findByActivationCode(String code);
}
