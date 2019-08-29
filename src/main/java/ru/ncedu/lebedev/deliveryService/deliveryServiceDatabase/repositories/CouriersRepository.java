package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Couriers;

import java.util.Date;
import java.util.List;

public interface CouriersRepository extends CrudRepository<Couriers, Integer> {

    List<Couriers> findByCourierId(Integer id);

    List<Couriers> findByFirstName(String firstName);

    List<Couriers> findByLastName(String lastName);

    List<Couriers> findByFirstNameAndLastName(String firstName, String lastName);

    List<Couriers> findByCourierIdAndFirstNameAndLastName(Integer id, String firstName, String lastName);

    void deleteByCourierId(Integer id);

    @Modifying
    @Query("update Couriers c set c.firstName = ?1 where c.courierId = ?2")
    int setFirstNameFor(String firstName, Integer id);

    @Modifying
    @Query("update Couriers c set c.lastName = ?1 where c.courierId = ?2")
    int setLastNameFor(String lastName, Integer id);

    @Modifying
    @Query("update Couriers c set c.email = ?1 where c.courierId = ?2")
    int setEmailFor(String email, Integer id);

    @Modifying
    @Query("update Couriers c set c.phoneNumber = ?1 where c.courierId = ?2")
    int setPhoneNumberFor(String phoneNumber, Integer id);

    @Modifying
    @Query("update Couriers c set c.rating = ?1 where c.courierId = ?2")
    int setRatingFor(Integer rating, Integer id);

    @Modifying
    @Query("update Couriers c set c.salary = ?1 where c.courierId = ?2")
    int setSalaryFor(Integer salary, Integer id);

    @Modifying
    @Query("update Couriers c set c.hireDate = ?1 where c.courierId = ?2")
    int setHireDateFor(Date hireDate, Integer id);

    @Modifying
    @Query("update Couriers c set c.premium = ?1 where c.courierId = ?2")
    int setPremiumFor(Integer premium, Integer id);

    @Modifying
    @Query("update Couriers c set c.departmentId = ?1 where c.courierId = ?2")
    int setDepartmentFor(Integer departmentId, Integer id);
}
