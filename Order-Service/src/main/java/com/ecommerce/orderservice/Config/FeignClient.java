package com.ecommerce.orderservice.Config;


import com.ecommerce.orderservice.DTO.InventoryResponse;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "inventory-service")

public interface FeignClient {
    @LoadBalanced
    @GetMapping("api/inventory/exists")
    public List<InventoryResponse> isProductExist(@RequestParam("skuCodes") List<String> skuCodes);


}
