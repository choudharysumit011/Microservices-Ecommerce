package com.app.ecommerce.Product_Service.Controller;


import com.app.ecommerce.Product_Service.Service.ProductService;
import com.app.ecommerce.Product_Service.dto.ProductRequest;
import com.app.ecommerce.Product_Service.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@Validated
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequest productRequest){
       return productService.createProduct(productRequest);

    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponse> getProduct(){
       return productService.getProduct();
    }

    @GetMapping("/get/name/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> getProductByName(@PathVariable String id){
        List<ProductResponse> productResponses = productService.getProductByName(id);
        if (productResponses.isEmpty()) {
            // Return a custom message if no products are found
            Map<String, String> response = new HashMap<>();

            response.put("message", "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            // Return the list of products
            return ResponseEntity.ok(productResponses);
        }
    }
}
