package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrdersEntity;
import java.util.List;

public interface OrdersRepository extends CrudRepository<OrdersEntity, Integer> {
    List<OrdersEntity> findByPaymentMethod(String orderPrice);
    List<OrdersEntity> findByOrderId(Integer id);
    void deleteByOrderId(Integer id);
    @Modifying
    @Query("update OrdersEntity c set c.departmentId = ?1 where c.orderId = ?2")
    void setDepartmentIdFor(Integer departmentId, Integer id);

    @Modifying
    @Query("update OrdersEntity c set c.managerId = ?1 where c.orderId = ?2")
    void setManagerIdFor(Integer managerId, Integer id);

    @Modifying
    @Query("update OrdersEntity c set c.courierId = ?1 where c.orderId = ?2")
    void setCourierIdFor(Integer courierId, Integer id);

    @Modifying
    @Query("update OrdersEntity c set c.paymentMethod = ?1 where c.orderId = ?2")
    void setPaymentMethodFor(String paymentMethod, Integer id);

    @Modifying
    @Query("update OrdersEntity c set c.orderPrice = ?1 where c.orderId = ?2")
    void setOrderPriceFor(Integer orderPrice, Integer id);

    @Modifying
    @Query("update OrdersEntity c set c.discount = ?1 where c.orderId = ?2")
    void setDiscountFor(Integer discount, Integer id);
}
