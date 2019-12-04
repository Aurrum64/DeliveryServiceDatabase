package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ManagersEntity;

import java.util.Map;

@Service
public class SearchService {
    @Autowired
    private ManagersService managersService;

    private String table = null;

    public void initializer(String table, Map<String, Object> model){
        this.table = table;
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
    }

    public void search(Integer id, String secondParam, String thirdParam,Map<String, Object> model){
        switch(table){
            case "managers":
                managersSearch(id, secondParam, thirdParam, model);
                break;

        }
    }

    private void managersSearch(Integer id, String secondParam, String thirdParam,Map<String, Object> model){
        managersService.initializer(model);
        Iterable<ManagersEntity> managers = managersService.search(id, secondParam, thirdParam);
        if (!managers.iterator().hasNext()) {
            model.put("filterCheck", "No manager with such index!");
        } else {
            model.put("managers", managers);
        }
        model.put("managersTable", true);
    }
}
