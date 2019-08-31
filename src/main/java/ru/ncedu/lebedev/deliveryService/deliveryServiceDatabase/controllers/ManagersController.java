package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.ManagersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ManagersRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ManagersController {

    private ManagersRepository managersRepository;

    @Autowired
    public ManagersController(ManagersRepository managersRepository) {
        this.managersRepository = managersRepository;
    }

    @GetMapping("/managers")
    public String managersView(Map<String, Object> model) {
        Iterable<ManagersEntity> managers = managersRepository.findAll();
        model.put("managers", managers);
        return "managers";
    }

    @PostMapping("/managers")
    public String addManager(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false, defaultValue = "+7(000)-000-00-00") String phoneNumber,
                             @RequestParam(required = false, defaultValue = "0") Integer salary,
                             @RequestParam(required = false, defaultValue = "01-01-2000") @DateTimeFormat(pattern = "dd-mm-yyyy") Date hireDate,
                             @RequestParam(required = false, defaultValue = "0") Integer premium) {
        ManagersEntity manager = new ManagersEntity();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setEmail(email);
        manager.setPhoneNumber(phoneNumber);
        manager.setSalary(salary);
        manager.setHireDate(hireDate);
        manager.setPremium(premium);
        managersRepository.save(manager);
        return "redirect:/managers";
    }

    @PostMapping("/managersFilter")
    public String findCourier(@RequestParam(required = false) Integer managerId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              Map<String, Object> model) {
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
        if (!managers.iterator().hasNext()) {
            model.put("filterCheck", "No manager with such index!");
            return "managers";
        } else {
            model.put("managers", managers);
        }
        return "managers";
    }

    @Transactional
    @PostMapping("/managersDelete")
    public String deleteManager(@RequestParam Integer managerId, Map<String, Object> model) {
        List<ManagersEntity> manager = managersRepository.findByManagerId(managerId);
        if (manager.isEmpty()) {
            model.put("deleteIdCheck", "No manager with such index!");
            return "managers";
        } else {
            managersRepository.deleteByManagerId(managerId);
        }
        return "redirect:/managers";
    }
}
