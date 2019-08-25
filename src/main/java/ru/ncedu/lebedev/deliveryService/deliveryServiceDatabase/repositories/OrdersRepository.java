package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Orders;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {
    List<Orders> findByPaymentMethod(String orderPrice);
}
