package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.ManagersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ManagersRepository;

import java.util.Map;

@Controller
public class ManagersController {

    private ManagersRepository managersRepository;

    @Autowired
    public ManagersController(ManagersRepository managersRepository) {
        this.managersRepository = managersRepository;
    }

    @GetMapping("/managers")
    public String couriersView(Map<String, Object> model) {
        Iterable<ManagersEntity> managers = managersRepository.findAll();
        model.put("managers", managers);
        return "managers";
    }
}
