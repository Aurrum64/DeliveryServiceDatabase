package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CallCentre;


import java.util.List;

public interface CallCentreRepository extends CrudRepository<CallCentre, Integer> {
    List<CallCentre> findByName(String name);
    List<CallCentre> findByDepartmentId(Integer id);
    void deleteByDepartmentId(Integer id);

    @Modifying
    @Query("update CallCentre c set c.name = ?1 where c.departmentId = ?2")
    void setNameFor(String name, Integer id);

    @Modifying
    @Query("update CallCentre c set c.deliveryRegion = ?1 where c.departmentId = ?2")
    void setDeliveryRegionFor(String deliveryRegion, Integer id);

    @Modifying
    @Query("update CallCentre c set c.managerId = ?1 where c.departmentId = ?2")
    void setManagerIdFor(Integer managerId, Integer id);

    @Modifying
    @Query("update CallCentre c set c.locationId = ?1 where c.departmentId = ?2")
    void setLocationFor(Integer location, Integer id);

}
