package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ProductsRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ProductsEntity;

import java.util.Map;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    public void initializer(Map<String, Object> model){
        Iterable<ProductsEntity> products = productsRepository.findAll();
        model.put("products", products);
    }

    public Iterable<ProductsEntity> search(Integer productId, String title, Integer price){
        Iterable<ProductsEntity> products;
        if (productId != null & title.isEmpty() & price == null) {
            products = productsRepository.findByProductId(productId);
        } else if (productId == null & !title.isEmpty() & price != null) {
            products = productsRepository.findByPriceAndTitle(price, title);
        } else if (productId == null & !title.isEmpty() & price == null) {
            products = productsRepository.findByTitle(title);
        } else if (productId != null & !title.isEmpty() & price == null) {
            products = productsRepository.findByProductIdAndTitle(productId, title);
        } else if (productId != null & title.isEmpty() & price != null) {
            products = productsRepository.findByProductIdAndPrice(productId, price);
        } else if (productId == null & title.isEmpty() & price != null) {
            products = productsRepository.findByPrice(price);
        } else if (productId != null & !title.isEmpty() & price != null) {
            products = productsRepository.findByProductIdAndPriceAndTitle(productId, price, title);
        } else {
            products = productsRepository.findAll();
        }
        return products;
    }
}
