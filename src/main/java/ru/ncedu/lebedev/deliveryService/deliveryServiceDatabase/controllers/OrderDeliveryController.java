package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendOrderDetailsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.*;

@Controller
public class OrderDeliveryController {

    private OrderDetailsRepository orderDetailsRepository;
    private CouriersRepository couriersRepository;

    @Autowired
    public OrderDeliveryController(OrderDetailsRepository orderDetailsRepository,
                                   CouriersRepository couriersRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.couriersRepository = couriersRepository;
    }

    @GetMapping("/orderDelivery")
    public String orderDelivery(@AuthenticationPrincipal UsersEntity user,
                                Map<String, Object> model) {
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();

        List<OrderDetailsEntity> archiveOrdersListForCurrentUser = new ArrayList<>();
        for (OrderDetailsEntity element : orderDetails) {
            if (user.getUsername().equals(element.getAuthorName()) &&
                    element.getStatus().equals("Заказ доставлен")) {
                archiveOrdersListForCurrentUser.add(element);
            }
        }
        model.put("archiveOrdersListForCurrentUser", archiveOrdersListForCurrentUser);
        return "orderDelivery";
    }

    @GetMapping(value = "/activeOrdersListForUser", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveOrdersListForUser(@AuthenticationPrincipal UsersEntity user) {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> activeOrdersListForCurrentUser = new ArrayList<>();
        for (OrderDetailsEntity element : orderDetails) {
            if (user.getUsername().equals(element.getAuthorName()) &&
                    element.getStatus().equals("Заказ доставляется")) {
                activeOrdersListForCurrentUser.add(element);
            }
        }
        if (activeOrdersListForCurrentUser.isEmpty()) {
            result.setMsg("Active orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(activeOrdersListForCurrentUser);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/waitingOrdersListForUser", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendWaitingOrdersListForUser(@AuthenticationPrincipal UsersEntity user) {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> waitingOrdersListForCurrentUser = new ArrayList<>();
        for (OrderDetailsEntity element : orderDetails) {
            if (user.getUsername().equals(element.getAuthorName()) &&
                    element.getStatus().equals("Заказ в ожидании")) {
                Calendar orderDate = Calendar.getInstance();
                orderDate.setTime(element.getOrderDate());
                orderDate.add(Calendar.HOUR, -3);
                Calendar currentDate = Calendar.getInstance();
                currentDate.setTime(new Date());
                if (orderDate.before(currentDate)) {
                    element.setStatus("Заказ доставляется");
                    orderDetailsRepository.save(element);
                } else {
                    waitingOrdersListForCurrentUser.add(element);
                }
            }
        }
        if (waitingOrdersListForCurrentUser.isEmpty()) {
            result.setMsg("Active orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(waitingOrdersListForCurrentUser);
        return ResponseEntity.ok(result);
    }

/*    @GetMapping(value = "/orderDelivery", produces = "application/json")
    public String sendArchiveOrdersListForUser(@AuthenticationPrincipal UsersEntity user,
                                               Map<String, Object> model) {

        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        List<OrderDetailsEntity> archiveOrdersListForCurrentUser = new ArrayList<>();
        for (OrderDetailsEntity element : orderDetails) {
            if (user.getUsername().equals(element.getAuthorName()) &&
                    element.getStatus().equals("Заказ доставлен")) {
                archiveOrdersListForCurrentUser.add(element);
            }
        }
        model.put("archiveOrdersListForCurrentUser", archiveOrdersListForCurrentUser);
        return "redirect:/orderDelivery";
    }*/

    @GetMapping(value = "/activeOrdersListForMap", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveOrdersListForCurrentCourier(@AuthenticationPrincipal UsersEntity user) {

        final int FIRST_ACTIVE_ORDERS_LIST_SIZE = 5;

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> activeOrders = orderDetailsRepository.
                findAllByStatusAndAlreadyInProgressAndOrderSpecification_RouteBlocked("Заказ доставляется", false, false);

        List<CouriersEntity> thisCourier = couriersRepository.findByFirstName(user.getUsername());
        List<OrderDetailsEntity> ordersOfThisCourier = orderDetailsRepository.
                findAllByCourierFirstNameAndStatusAndOrderSpecification_RouteBlocked(thisCourier.get(0).getFirstName(), "Заказ доставляется", true);
        if (!ordersOfThisCourier.isEmpty()) {
            activeOrders.addAll(ordersOfThisCourier);
        }
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

    @GetMapping(value = "/activeOrdersListForLogisticsPage", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendActiveOrdersListForLogisticsPage() {

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

    @GetMapping(value = "/archiveOrdersListForLogisticPage", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendArchiveOrdersListForManager() {

        final int RECENT_ARCHIVE_ORDERS_LIST_SIZE = 10;

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> archiveOrders = orderDetailsRepository.findAllByStatus("Заказ доставлен");
        if (orderDetailsRepository.count() > 10) {
            List<OrderDetailsEntity> recentArchiveOrders = archiveOrders.subList(archiveOrders.size() - RECENT_ARCHIVE_ORDERS_LIST_SIZE,
                    archiveOrders.size());
            if (recentArchiveOrders.isEmpty()) {
                result.setMsg("Archive orders list is empty!");
            } else {
                result.setMsg("success");
            }
            result.setResult(recentArchiveOrders);
        } else {
            if (archiveOrders.isEmpty()) {
                result.setMsg("Archive orders list is empty!");
            } else {
                result.setMsg("success");
            }
            result.setResult(archiveOrders);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/allArchiveOrdersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendAllArchiveOrdersList() {
        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> archiveOrders = orderDetailsRepository.findAllByStatus("Заказ доставлен");
        if (archiveOrders.isEmpty()) {
            result.setMsg("Archive orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(archiveOrders);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/allActiveOrdersList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendAllActiveOrdersList() {
        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        List<OrderDetailsEntity> archiveOrders = orderDetailsRepository.findAllByStatus("Заказ доставляется");
        if (archiveOrders.isEmpty()) {
            result.setMsg("Archive orders list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(archiveOrders);
        return ResponseEntity.ok(result);
    }
}
