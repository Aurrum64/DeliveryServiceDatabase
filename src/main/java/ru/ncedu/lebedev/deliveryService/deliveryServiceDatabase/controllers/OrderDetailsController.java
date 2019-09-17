package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.ChangeStatusForOrderDetailsId;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.ControllerAnswerToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.OrderDetailsMessage;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendOrderDetailsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

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

    @GetMapping(value = "/orderDetailsList", produces = "application/json")
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

    @PostMapping(value = "/searchOrderDetails",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity<?> findOrderDetails(@RequestBody OrderDetailsMessage order) {
        Iterable<OrderDetailsEntity> orderDetails;
        if (order.getOrderDetailsId() != null & order.getOrderDate() == null & order.getOrderAddress().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsId(order.getOrderDetailsId());
        } else if (order.getOrderDetailsId() == null & order.getOrderDate() != null & !order.getOrderAddress().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDateAndOrderAddress(order.getOrderDate(), order.getOrderAddress());
        } else if (order.getOrderDetailsId() == null & order.getOrderDate() != null & order.getOrderAddress().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDate(order.getOrderDate());
        } else if (order.getOrderDetailsId() != null & order.getOrderDate() != null & order.getOrderAddress().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndOrderDate(order.getOrderDetailsId(), order.getOrderDate());
        } else if (order.getOrderDetailsId() != null & order.getOrderDate() == null & !order.getOrderAddress().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndOrderAddress(order.getOrderDetailsId(), order.getOrderAddress());
        } else if (order.getOrderDetailsId() == null & order.getOrderDate() == null & !order.getOrderAddress().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderAddress(order.getOrderAddress());
        } else if (order.getOrderDetailsId() != null & order.getOrderDate() != null & !order.getOrderAddress().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndOrderDateAndOrderAddress(order.getOrderDetailsId(), order.getOrderDate(), order.getOrderAddress());
        } else {
            orderDetails = orderDetailsRepository.findAll();
        }
        SendOrderDetailsToAjax result = new SendOrderDetailsToAjax();
        if (!orderDetails.iterator().hasNext()) {
            result.setMsg("Nothing found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(orderDetails);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @PostMapping(value = "/deleteOrderDetails",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax deleteOrderDetails(@RequestBody OrderDetailsMessage order) {
        List<OrderDetailsEntity> orderDetails = orderDetailsRepository.findByOrderDetailsId(order.getOrderDetailsId());
        if (orderDetails.isEmpty()) {
            return new ControllerAnswerToAjax("NOT EXISTS", "");
        } else {
            orderDetailsRepository.deleteByOrderDetailsId(order.getOrderDetailsId());
            return new ControllerAnswerToAjax("OK", "");
        }
    }

    @Transactional
    @PostMapping(value = "/updateOrderDetails",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax updateOrderDetails(@RequestBody OrderDetailsMessage order) {
        List<OrderDetailsEntity> orderDetails = orderDetailsRepository.findByOrderDetailsId(order.getOrderDetailsId());
        if (orderDetails.isEmpty()) {
            return new ControllerAnswerToAjax("NOT EXISTS", "");
        } else {
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
