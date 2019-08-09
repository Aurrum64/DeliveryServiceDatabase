package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {
}
