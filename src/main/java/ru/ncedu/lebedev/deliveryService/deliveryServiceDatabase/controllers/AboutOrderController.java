package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderSpecificationsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderSpecificationEntity;

import java.util.Map;

@Controller
@RequestMapping("order")
public class AboutOrderController {

    private OrderSpecificationsRepository orderSpecificationsRepository;
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public AboutOrderController(OrderSpecificationsRepository orderSpecificationsRepository,
                                OrderDetailsRepository orderDetailsRepository) {
        this.orderSpecificationsRepository = orderSpecificationsRepository;
        this.orderDetailsRepository = orderDetailsRepository;
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
}
