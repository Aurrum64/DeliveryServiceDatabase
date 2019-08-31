package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.ManagersEntity;

import java.util.List;

public interface ManagersRepository extends CrudRepository<ManagersEntity, Integer> {

    List<ManagersEntity> findByManagerId(Integer id);

    List<ManagersEntity> findByManagerIdAndFirstName(Integer id, String firstName);

    List<ManagersEntity> findByManagerIdAndLastName(Integer id, String lastName);

    List<ManagersEntity> findByFirstName(String firstName);

    List<ManagersEntity> findByLastName(String lastName);

    List<ManagersEntity> findByFirstNameAndLastName(String firstName, String lastName);

    List<ManagersEntity> findByManagerIdAndFirstNameAndLastName(Integer id, String firstName, String lastName);

    void deleteByManagerId(Integer id);
}
