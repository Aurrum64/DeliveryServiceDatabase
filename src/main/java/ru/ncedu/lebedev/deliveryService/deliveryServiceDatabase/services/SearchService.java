package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class SearchService {
    private ManagersService managersService;
    private ProductsService productsService;
    private OrderDetailsService orderDetailsService;
    private CouriersService couriersService;
    private LocationService locationService;
    private String table = null;

    @Autowired
    public SearchService(ManagersService managersService, ProductsService productsService,
                         OrderDetailsService orderDetailsService, CouriersService couriersService,
                         LocationService locationService){
        this.managersService = managersService;
        this.productsService = productsService;
        this.orderDetailsService = orderDetailsService;
        this.couriersService = couriersService;
        this.locationService = locationService;
    }

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

    public void search(Integer id, String secondParam, String thirdParam,Map<String, Object> model) throws ParseException {
        switch(table){
            case "managers":
                managersSearch(id, secondParam, thirdParam, model);
                break;
            case "products":
                productsSearch(id, secondParam, thirdParam, model);
                break;
            case "orderdetails":
                orderDetailsSearch(id,secondParam, thirdParam, model);
            case "couriers":
                couriersSearch(id, secondParam, thirdParam, model);
            case "locations":
                locationsSearch(id, secondParam, thirdParam, model);
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

    private void productsSearch(Integer id, String secondParam, String thirdParam,Map<String, Object> model){
        productsService.initializer(model);
        Integer IntThirdParam;
        if(thirdParam == null || thirdParam.isEmpty()){
            IntThirdParam = null;
        }
        else{
            IntThirdParam = Integer.valueOf(thirdParam);
        }
        Iterable<ProductsEntity> products = productsService.search(id, secondParam, IntThirdParam);
        if (!products.iterator().hasNext()) {
            model.put("filterCheck", "No product with such index!");
        } else {
            model.put("products", products);
        }
        model.put("productsTable", true);
    }

    private void orderDetailsSearch(Integer id, String secondParam, String thirdParam,Map<String, Object> model) throws ParseException {
        orderDetailsService.initializer(model);

        Date dateSecondParam;
        if(secondParam == null || secondParam.isEmpty()){
            dateSecondParam = null;
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            dateSecondParam = format.parse(secondParam);
        }

        Iterable<OrderDetailsEntity> orderDetails = orderDetailsService.search(id, dateSecondParam, thirdParam);
        if (!orderDetails.iterator().hasNext()) {
            model.put("filterCheck", "No orders with such index!");
        } else {
            model.put("orderDetails", orderDetails);
        }
        model.put("ordersTable", true);
    }

    private void couriersSearch(Integer id, String secondParam, String thirdParam,Map<String, Object> model){
        couriersService.initializer(model);
        Iterable<CouriersEntity> couriers = couriersService.search(id, secondParam, thirdParam);
        if (!couriers.iterator().hasNext()) {
            model.put("filterCheck", "No courier with such index!");
        } else {
            model.put("couriers", couriers);
        }
        model.put("couriersTable", true);
    }

    private void locationsSearch(Integer id, String secondParam, String thirdParam,Map<String, Object> model){
        locationService.initializer(model);
        Integer IntThirdParam;
        if(thirdParam == null || thirdParam.isEmpty()){
            IntThirdParam = null;
        }
        else{
            IntThirdParam = Integer.valueOf(thirdParam);
        }
        Iterable<LocationsEntity> locations = locationService.search(id, secondParam, IntThirdParam);
        if (!locations.iterator().hasNext()) {
            model.put("filterCheck", "No locations with such index!");
        } else {
            model.put("locations", locations);
        }
        model.put("locationsTable", true);
    }
}
