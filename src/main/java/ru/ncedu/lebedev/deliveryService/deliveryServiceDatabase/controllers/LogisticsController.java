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
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderSpecificationsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderSpecificationEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.List;

@Controller
public class LogisticsController {

    private OrderSpecificationsRepository orderSpecificationsRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private CouriersRepository couriersRepository;

    @Autowired
    public LogisticsController(OrderSpecificationsRepository orderSpecificationsRepository,
                               OrderDetailsRepository orderDetailsRepository,
                               CouriersRepository couriersRepository) {
        this.orderSpecificationsRepository = orderSpecificationsRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.couriersRepository = couriersRepository;
    }

    @GetMapping("/logistics")
    public String logistics() {
        return "logistics";
    }

    @PostMapping(value = "/changeDeliveryStatus",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax changeDeliveryStatus(@RequestBody ChangeStatusForOrderDetailsId order) {
        OrderSpecificationEntity specification = orderSpecificationsRepository.findByOrderSpecificationId(order.getOrderDetailsId());
        specification.setOrderDelivered(true);
        orderSpecificationsRepository.save(specification);
        return new ControllerAnswerToAjax("OK", "");
    }

    @PostMapping(value = "/assignCourierToOrder",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax assignCourierToOrder(@RequestBody ChangeStatusForOrderDetailsId message) {
        OrderDetailsEntity order = orderDetailsRepository.findByOrderDetailsId(message.getOrderDetailsId());

        OrderSpecificationEntity specification = order.getOrderSpecification();
        specification.setCourierFound(true);
        orderSpecificationsRepository.save(specification);

        CouriersEntity courier = couriersRepository.findByCourierId(message.getCourierId());
        order.setCourier(courier);
        order.setAlreadyInProgress(true);
        orderDetailsRepository.save(order);
        return new ControllerAnswerToAjax("OK", "");
    }

    @PostMapping(value = "/changeOrderPickedUpStatus",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax changeOrderPickedUpStatus(@RequestBody ChangeStatusForOrderDetailsId message) {
        OrderDetailsEntity order = orderDetailsRepository.findByOrderDetailsId(message.getOrderDetailsId());

        OrderSpecificationEntity specification = order.getOrderSpecification();
        specification.setOrderPickedUp(true);
        orderSpecificationsRepository.save(specification);

        return new ControllerAnswerToAjax("OK", "");
    }

    @PostMapping(value = "/blockSelectedRoute",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax blockSelectedRoute(@RequestBody ChangeStatusForOrderDetailsId message) {
        OrderDetailsEntity order = orderDetailsRepository.findByOrderDetailsId(message.getOrderDetailsId());

        CouriersEntity courier = couriersRepository.findByCourierId(message.getCourierId());
        order.setCourier(courier);
        orderDetailsRepository.save(order);

        OrderSpecificationEntity specification = order.getOrderSpecification();
        specification.setRouteBlocked(true);
        orderSpecificationsRepository.save(specification);

        return new ControllerAnswerToAjax("OK", "");
    }

    @PostMapping(value = "/unblockRoute",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax unblockRoute(@AuthenticationPrincipal UsersEntity user) {

        List<CouriersEntity> currentCourier = couriersRepository.findByFirstName(user.getUsername());

        List<OrderDetailsEntity> blockedOrderRoutes = orderDetailsRepository.findAllByOrderSpecification_RouteBlocked(true);
        for (OrderDetailsEntity blockedOrder : blockedOrderRoutes) {
            if (currentCourier.get(0).getCourierId().equals(blockedOrder.getCourier().getCourierId())) {
                if (!blockedOrder.getOrderSpecification().getCourierWent()) {
                    blockedOrder.setCourier(null);
                    blockedOrder.getOrderSpecification().setRouteBlocked(false);
                    orderDetailsRepository.save(blockedOrder);
                }
            }
        }
        return new ControllerAnswerToAjax("OK", "");
    }

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

    @Transactional
    @PostMapping(value = "/movingCourierCoordinates",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ControllerAnswerToAjax courierMove(@RequestBody CourierCoordinateAfterMove courier) {

        OrderSpecificationEntity specification = orderSpecificationsRepository.findByOrderSpecificationId(courier.getOrderId());
        if (!specification.getCourierWent()) {
            specification.setCourierWent(true);
        }
        couriersRepository.setLatitudeFor(courier.getLat(), courier.getCourierId());
        couriersRepository.setLongitudeFor(courier.getLng(), courier.getCourierId());
        return new ControllerAnswerToAjax("OK", "");
    }

    @GetMapping(value = "/currentCourier", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendCurrentCourier(@AuthenticationPrincipal UsersEntity user) {

        SendCouriersToAjax listWithCurrentCourier = new SendCouriersToAjax();
        List<CouriersEntity> currentCourier = couriersRepository.findByFirstName(user.getUsername());
        if (currentCourier.isEmpty() || !currentCourier.get(0).isReadiness() || currentCourier.get(0).isFired()) {
            listWithCurrentCourier.setMsg("Current courier don't ready to take orders!");
        } else {
            listWithCurrentCourier.setMsg("success");
            listWithCurrentCourier.setResult(currentCourier);
        }
        return ResponseEntity.ok(listWithCurrentCourier);
    }
}
