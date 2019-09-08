package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ManagersEntity;

import java.util.Date;
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

    @Modifying
    @Query("update ManagersEntity m set m.firstName = ?1 where m.managerId = ?2")
    void setFirstNameFor(String firstName, Integer id);

    @Modifying
    @Query("update ManagersEntity m set m.lastName = ?1 where m.managerId = ?2")
    void setLastNameFor(String lastName, Integer id);

    @Modifying
    @Query("update ManagersEntity m set m.email = ?1 where m.managerId = ?2")
    void setEmailFor(String email, Integer id);

    @Modifying
    @Query("update ManagersEntity m set m.phoneNumber = ?1 where m.managerId = ?2")
    void setPhoneNumberFor(String phoneNumber, Integer id);

    @Modifying
    @Query("update ManagersEntity m set m.salary = ?1 where m.managerId = ?2")
    void setSalaryFor(Integer salary, Integer id);

    @Modifying
    @Query("update ManagersEntity m set m.hireDate = ?1 where m.managerId = ?2")
    void setHireDateFor(Date hireDate, Integer id);

    @Modifying
    @Query("update ManagersEntity m set m.premium = ?1 where m.managerId = ?2")
    void setPremiumFor(Integer premium, Integer id);
}
