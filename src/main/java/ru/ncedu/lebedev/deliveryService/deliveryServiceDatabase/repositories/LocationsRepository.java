package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.LocationsEntity;

import java.util.List;

public interface LocationsRepository extends CrudRepository<LocationsEntity, Integer> {

    List<LocationsEntity> findByLocationId(Integer id);
    List<LocationsEntity> findByBuilding(Integer building);
    List<LocationsEntity> findByStreet(String street);

    List<LocationsEntity> findByLocationIdAndStreet(Integer id, String Street);
    List<LocationsEntity> findByLocationIdAndBuilding(Integer id, Integer building);
    List<LocationsEntity> findByStreetAndBuilding(String Street, Integer building);

    List<LocationsEntity> findByLocationIdAndStreetAndBuilding(Integer id, String Street, Integer building);

    @Modifying
    @Query("update LocationsEntity l set l.city = ?1 where l.locationId = ?2")
    void setCityFor(String city, Integer id);

    @Modifying
    @Query("update LocationsEntity l set l.district = ?1 where l.locationId = ?2")
    void setDistrictFor(String district, Integer id);

    @Modifying
    @Query("update LocationsEntity l set l.street = ?1 where l.locationId = ?2")
    void setStreetFor(String street, Integer id);

    @Modifying
    @Query("update LocationsEntity l set l.building = ?1 where l.locationId = ?2")
    void setBuildingFor(Integer building, Integer id);


}
