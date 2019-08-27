package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Couriers;

import java.util.List;

public interface CouriersRepository extends CrudRepository<Couriers, Integer> {

    void deleteByCourierId(Integer id);

    List<Couriers> findByCourierId(Integer id);
}
