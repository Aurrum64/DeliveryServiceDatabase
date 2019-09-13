package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.LocationsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.LocationsRepository;


import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class LocationsController {
    private LocationsRepository locationsRepository;

    @Autowired
    LocationsController(LocationsRepository locationsRepository){this.locationsRepository = locationsRepository;};

    @GetMapping("/locations")
    String locationsView(Map<String, Object> model){
        Iterable<LocationsEntity> locations = locationsRepository.findAll();
        model.put("locations", locations);
        return "locations";
    }
    
    @PostMapping("/locationsFilter")
    public String findCourier(@RequestParam(required = false) Integer locationId,
                              @RequestParam(required = false) String street,
                              @RequestParam(required = false) Integer building,
                              Map<String, Object> model) {
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
        if (!locations.iterator().hasNext()) {
            model.put("filterCheck", "No location with such index!");
            return "locations";
        } else {
            model.put("locations", locations);
        }
        return "locations";
    }

    @Transactional
    @PostMapping("/locationsDelete")
    public String deleteLocation(@RequestParam Integer locationId, Map<String, Object> model) {
        List<LocationsEntity> location = locationsRepository.findByLocationId(locationId);
        if (location.isEmpty()) {
            model.put("deleteIdCheck", "No location with such index!");
            return "locations";
        } else {
            locationsRepository.deleteByLocationId(locationId);
        }
        return "redirect:/locations";
    }

    @Transactional
    @PostMapping("/locationsUpdate")
    public String updateCourier(@RequestParam Integer locationId,
                                @RequestParam(required = false) String street,
                                @RequestParam(required = false) Integer building,
                                @RequestParam(required = false) String district,
                                @RequestParam(required = false) String city,
                                Map<String, Object> model) {
        List<LocationsEntity> location = locationsRepository.findByLocationId(locationId);
        if (location.isEmpty()) {
            model.put("updateIdCheck", "Location with such index does not exist!");
            return "locations";
        } else {
            if (!street.isEmpty()) {
                locationsRepository.setStreetFor(street, locationId);
            }
            if (building != null) {
                locationsRepository.setBuildingFor(building, locationId);
            }
            if (!district.isEmpty()) {
                locationsRepository.setDistrictFor(district, locationId);
            }
            if (!city.isEmpty()) {
                locationsRepository.setCityFor(city, locationId);
            }
        }
        return "redirect:/locations";
    }

    @PostMapping("/locations")
    public String addLocation(@RequestParam String street,
                              @RequestParam Integer building,
                              @RequestParam(required = false) String district,
                              @RequestParam(required = false, defaultValue = "Moscow") String city){
        LocationsEntity location = new LocationsEntity();
        location.setStreet(street);
        location.setBuilding(building);
        location.setDistrict(district);
        location.setCity(city);
        locationsRepository.save(location);
        return "redirect:/locations";
    }
}