package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ProductsEntity;
import java.util.List;

public interface ProductsRepository extends CrudRepository<ProductsEntity, Integer> {

    List<ProductsEntity> findByProductId(Integer id);
    List<ProductsEntity> findByTitle(String title);
    List<ProductsEntity> findByPrice(Integer price);// не уверен, что нужно, мб уберу потом

    List<ProductsEntity> findByProductIdAndPrice(Integer id, Integer price);
    List<ProductsEntity> findByProductIdAndTitle(Integer id, String title);
    List<ProductsEntity> findByPriceAndTitle(Integer price, String title);

    List<ProductsEntity> findByProductIdAndPriceAndTitle(Integer id, Integer price, String title);

    void deleteByProductId(Integer id);

    @Modifying
    @Query("update ProductsEntity p set p.amount = ?1 where p.productId = ?2")
    void setAmountFor(Integer amount, Integer id);
    

    @Modifying
    @Query("update ProductsEntity p set p.price = ?1 where p.productId = ?2")
    void setPriceFor(Integer price, Integer id);

    @Modifying
    @Query("update ProductsEntity p set p.title = ?1 where p.productId = ?2")
    void setTitleFor(String title, Integer id);


}
