package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.LocationsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.LocationsEntity;

import java.util.Map;

@Service
public class LocationService {
    private LocationsRepository locationsRepository;

    @Autowired
    public LocationService(LocationsRepository locationsRepository){
        this.locationsRepository = locationsRepository;
    }

    public void initializer(Map<String, Object> model){
        Iterable<LocationsEntity> locations = locationsRepository.findAll();
        model.put("locations", locations);
    }

    public Iterable<LocationsEntity> search(Integer locationId, String street, Integer building){
        Iterable<LocationsEntity> locations;
        if (locationId != null & street.isEmpty() & building == null) {
            locations = locationsRepository.findByLocationId(locationId);
        } else if (locationId == null & !street.isEmpty() & building != null) {
            locations = locationsRepository.findByStreetAndBuilding(street, building);
        } else if (locationId == null & !street.isEmpty() & building == null) {
            locations = locationsRepository.findByStreet(street);
        } else if (locationId != null & !street.isEmpty() & building == null) {
            locations = locationsRepository.findByLocationIdAndStreet(locationId, street);
        } else if (locationId != null & street.isEmpty() & building != null) {
            locations = locationsRepository.findByLocationIdAndBuilding(locationId, building);
        } else if (locationId == null & street.isEmpty() & building != null) {
            locations = locationsRepository.findByBuilding(building);
        } else if (locationId != null & !street.isEmpty() & building != null) {
            locations = locationsRepository.findByLocationIdAndStreetAndBuilding(locationId, street, building);
        } else {
            locations = locationsRepository.findAll();
        }
        return locations;
    }
}
