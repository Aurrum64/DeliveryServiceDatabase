package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrdersEntity;

public interface OrdersRepository extends CrudRepository<OrdersEntity, Integer> {
}
