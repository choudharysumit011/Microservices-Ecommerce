package com.ecommerce.inventoryservice.Controller;

import com.ecommerce.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public boolean isStockAvailable(@PathVariable("skuCode") String skuCode) {
        return inventoryService.isStockAvailable(skuCode);
    }


}
