package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.Clients;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ClientsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;

import java.util.Date;
import java.util.Map;

@Controller
public class ClientsController {

    private ClientsRepository clientsRepository;

    @Autowired
    public ClientsController(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @GetMapping("/clients")
    public String ClientsView(Map<String, Object> model) {
        Iterable<Clients> clients = clientsRepository.findAll();
        model.put("clients", clients);
        return "clients";
    }

    @PostMapping("/clients")
    public String add(@RequestParam  String name,
                      @RequestParam String surname,
                      @RequestParam(required = false) String email,
                      @RequestParam String telephone,
                      @RequestParam(required = false) String rating,
                      @RequestParam String address) {
        Clients clients = new Clients();
        clients.setName(name);
        clients.setSurname(surname);
        clients.setEmail(email);
        clients.setTelephone(telephone);
        clients.setRating(rating);
        clients.setAddress(address);
        clientsRepository.save(clients);
        return "redirect:/clients";
    }

    @PostMapping("/clientsFilter")
    public String findByName(@RequestParam String surname, Map<String, Object> model) {
        Iterable<Clients> clients;
        if (surname != null && !surname.isEmpty()) {
            clients = clientsRepository.findBySurname(surname);
        } else {
            clients = clientsRepository.findAll();
        }
        model.put("clients", clients);
        return "clients";
    }
}
