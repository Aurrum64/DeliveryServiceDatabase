package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersRequestsEntity;

public interface UsersRequestsRepository extends CrudRepository<UsersRequestsEntity, Integer> {

    UsersRequestsEntity findByRequestId(Integer id);
}
