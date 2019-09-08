package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities.OrderDetailsEntity;

import java.util.Date;
import java.util.List;

public interface OrderDetailsRepository extends CrudRepository<OrderDetailsEntity, Integer> {

    List<OrderDetailsEntity> findByOrderDetailsId(Integer id);

    List<OrderDetailsEntity> findByOrderDetailsIdAndOrderDate(Integer id, Date orderDate);

    List<OrderDetailsEntity> findByOrderDetailsIdAndOrderAddress(Integer id, String orderAddress);

    List<OrderDetailsEntity> findByOrderDate(Date orderDate);

    List<OrderDetailsEntity> findByOrderAddress(String orderAddress);

    List<OrderDetailsEntity> findByOrderDateAndOrderAddress(Date orderDate, String orderAddress);

    List<OrderDetailsEntity> findByOrderDetailsIdAndOrderDateAndOrderAddress(Integer id, Date orderDate, String orderAddress);

    void deleteByOrderDetailsId(Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.orderDate = ?1 where o.orderDetailsId = ?2")
    void setOrderDateFor(Date orderDate, Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.orderAddress = ?1 where o.orderDetailsId = ?2")
    void setOrderAddressFor(String orderAddress, Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.comment = ?1 where o.orderDetailsId = ?2")
    void setCommentFor(String comment, Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.status = ?1 where o.orderDetailsId = ?2")
    void setStatusFor(String status, Integer id);
}
