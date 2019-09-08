package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Clients;

import java.util.List;

public interface ClientsRepository extends CrudRepository<Clients, Integer> {
    List<Clients> findBySurname(String surname);
}
