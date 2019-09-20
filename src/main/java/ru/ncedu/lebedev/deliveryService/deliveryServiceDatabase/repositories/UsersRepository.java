package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findByUsername(String username);

    UsersEntity findByActivationCode(String code);
}
