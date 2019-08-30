package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.ManagersEntity;

import java.util.List;

public interface ManagersRepository extends CrudRepository<ManagersEntity, Integer> {

    List<ManagersEntity> findByManagerId(Integer id);

    void deleteByManagerId(Integer id);
}
