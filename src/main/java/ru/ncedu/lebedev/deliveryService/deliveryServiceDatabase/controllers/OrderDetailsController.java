package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class OrderDetailsController {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsController(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/orderDetails")
    public String orderDetailsView(Map<String, Object> model) {
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        model.put("orderDetails", orderDetails);
        return "orderDetails";
    }

    @PostMapping(value = "/addOrderDetails",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax addOrderDetails(@AuthenticationPrincipal UsersEntity user,
                                                  @RequestBody OrderDetailsMessage order) {
        OrderDetailsEntity orderDetail = new OrderDetailsEntity();
        orderDetail.setOrderDate(order.getOrderDate());
        orderDetail.setOrderAddress(order.getOrderAddress());
        if (order.getComment().isEmpty()) {
            orderDetail.setComment("Доставить как можно скорее");
        } else {
            orderDetail.setComment(order.getComment());
        }
        orderDetail.setStatus("Заказ не доставлен");
        orderDetail.setAuthor(user);
        orderDetailsRepository.save(orderDetail);
        return new ControllerAnswerToAjax("OK", "");
    }

    @PostMapping("/orderDetailsFilter")
    public String findOrderDetails(@RequestParam(required = false) Integer orderDetailsId,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate,
                                   @RequestParam(required = false) String orderAddress,
                                   Map<String, Object> model) {
        Iterable<OrderDetailsEntity> orderDetails;
        if (orderDetailsId != null & orderDate == null & orderAddress.isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsId(orderDetailsId);
        } else if (orderDetailsId == null & orderDate != null & !orderAddress.isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDateAndOrderAddress(orderDate, orderAddress);
        } else if (orderDetailsId == null & orderDate != null & orderAddress.isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDate(orderDate);
        } else if (orderDetailsId != null & orderDate != null & orderAddress.isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndOrderDate(orderDetailsId, orderDate);
        } else if (orderDetailsId != null & orderDate == null & !orderAddress.isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndOrderAddress(orderDetailsId, orderAddress);
        } else if (orderDetailsId == null & orderDate == null & !orderAddress.isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderAddress(orderAddress);
        } else if (orderDetailsId != null & orderDate != null & !orderAddress.isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndOrderDateAndOrderAddress(orderDetailsId, orderDate, orderAddress);
        } else {
            orderDetails = orderDetailsRepository.findAll();
        }
        if (!orderDetails.iterator().hasNext()) {
            model.put("filterCheck", "No order details with such index!");
            return "orderDetails";
        } else {
            model.put("orderDetails", orderDetails);
        }
        return "orderDetails";
    }

    @Transactional
    @PostMapping(value = "/deleteOrderDetails",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax deleteOrderDetails(@RequestBody OrderDetailsMessage order) {
        /*List<OrderDetailsEntity> orderDetails = orderDetailsRepository.findByOrderDetailsId(order.getOrderDetailsId());*/
        /*if (orderDetails.isEmpty()) {
            model.put("deleteIdCheck", "No order details with such index!");
            return "orderDetails";*/
        orderDetailsRepository.deleteByOrderDetailsId(order.getOrderDetailsId());
        return new ControllerAnswerToAjax("OK", "");
    }

    @Transactional
    @PostMapping(value = "/updateOrderDetails",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax updateOrderDetails(@RequestBody OrderDetailsMessage order) {
        List<OrderDetailsEntity> orderDetails = orderDetailsRepository.findByOrderDetailsId(order.getOrderDetailsId());
        /*if (orderDetails.isEmpty()) {
            model.put("updateIdCheck", "Order details with such index does not exist!");
            return "orderDetails";*/
        if (order.getOrderDate() != null) {
            orderDetailsRepository.setOrderDateFor(order.getOrderDate(), order.getOrderDetailsId());
        }
        if (!order.getOrderAddress().isEmpty()) {
            orderDetailsRepository.setOrderAddressFor(order.getOrderAddress(), order.getOrderDetailsId());
        }
        if (!order.getComment().isEmpty()) {
            orderDetailsRepository.setCommentFor(order.getComment(), order.getOrderDetailsId());
        }
        return new ControllerAnswerToAjax("OK", "");
    }

    @GetMapping(value = "/deliveryCoordinates", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendDeliveryCoordinates() {

        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        if (!orderDetails.iterator().hasNext()) {
            result.setMsg("Order details list is empty!");
        } else {
            result.setMsg("success");
        }
        result.setResult(orderDetails);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @PostMapping(value = "/changeDeliveryStatus",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax changeDeliveryStatus(@RequestBody ChangeStatusForOrderDetailsId order) {
        orderDetailsRepository.setStatusFor("Заказ доставлен", order.getOrderDetailsId());
        return new ControllerAnswerToAjax("OK", "");
    }
}
