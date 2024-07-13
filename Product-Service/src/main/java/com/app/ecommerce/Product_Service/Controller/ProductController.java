package com.app.ecommerce.Product_Service.Controller;


import com.app.ecommerce.Product_Service.Service.ProductService;
import com.app.ecommerce.Product_Service.dto.ProductRequest;
import com.app.ecommerce.Product_Service.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);

    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponse> getProduct(){
       return productService.getProduct();
    }

    @GetMapping("/get/name/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponse> getProductByName(@PathVariable String id){
        return productService.getProductByName(id);
    }
}
