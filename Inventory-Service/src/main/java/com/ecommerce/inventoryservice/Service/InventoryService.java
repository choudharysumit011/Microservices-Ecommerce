package com.ecommerce.inventoryservice.Service;

import com.ecommerce.inventoryservice.DTO.InventoryResponse;
import com.ecommerce.inventoryservice.Model.Inventory;
import com.ecommerce.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isStockAvailable(List<String> skuCodes) {
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);

        // Create a map of SKU codes to their corresponding inventory quantities
        Map<String, Integer> inventoryMap = inventories.stream()
                .collect(Collectors.toMap(Inventory::getSkuCode, Inventory::getQuantity));

        // Create InventoryResponse for each SKU code in the request
        List<InventoryResponse> inventoryResponses = skuCodes.stream()
                .map(skuCode -> {
                    Integer quantity = inventoryMap.get(skuCode);
                    boolean isInStock = quantity != null && quantity > 0;
                    return InventoryResponse.builder()
                            .skuCode(skuCode)
                            .isInStock(isInStock)
                            .build();
                })
                .collect(Collectors.toList());

        return inventoryResponses;
    }
}
