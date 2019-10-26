package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private ManagersController mc;

    @PostMapping("/search")
    String searchInTable(@RequestParam(required = true) String table, Map<String, Object> model){
        String placeholder1 = "Найти по Id...";
        String placeholder2 = "";
        String placeholder3 = "";

        switch(table){
            case "products":
                placeholder2 = "Найти по названию...";
                placeholder3 = "Найти по цене...";
                break;
            case "orderdetails":
                placeholder2 = "Найти по дате...";
                placeholder3 = "Найти по адресу...";
                break;
            case "managers":
            case "couriers":
                placeholder2 = "Найти по имени...";
                placeholder3 = "Найти по фамилии...";
                break;
            case "locations":
                placeholder2 = "Найти по улице...";
                placeholder3 = "Найти по номеру дома...";
                break;
        }
        model.put("placeholder1", placeholder1);
        model.put("placeholder2", placeholder2);
        model.put("placeholder3", placeholder3);

        System.out.println(table);
        System.out.println(placeholder2);
        System.out.println(placeholder3);
        return "search1";
    }

    @PostMapping("/searchFilter")
    String searchFilter(){

        return "search1";
    }

    @GetMapping("/search")
    String searchPage(){
        return "search";
    }
    /*String search(Map<String, Object> model){
        System.out.println(mc.findManager(1,"a","s", model));
        System.out.println(model.toString());
        return "managers";
    }*/
}
