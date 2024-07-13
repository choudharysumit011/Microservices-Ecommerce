package com.ecommerce.orderservice.Config;


import com.ecommerce.orderservice.DTO.InventoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "feign",url = "http://localhost:8082/api/inventory")
public interface FeignClient {

    @GetMapping("/exists")
    public List<InventoryResponse> isProductExist(@RequestParam("skuCodes") List<String> skuCodes);


}
