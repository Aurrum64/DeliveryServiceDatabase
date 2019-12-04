package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services.SearchService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ManagersEntity;

import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/search")
    String searchInTable(@RequestParam(required = true) String table, Map<String, Object> model){
        searchService.initializer(table, model);

        return "searchFilter";
    }

    @PostMapping("/searchFilter")
    String searchFilter(@RequestParam(required = true) Integer id,
                        @RequestParam(required = false) String secondParam,
                        @RequestParam(required = false) String thirdParam,Map<String, Object> model){
        searchService.search(id,secondParam,thirdParam,model);
        return "searchResult";
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
