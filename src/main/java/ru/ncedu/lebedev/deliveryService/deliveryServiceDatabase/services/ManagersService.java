package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ManagersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ManagersEntity;

import java.util.Map;

@Service
public class ManagersService {
    @Autowired
    private ManagersRepository managersRepository;

    public Iterable<ManagersEntity> search(Integer managerId, String firstName, String lastName){
        Iterable<ManagersEntity> managers;
        if (managerId != null & firstName.isEmpty() & lastName.isEmpty()) {
            managers = managersRepository.findByManagerId(managerId);
        } else if (managerId == null & !firstName.isEmpty() & !lastName.isEmpty()) {
            managers = managersRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (managerId == null & !firstName.isEmpty() & lastName.isEmpty()) {
            managers = managersRepository.findByFirstName(firstName);
        } else if (managerId != null & !firstName.isEmpty() & lastName.isEmpty()) {
            managers = managersRepository.findByManagerIdAndFirstName(managerId, firstName);
        } else if (managerId != null & firstName.isEmpty() & !lastName.isEmpty()) {
            managers = managersRepository.findByManagerIdAndLastName(managerId, lastName);
        } else if (managerId == null & firstName.isEmpty() & !lastName.isEmpty()) {
            managers = managersRepository.findByLastName(lastName);
        } else if (managerId != null & !firstName.isEmpty() & !lastName.isEmpty()) {
            managers = managersRepository.findByManagerIdAndFirstNameAndLastName(managerId, firstName, lastName);
        } else {
            managers = managersRepository.findAll();
        }
        return managers;
    }

    public void initializer(Map<String, Object> model){
        Iterable<ManagersEntity> managers = managersRepository.findAll();
        model.put("managers", managers);
    }
}
