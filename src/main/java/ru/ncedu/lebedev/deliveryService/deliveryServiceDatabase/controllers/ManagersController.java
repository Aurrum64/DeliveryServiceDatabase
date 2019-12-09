package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services.ManagersService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ManagersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ManagersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ManagersController {

    private ManagersRepository managersRepository;
    private ManagersService managersService;

    @Autowired
    public ManagersController(ManagersRepository managersRepository, ManagersService managersService) {
        this.managersRepository = managersRepository;
        this.managersService = managersService;
    }

    @GetMapping("/managers")
    public String managersView(Map<String, Object> model) {
        managersService.initializer(model);
        return "managers";
    }

    @PostMapping("/addManagers")
    public String addManager(@AuthenticationPrincipal UsersEntity user,
                             @RequestParam String firstName,
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
        manager.setAuthor(user);
        managersRepository.save(manager);
        return "redirect:/managers";
    }

    @PostMapping("/searchManagers")
    public String findManager(@RequestParam(required = false) Integer managerId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              Map<String, Object> model) {
        Iterable<ManagersEntity> managers = managersService.search(managerId, firstName, lastName);
        if (!managers.iterator().hasNext()) {
            model.put("filterCheck", "No manager with such index!");
            return "managers";
        } else {
            model.put("managers", managers);
        }
        return "managers";
    }

    @Transactional
    @PostMapping("/deleteManagers")
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

    @Transactional
    @PostMapping("/updateManagers")
    public String updateManager(@RequestParam Integer managerId,
                                @RequestParam(required = false) String firstName,
                                @RequestParam(required = false) String lastName,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String phoneNumber,
                                @RequestParam(required = false) Integer salary,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "dd-mm-yyyy") Date hireDate,
                                @RequestParam(required = false) Integer premium,
                                Map<String, Object> model) {
        List<ManagersEntity> manager = managersRepository.findByManagerId(managerId);
        if (manager.isEmpty()) {
            model.put("updateIdCheck", "Manager with such index does not exist!");
            return "managers";
        } else {
            if (!firstName.isEmpty()) {
                managersRepository.setFirstNameFor(firstName, managerId);
            }
            if (!lastName.isEmpty()) {
                managersRepository.setLastNameFor(lastName, managerId);
            }
            if (!email.isEmpty()) {
                managersRepository.setEmailFor(email, managerId);
            }
            if (!phoneNumber.isEmpty()) {
                managersRepository.setPhoneNumberFor(phoneNumber, managerId);
            }
            if (salary != null) {
                managersRepository.setSalaryFor(salary, managerId);
            }
            if (hireDate != null) {
                managersRepository.setHireDateFor(hireDate, managerId);
            }
            if (premium != null) {
                managersRepository.setPremiumFor(premium, managerId);
            }
        }
        return "redirect:/managers";
    }
}
