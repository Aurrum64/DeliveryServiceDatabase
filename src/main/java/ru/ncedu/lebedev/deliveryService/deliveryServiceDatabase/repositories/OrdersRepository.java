package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrdersEntity;
import java.util.List;

public interface OrdersRepository extends CrudRepository<OrdersEntity, Integer> {
    List<OrdersEntity> findByPaymentMethod(String orderPrice);
}
