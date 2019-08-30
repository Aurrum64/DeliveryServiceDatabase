package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Couriers;

import java.util.Date;
import java.util.List;

public interface CouriersRepository extends CrudRepository<Couriers, Integer> {

    List<Couriers> findByCourierId(Integer id);

    List<Couriers> findByCourierIdAndFirstName(Integer id, String firstName);

    List<Couriers> findByCourierIdAndLastName(Integer id, String lastName);

    List<Couriers> findByFirstName(String firstName);

    List<Couriers> findByLastName(String lastName);

    List<Couriers> findByFirstNameAndLastName(String firstName, String lastName);

    List<Couriers> findByCourierIdAndFirstNameAndLastName(Integer id, String firstName, String lastName);

    void deleteByCourierId(Integer id);

    @Modifying
    @Query("update Couriers c set c.firstName = ?1 where c.courierId = ?2")
    void setFirstNameFor(String firstName, Integer id);

    @Modifying
    @Query("update Couriers c set c.lastName = ?1 where c.courierId = ?2")
    void setLastNameFor(String lastName, Integer id);

    @Modifying
    @Query("update Couriers c set c.email = ?1 where c.courierId = ?2")
    void setEmailFor(String email, Integer id);

    @Modifying
    @Query("update Couriers c set c.phoneNumber = ?1 where c.courierId = ?2")
    void setPhoneNumberFor(String phoneNumber, Integer id);

    @Modifying
    @Query("update Couriers c set c.rating = ?1 where c.courierId = ?2")
    void setRatingFor(Integer rating, Integer id);

    @Modifying
    @Query("update Couriers c set c.salary = ?1 where c.courierId = ?2")
    void setSalaryFor(Integer salary, Integer id);

    @Modifying
    @Query("update Couriers c set c.hireDate = ?1 where c.courierId = ?2")
    void setHireDateFor(Date hireDate, Integer id);

    @Modifying
    @Query("update Couriers c set c.premium = ?1 where c.courierId = ?2")
    void setPremiumFor(Integer premium, Integer id);

    @Modifying
    @Query("update Couriers c set c.departmentId = ?1 where c.courierId = ?2")
    void setDepartmentFor(Integer departmentId, Integer id);
}
