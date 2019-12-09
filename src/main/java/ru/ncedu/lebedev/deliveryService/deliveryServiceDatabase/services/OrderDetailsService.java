package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.OrderDetailsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;

import java.util.Date;
import java.util.Map;

@Service
public class OrderDetailsService {
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository){
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public void initializer(Map<String, Object> model){
        Iterable<OrderDetailsEntity> orderDetails = orderDetailsRepository.findAll();
        model.put("orderDetails", orderDetails);
    }

    public Iterable<OrderDetailsEntity> search(Integer orderDetailsId, Date orderDate, String orderAddress){
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
        return orderDetails;
    }
}
