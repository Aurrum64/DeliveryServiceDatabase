package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ClientsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ClientsRepository;

import java.util.List;
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
        Iterable<ClientsEntity> clients = clientsRepository.findAll();
        model.put("clients", clients);
        return "clients";
    }

    @PostMapping("/clients")
    public String add(@RequestParam  String name,
                      @RequestParam String surname,
                      @RequestParam(required = false) String email,
                      @RequestParam String telephone,
                      @RequestParam(required = false) Integer rating,
                      @RequestParam String address) {
        ClientsEntity clientsEntity = new ClientsEntity();
        clientsEntity.setName(name);
        clientsEntity.setSurname(surname);
        clientsEntity.setEmail(email);
        clientsEntity.setTelephone(telephone);
        clientsEntity.setRating(rating);
        clientsEntity.setAddress(address);
        clientsRepository.save(clientsEntity);
        return "redirect:/clients";
    }

    @PostMapping("/clientsFilter")
    public String findBySurname(@RequestParam String surname, Map<String, Object> model) {
        Iterable<ClientsEntity> clients;
        if (!surname.isEmpty()) {
            clients = clientsRepository.findBySurname(surname);
        }
        else {
            clients = clientsRepository.findAll();
        }
        if (!clients.iterator().hasNext()) {
            model.put("filterCheck", "No clients with such surname!");
            return "clients";
        } else {
            model.put("clients", clients);
        }
        return "clients";
    }
    @Transactional
    @PostMapping("/clientsDelete")
    public String deleteClients(@RequestParam Integer clientId, Map<String, Object> model) {
        List<ClientsEntity> clients = clientsRepository.findByClientId(clientId);
        if (clients.isEmpty()) {
            model.put("deleteIdCheck", "No client with such index!");
            return "clients";
        } else {
            clientsRepository.deleteByClientId(clientId);
        }
        return "redirect:/clients";
    }
    @Transactional
    @PostMapping("/clientsUpdate")
    public String updateClients(@RequestParam Integer clientId,
                                @RequestParam (required = false) String name,
                                @RequestParam (required = false) String surname,
                                @RequestParam (required = false) String email,
                                @RequestParam (required = false) String telephone,
                                @RequestParam (required = false) Integer rating,
                                @RequestParam (required = false) String address,
                                   Map<String, Object> model) {
        List<ClientsEntity> clients = clientsRepository.findByClientId(clientId);
        if (clients.isEmpty()) {
            model.put("updateIdCheck", "Client with such index does not exist!");
            return "clients";
        } else {
            if (!name.isEmpty()) {
                clientsRepository.setNameFor(name, clientId);
            }
            if (!surname.isEmpty()) {
                clientsRepository.setSurnameFor(surname,clientId);
            }
            if (!email.isEmpty()) {
               clientsRepository.setEmailFor(email,clientId);
            }
            if (!telephone.isEmpty()) {
                clientsRepository.setTelephoneFor(telephone,clientId);
            }
            if (rating!=null) {
                clientsRepository.setRatingFor(rating,clientId);
            }
            if (!address.isEmpty()) {
                clientsRepository.setAddressFor(address,clientId);
            }
        }
        return "redirect:/clients";
    }
}
