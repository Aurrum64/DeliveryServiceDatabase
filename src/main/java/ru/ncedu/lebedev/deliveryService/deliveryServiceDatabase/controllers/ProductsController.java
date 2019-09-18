package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ProductsEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.ProductsRepository;

import java.util.List;
import java.util.Map;

@Controller
public class ProductsController {
    private ProductsRepository productsRepository;

    @Autowired
    ProductsController(ProductsRepository productsRepository){this.productsRepository = productsRepository;}

    @GetMapping("/products")
    String productsView(Map<String, Object> model){
        Iterable<ProductsEntity> products = productsRepository.findAll();
        model.put("products", products);
        return "products";
    }

    @PostMapping("/productsFilter")
    public String findCourier(@RequestParam(required = false) Integer productId,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) Integer price,
                              Map<String, Object> model) {
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
        if (!products.iterator().hasNext()) {
            model.put("filterCheck", "No product with such index!");
            return "products";
        } else {
            model.put("products", products);
        }
        return "products";
    }

    @Transactional
    @PostMapping("/productsDelete")
    public String deleteProduct(@RequestParam Integer productId, Map<String, Object> model) {
        List<ProductsEntity> product = productsRepository.findByProductId(productId);
        if (product.isEmpty()) {
            model.put("deleteIdCheck", "No product with such index!");
            return "products";
        } else {
            productsRepository.deleteByProductId(productId);
        }
        return "redirect:/products";
    }

    @Transactional
    @PostMapping("/productsUpdate")
    public String updateCourier(@RequestParam Integer productId,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) Integer price,
                                @RequestParam(required = false) Integer amount,
                                Map<String, Object> model) {
        List<ProductsEntity> product = productsRepository.findByProductId(productId);
        if (product.isEmpty()) {
            model.put("updateIdCheck", "Product with such index does not exist!");
            return "products";
        } else {
            if (!title.isEmpty()) {
                productsRepository.setTitleFor(title, productId);
            }
            if (price != null) {
                productsRepository.setPriceFor(price, productId);
            }
            if (amount != null) {
                productsRepository.setAmountFor(amount, productId);
            }
        }
        return "redirect:/products";
    }

    @PostMapping("/products")
    public String addProduct(@RequestParam String title,
                             @RequestParam Integer price,
                             @RequestParam(required = false) Integer amount){
        ProductsEntity product = new ProductsEntity();
        product.setTitle(title);
        product.setPrice(price);
        product.setAmount(amount);
        productsRepository.save(product);
        return "redirect:/products";
    }
}