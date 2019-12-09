package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services.SearchService;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ManagersEntity;

import java.text.ParseException;
import java.util.Map;

@Controller
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping("/search")
    String searchInTable(@RequestParam(required = true) String table, Map<String, Object> model){
        searchService.initializer(table, model);

        return "searchFilter";
    }

    @PostMapping("/searchFilter")
    String searchFilter(@RequestParam(required = true) Integer id,
                        @RequestParam(required = false) String secondParam,
                        @RequestParam(required = false) String thirdParam,Map<String, Object> model) throws ParseException {
        searchService.search(id,secondParam,thirdParam,model);
        return "searchResult";
    }

    @GetMapping("/search")
    String searchPage(){
        return "search";
    }
}
