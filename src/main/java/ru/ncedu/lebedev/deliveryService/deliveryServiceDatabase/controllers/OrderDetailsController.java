package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;

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

    @PostMapping("/orderDetails")
    public String add(@RequestParam @DateTimeFormat(pattern = "dd-mm-yyyy") Date orderDate,
                      @RequestParam String orderAddress,
                      @RequestParam(required = false, defaultValue = "Доставить как можно скорее") String comment) {
        OrderDetailsEntity orderDetail = new OrderDetailsEntity();
        orderDetail.setOrderDate(orderDate);
        orderDetail.setOrderAddress(orderAddress);
        orderDetail.setComment(comment);
        orderDetail.setStatus("Заказ не доставлен");
        orderDetailsRepository.save(orderDetail);
        return "redirect:/orderDetails";
    }

    @PostMapping("/orderDetailsFilter")
    public String findOrderDetails(@RequestParam(required = false) Integer orderDetailsId,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "dd-mm-yyyy") Date orderDate,
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
    @PostMapping("/orderDetailsDelete")
    public String deleteCourier(@RequestParam Integer orderDetailsId, Map<String, Object> model) {
        List<OrderDetailsEntity> orderDetails = orderDetailsRepository.findByOrderDetailsId(orderDetailsId);
        if (orderDetails.isEmpty()) {
            model.put("deleteIdCheck", "No order details with such index!");
            return "orderDetails";
        } else {
            orderDetailsRepository.deleteByOrderDetailsId(orderDetailsId);
        }
        return "redirect:/orderDetails";
    }

    @Transactional
    @PostMapping("/orderDetailsUpdate")
    public String updateCourier(@RequestParam Integer orderDetailsId,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "dd-mm-yyyy") Date orderDate,
                                @RequestParam(required = false) String orderAddress,
                                @RequestParam(required = false) String comment,
                                Map<String, Object> model) {
        List<OrderDetailsEntity> orderDetails = orderDetailsRepository.findByOrderDetailsId(orderDetailsId);
        if (orderDetails.isEmpty()) {
            model.put("updateIdCheck", "Order details with such index does not exist!");
            return "orderDetails";
        } else {
            if (orderDate != null) {
                orderDetailsRepository.setOrderDateFor(orderDate, orderDetailsId);
            }
            if (!orderAddress.isEmpty()) {
                orderDetailsRepository.setOrderAddressFor(orderAddress, orderDetailsId);
            }
            if (!comment.isEmpty()) {
                orderDetailsRepository.setCommentFor(comment, orderDetailsId);
            }
        }
        return "redirect:/orderDetails";
    }

    @GetMapping(value = "/deliveryCoordinates", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendDeliveryCoordinates() {

        DetailsJsonResponseEntity result = new DetailsJsonResponseEntity();
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
    @RequestMapping(value = "/changeDeliveryStatus",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public JsonResponse changeDeliveryStatus(@RequestBody MovingCourierEntity courier) {
        orderDetailsRepository.setStatusFor("Заказ доставлен", 1);
        return new JsonResponse("Status changed, OK!", "");
    }
}
