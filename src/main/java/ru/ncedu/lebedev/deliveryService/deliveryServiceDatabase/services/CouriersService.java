package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import java.util.Map;

@Service
public class CouriersService {
    private CouriersRepository couriersRepository;

    @Autowired
    public CouriersService(CouriersRepository couriersRepository){
        this.couriersRepository = couriersRepository;
    }

    public void initializer(Map<String, Object> model){
        Iterable<CouriersEntity> couriers = couriersRepository.findAll();
        model.put("couriers", couriers);
    }

    public Iterable<CouriersEntity> search(Integer courierId, String firstName, String lastName){
        Iterable<CouriersEntity> couriers;
        if (courierId != null & firstName.isEmpty() & lastName.isEmpty()) {
            couriers = couriersRepository.findByCourierId(courierId);
        } else if (courierId == null & !firstName.isEmpty() & !lastName.isEmpty()) {
            couriers = couriersRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (courierId == null & !firstName.isEmpty() & lastName.isEmpty()) {
            couriers = couriersRepository.findByFirstName(firstName);
        } else if (courierId != null & !firstName.isEmpty() & lastName.isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndFirstName(courierId, firstName);
        } else if (courierId != null & firstName.isEmpty() & !lastName.isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndLastName(courierId, lastName);
        } else if (courierId == null & firstName.isEmpty() & !lastName.isEmpty()) {
            couriers = couriersRepository.findByLastName(lastName);
        } else if (courierId != null & !firstName.isEmpty() & !lastName.isEmpty()) {
            couriers = couriersRepository.findByCourierIdAndFirstNameAndLastName(courierId, firstName, lastName);
        } else {
            couriers = couriersRepository.findAll();
        }
        return couriers;
    }
}
