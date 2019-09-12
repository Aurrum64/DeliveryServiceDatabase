package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Clients;

import java.util.List;

public interface ClientsRepository extends CrudRepository<Clients, Integer> {
    List<Clients> findBySurname(String surname);
    List<Clients> findByClientId(Integer id);
    void deleteByClientId(Integer id);
    @Modifying
    @Query("update Clients c set c.name = ?1 where c.clientId = ?2")
    void setNameFor(String name, Integer id);
    @Modifying
    @Query("update Clients c set c.surname = ?1 where c.clientId = ?2")
    void setSurnameFor(String surname, Integer id);
    @Modifying
    @Query("update Clients c set c.email = ?1 where c.clientId = ?2")
    void setEmailFor(String email, Integer id);
    @Modifying
    @Query("update Clients c set c.telephone = ?1 where c.clientId = ?2")
    void setTelephoneFor(String telephone, Integer id);
    @Modifying
    @Query("update Clients c set c.rating = ?1 where c.clientId = ?2")
    void setRatingFor(Integer rating, Integer id);
    @Modifying
    @Query("update Clients c set c.address = ?1 where c.clientId = ?2")
    void setAddressFor(String address, Integer id);


}
