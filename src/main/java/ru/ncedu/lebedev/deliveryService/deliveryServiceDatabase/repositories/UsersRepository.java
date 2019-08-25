package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findByUsername(String username);
}
