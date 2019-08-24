package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrderDetailsEntity;

import java.util.List;

public interface OrderDetailsRepository extends CrudRepository<OrderDetailsEntity, Integer> {

    List<OrderDetailsEntity> findByOrderAddress(String orderAddress);
}
