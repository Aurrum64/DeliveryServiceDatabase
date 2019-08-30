package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.ManagersEntity;

public interface ManagersRepository extends CrudRepository<ManagersEntity, Integer> {
}
