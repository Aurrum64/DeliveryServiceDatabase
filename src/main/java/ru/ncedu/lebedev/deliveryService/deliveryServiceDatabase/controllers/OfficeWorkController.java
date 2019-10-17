package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendCouriersToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendOrderDetailsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OfficeWorkController {

    private OrderDetailsRepository orderDetailsRepository;
    private CouriersRepository couriersRepository;

    @Autowired
    public OfficeWorkController(OrderDetailsRepository orderDetailsRepository,
                                CouriersRepository couriersRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.couriersRepository = couriersRepository;
    }

    @GetMapping("/officeWork")
    public String officeWork() {
        return "officeWork";
    }

    @GetMapping(value = "/activeOrdersListForOfficeWorkPage", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveOrdersListForOfficeWorkPage() {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> activeOrders = orderDetailsRepository.findAllByStatus("Заказ доставляется");
        List<OrderDetailsEntity> firstActiveOrders = activeOrders.stream().limit(10).collect(Collectors.toList());
        if (firstActiveOrders.isEmpty()) {
            result.setMsg("Active orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(firstActiveOrders);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/activeCouriersListForOfficeWorkPage", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveCouriersListForOfficeWorkPage() {

        SendCouriersToAjax couriersList = new SendCouriersToAjax();
        List<CouriersEntity> activeCouriers = couriersRepository.findAllByReadinessAndFired(true, false);
        List<CouriersEntity> firstActiveCouriers = activeCouriers.stream().limit(3).collect(Collectors.toList());
        if (firstActiveCouriers.isEmpty()) {
            couriersList.setMsg("Active couriers list for office work page is empty!");
        } else {
            couriersList.setMsg("success");
        }
        couriersList.setResult(firstActiveCouriers);

        return ResponseEntity.ok(couriersList);
    }
}
