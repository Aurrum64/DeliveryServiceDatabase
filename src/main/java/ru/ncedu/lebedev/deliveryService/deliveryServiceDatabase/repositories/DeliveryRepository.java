package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.DeliveryInfo;

public interface DeliveryRepository extends CrudRepository<DeliveryInfo, Integer> {
}
