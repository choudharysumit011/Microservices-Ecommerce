package com.app.ecommerce.Product_Service.Service;


import com.app.ecommerce.Product_Service.Model.Product;
import com.app.ecommerce.Product_Service.dto.ProductRequest;
import com.app.ecommerce.Product_Service.dto.ProductResponse;
import com.app.ecommerce.Product_Service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

   private final ProductRepository productRepository;


    public ResponseEntity<String> createProduct(ProductRequest productRequest){
        List<Product> existingProducts = productRepository.findByName(productRequest.getName());
        if (!existingProducts.isEmpty()) {

            log.info("Product with name {} already exists", productRequest.getName());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product with name " + productRequest.getName() + " already exists");
            //throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
        }
        Product product = Product.builder().name(productRequest.getName()).price(productRequest.getPrice())
                .description(productRequest.getDescription()).build();

                productRepository.save(product);
        log.info("Product {} created",product.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body("Product created");


    }

    public List<ProductResponse> getProduct() {
        List<Product> products = productRepository.findAll();
       return products.stream().map(this::mapToProductResponse).toList();
    }

    public List<ProductResponse> getProductByName(String name) {

        if(productRepository.findByName(name).isEmpty()){
            log.info("Product with name {} doesn't exists", name);
            return Collections.EMPTY_LIST;
        }
        List<Product> products = productRepository.findByName(name);
        log.info("Product with name {} found",name);
        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }
}
