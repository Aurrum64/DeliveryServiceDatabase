package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ClientsEntity;

import java.util.List;

public interface ClientsRepository extends CrudRepository<ClientsEntity, Integer> {
    List<ClientsEntity> findBySurname(String surname);
    List<ClientsEntity> findByClientId(Integer id);
    void deleteByClientId(Integer id);
    @Modifying
    @Query("update ClientsEntity c set c.name = ?1 where c.clientId = ?2")
    void setNameFor(String name, Integer id);
    @Modifying
    @Query("update ClientsEntity c set c.surname = ?1 where c.clientId = ?2")
    void setSurnameFor(String surname, Integer id);
    @Modifying
    @Query("update ClientsEntity c set c.email = ?1 where c.clientId = ?2")
    void setEmailFor(String email, Integer id);
    @Modifying
    @Query("update ClientsEntity c set c.telephone = ?1 where c.clientId = ?2")
    void setTelephoneFor(String telephone, Integer id);
    @Modifying
    @Query("update ClientsEntity c set c.rating = ?1 where c.clientId = ?2")
    void setRatingFor(Integer rating, Integer id);
    @Modifying
    @Query("update ClientsEntity c set c.address = ?1 where c.clientId = ?2")
    void setAddressFor(String address, Integer id);


}
