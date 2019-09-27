package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface OrderDetailsRepository extends CrudRepository<OrderDetailsEntity, Integer> {

    OrderDetailsEntity findByOrderDetailsId(Integer id);

    List<OrderDetailsEntity> findAllByOrderDetailsId(Integer id);

    List<OrderDetailsEntity> findByOrderDetailsIdAndOrderDate(Integer id, Date orderDate);

    List<OrderDetailsEntity> findByOrderDetailsIdAndSecondOrderAddressPoint(Integer id, String secondOrderAddressPoint);

    List<OrderDetailsEntity> findByOrderDate(Date orderDate);

    List<OrderDetailsEntity> findBySecondOrderAddressPoint(String secondOrderAddressPoint);

    List<OrderDetailsEntity> findByOrderDateAndSecondOrderAddressPoint(Date orderDate, String secondOrderAddressPoint);

    List<OrderDetailsEntity> findByOrderDetailsIdAndOrderDateAndSecondOrderAddressPoint(Integer id, Date orderDate, String secondOrderAddressPoint);

    List<OrderDetailsEntity> findAll();

    List<OrderDetailsEntity> findAllByStatus(String status);

    void deleteByOrderDetailsId(Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.orderDate = ?1 where o.orderDetailsId = ?2")
    void setOrderDateFor(Date orderDate, Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.firstOrderAddressPoint = ?1 where o.orderDetailsId = ?2")
    void setFirstOrderAddressPointFor(String firstOrderAddressPoint, Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.secondOrderAddressPoint = ?1 where o.orderDetailsId = ?2")
    void setSecondOrderAddressPointFor(String secondOrderAddressPoint, Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.comment = ?1 where o.orderDetailsId = ?2")
    void setCommentFor(String comment, Integer id);

    @Modifying
    @Query("update OrderDetailsEntity o set o.status = ?1 where o.orderDetailsId = ?2")
    void setStatusFor(String status, Integer id);
}
