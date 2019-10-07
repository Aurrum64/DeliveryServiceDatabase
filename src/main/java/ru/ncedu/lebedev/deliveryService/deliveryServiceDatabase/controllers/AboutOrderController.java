package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.CouriersMessage;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendCouriersToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.SendOrderDetailsToAjax;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.TrackedCourierMessage;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderSpecificationsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderSpecificationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("order")
public class AboutOrderController {

    private OrderSpecificationsRepository orderSpecificationsRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private CouriersRepository couriersRepository;

    @Autowired
    public AboutOrderController(OrderSpecificationsRepository orderSpecificationsRepository,
                                OrderDetailsRepository orderDetailsRepository,
                                CouriersRepository couriersRepository) {
        this.orderSpecificationsRepository = orderSpecificationsRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.couriersRepository = couriersRepository;
    }

    @GetMapping("{orderDetailsId}")
    public String aboutOrderView(@PathVariable Integer orderDetailsId,
                                 Map<String, Object> model) {
        OrderSpecificationEntity specification = orderSpecificationsRepository.findByOrderSpecificationId(orderDetailsId);
        OrderDetailsEntity order = orderDetailsRepository.findByOrderDetailsId(orderDetailsId);
        model.put("specification", specification);
        model.put("order", order);
        return "aboutOrder";
    }

    @Transactional
    @PostMapping(value = "orderConfirmation")
    public String orderConfirmation(@RequestParam Integer orderDetailsId) {
        OrderSpecificationEntity specification = orderSpecificationsRepository.findByOrderSpecificationId(orderDetailsId);
        specification.setOrderConfirmed(true);
        orderSpecificationsRepository.save(specification);

        orderDetailsRepository.setStatusFor("Заказ доставлен", orderDetailsId);
        return "redirect:/order/" + orderDetailsId;
    }

    @PostMapping("orderTracking/{orderDetailsId}")
    public String orderTracking(@RequestParam Integer orderDetailsId,
                                Map<String, Object> model) {
        OrderDetailsEntity order = orderDetailsRepository.findByOrderDetailsId(orderDetailsId);
        Integer courierId = order.getCourier().getCourierId();
        model.put("orderDetailsId", orderDetailsId);
        model.put("courierId", courierId);
        return "orderTracking";
    }

    @PostMapping(value = "orderTracking/{orderDetailsId}/courierTracking",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity<?> courierTracking(@RequestBody TrackedCourierMessage couriersMessage) {
        SendCouriersToAjax courier = new SendCouriersToAjax();
        CouriersEntity trackedCourier = couriersRepository.findByCourierId(couriersMessage.getCourierId());
        List<CouriersEntity> listWithTrackedCourier = new ArrayList<>();
        listWithTrackedCourier.add(trackedCourier);
        courier.setMsg("success");
        courier.setResult(listWithTrackedCourier);
        return ResponseEntity.ok(courier);
    }

    @PostMapping(value = "orderTracking/{orderDetailsId}/trackedOrder",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity<?> trackedOrder(@RequestBody TrackedCourierMessage couriersMessage) {
        SendOrderDetailsToAjax order = new SendOrderDetailsToAjax();
        OrderDetailsEntity trackedOrder = orderDetailsRepository.findByOrderDetailsId(couriersMessage.getOrderDetailsId());
        List<OrderDetailsEntity> listWithTrackedOrder = new ArrayList<>();
        listWithTrackedOrder.add(trackedOrder);
        order.setMsg("success");
        order.setResult(listWithTrackedOrder);
        return ResponseEntity.ok(order);
    }
}
