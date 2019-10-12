package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderSpecificationEntity;

public interface OrderSpecificationsRepository extends CrudRepository<OrderSpecificationEntity, Integer> {

    OrderSpecificationEntity findByOrderSpecificationId(Integer id);
}