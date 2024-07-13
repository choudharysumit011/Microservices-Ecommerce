package com.ecommerce.inventoryservice.Controller;

import com.ecommerce.inventoryservice.DTO.InventoryResponse;
import com.ecommerce.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/exists")
    public List<InventoryResponse> isStockAvailable(@RequestParam List<String> skuCodes) {
        return inventoryService.isStockAvailable(skuCodes);
    }


}
