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
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderSpecificationsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderSpecificationEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

import java.util.List;

@Controller
public class OrderDetailsController {

    private OrderDetailsRepository orderDetailsRepository;
    private CouriersRepository couriersRepository;
    private OrderSpecificationsRepository orderSpecificationsRepository;

    @Autowired
    public OrderDetailsController(OrderDetailsRepository orderDetailsRepository,
                                  CouriersRepository couriersRepository,
                                  OrderSpecificationsRepository orderSpecificationsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.couriersRepository = couriersRepository;
        this.orderSpecificationsRepository = orderSpecificationsRepository;
    }

    @GetMapping("/orderDetails")
    public String orderDetailsView() {
        return "orderDetails";
    }

    @GetMapping("/orderDetailsHistory")
    public String orderDetailsHistory() {
        return "orderDetailsHistory";
    }

    @GetMapping("/activeOrderDetails")
    public String activeOrderDetails() {
        return "activeOrderDetails";
    }

    @GetMapping(value = "/orderDetailsList", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> sendOrderDetailsList() {

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
        System.out.println(order.getOrderDate());
        orderDetail.setOrderDate(order.getOrderDate());
        orderDetail.setFirstOrderAddressPoint(order.getFirstOrderAddressPoint());
        orderDetail.setSecondOrderAddressPoint(order.getSecondOrderAddressPoint());
        if (order.getComment().isEmpty()) {
            if (order.getOrderDate() == null) {
                orderDetail.setComment("Доставить как можно скорее");
            } else {
                orderDetail.setComment("Доставить в указанное время");
            }
        }
        if (order.getOrderDate() == null) {
            orderDetail.setStatus("Заказ доставляется");
        } else {
            orderDetail.setStatus("Заказ в ожидании");
        }
        orderDetail.setAuthor(user);
        orderDetail.setReviewWritten(false);
        orderDetail.setAlreadyInProgress(false);

        OrderSpecificationEntity specification = new OrderSpecificationEntity();
        specification.setCourierFound(false);
        specification.setCourierWent(false);
        specification.setOrderPickedUp(false);
        specification.setOrderDelivered(false);
        specification.setOrderConfirmed(false);
        specification.setRouteBlocked(false);
        orderSpecificationsRepository.save(specification);

        orderDetail.setOrderSpecification(specification);
        orderDetailsRepository.save(orderDetail);

        return new ControllerAnswerToAjax("OK", "");
    }

    @PostMapping(value = "/searchOrderDetails",
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ResponseEntity<?> findOrderDetails(@RequestBody OrderDetailsMessage order) {
        Iterable<OrderDetailsEntity> orderDetails;
        if (order.getOrderDetailsId() != null & order.getOrderDate() == null & order.getSecondOrderAddressPoint().isEmpty()) {
            orderDetails = orderDetailsRepository.findAllByOrderDetailsId(order.getOrderDetailsId());
        } else if (order.getOrderDetailsId() == null & order.getOrderDate() != null & !order.getSecondOrderAddressPoint().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDateAndSecondOrderAddressPoint(order.getOrderDate(), order.getSecondOrderAddressPoint());
        } else if (order.getOrderDetailsId() == null & order.getOrderDate() != null & order.getSecondOrderAddressPoint().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDate(order.getOrderDate());
        } else if (order.getOrderDetailsId() != null & order.getOrderDate() != null & order.getSecondOrderAddressPoint().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndOrderDate(order.getOrderDetailsId(), order.getOrderDate());
        } else if (order.getOrderDetailsId() != null & order.getOrderDate() == null & !order.getSecondOrderAddressPoint().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndSecondOrderAddressPoint(order.getOrderDetailsId(), order.getSecondOrderAddressPoint());
        } else if (order.getOrderDetailsId() == null & order.getOrderDate() == null & !order.getSecondOrderAddressPoint().isEmpty()) {
            orderDetails = orderDetailsRepository.findBySecondOrderAddressPoint(order.getSecondOrderAddressPoint());
        } else if (order.getOrderDetailsId() != null & order.getOrderDate() != null & !order.getSecondOrderAddressPoint().isEmpty()) {
            orderDetails = orderDetailsRepository.findByOrderDetailsIdAndOrderDateAndSecondOrderAddressPoint(order.getOrderDetailsId(), order.getOrderDate(), order.getSecondOrderAddressPoint());
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
        List<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAllByOrderDetailsId(order.getOrderDetailsId());
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
        List<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAllByOrderDetailsId(order.getOrderDetailsId());
        if (orderDetails.isEmpty()) {
            return new ControllerAnswerToAjax("NOT EXISTS", "");
        } else {
            if (order.getOrderDate() != null) {
                orderDetailsRepository.setOrderDateFor(order.getOrderDate(), order.getOrderDetailsId());
            }
            if (!order.getFirstOrderAddressPoint().isEmpty()) {
                orderDetailsRepository.setFirstOrderAddressPointFor(order.getFirstOrderAddressPoint(), order.getOrderDetailsId());
            }
            if (!order.getSecondOrderAddressPoint().isEmpty()) {
                orderDetailsRepository.setSecondOrderAddressPointFor(order.getSecondOrderAddressPoint(), order.getOrderDetailsId());
            }
            if (!order.getComment().isEmpty()) {
                orderDetailsRepository.setCommentFor(order.getComment(), order.getOrderDetailsId());
            }
            return new ControllerAnswerToAjax("OK", "");
        }
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

        OrderDetailsEntity blockedOrderRoute = orderDetailsRepository.findByOrderSpecification_RouteBlocked(true);
        if (blockedOrderRoute != null) {
            if (currentCourier.get(0).getCourierId().equals(blockedOrderRoute.getCourier().getCourierId())) {
                blockedOrderRoute.setCourier(null);
                blockedOrderRoute.getOrderSpecification().setRouteBlocked(false);
                orderDetailsRepository.save(blockedOrderRoute);
            }
        }
        return new ControllerAnswerToAjax("OK", "");
    }
}
