package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.User;

public interface UsersRepository extends JpaRepository<User, String> {
}
