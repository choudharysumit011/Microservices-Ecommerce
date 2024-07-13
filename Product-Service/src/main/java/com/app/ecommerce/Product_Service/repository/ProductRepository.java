package com.app.ecommerce.Product_Service.repository;

import com.app.ecommerce.Product_Service.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);
}
