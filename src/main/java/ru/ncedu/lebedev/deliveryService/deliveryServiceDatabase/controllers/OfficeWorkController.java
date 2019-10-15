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

        final int FIRST_ACTIVE_ORDERS_LIST_SIZE = 10;

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> activeOrders = orderDetailsRepository.findAllByStatus("Заказ доставляется");
        if (orderDetailsRepository.count() > FIRST_ACTIVE_ORDERS_LIST_SIZE) {
            List<OrderDetailsEntity> firstActiveOrders = activeOrders.subList(0, FIRST_ACTIVE_ORDERS_LIST_SIZE - 1);
            if (firstActiveOrders.isEmpty()) {
                result.setMsg("Active orders list is empty!");
            } else {
                result.setMsg("success");
            }
            result.setResult(firstActiveOrders);
        } else {
            if (activeOrders.isEmpty()) {
                result.setMsg("Active orders list is empty!");
            } else {
                result.setMsg("success");
            }
            result.setResult(activeOrders);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/activeCouriersListForOfficeWorkPage", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveCouriersListForOfficeWorkPage() {

        final int FIRST_ACTIVE_COURIERS_LIST_SIZE = 3;

        SendCouriersToAjax couriersList = new SendCouriersToAjax();
        List<CouriersEntity> activeCouriers = couriersRepository.findAllByReadinessAndFired(true, false);
        if (couriersRepository.count() > FIRST_ACTIVE_COURIERS_LIST_SIZE) {
            List<CouriersEntity> firstActiveCouriers = activeCouriers.subList(0, FIRST_ACTIVE_COURIERS_LIST_SIZE);
            if (firstActiveCouriers.isEmpty()) {
                couriersList.setMsg("Active couriers list for office work page is empty!");
            } else {
                couriersList.setMsg("success");
            }
            couriersList.setResult(firstActiveCouriers);
        } else {
            if (activeCouriers.isEmpty()) {
                couriersList.setMsg("Active couriers list for office work page is empty!");
            } else {
                couriersList.setMsg("success");
            }
            couriersList.setResult(activeCouriers);
        }
        return ResponseEntity.ok(couriersList);
    }
}
