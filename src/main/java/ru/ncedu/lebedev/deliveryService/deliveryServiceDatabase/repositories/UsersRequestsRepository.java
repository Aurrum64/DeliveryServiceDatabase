package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersRequestsEntity;

import java.util.List;

public interface UsersRequestsRepository extends CrudRepository<UsersRequestsEntity, Integer> {

    UsersRequestsEntity findByRequestId(Integer id);

    List<UsersRequestsEntity> findAll();
}
