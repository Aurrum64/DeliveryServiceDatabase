package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Couriers;

import java.util.List;

public interface CouriersRepository extends CrudRepository<Couriers, Integer> {

    void deleteByCourierId(Integer id);

    List<Couriers> findByCourierId(Integer id);

    @Modifying
    @Query("update Couriers c set c.firstName = ?1 where c.courierId = ?2")
    int setFirstnameFor(String firstName, Integer id);
}
