package com.ecommerce.inventoryservice.Service;

import com.ecommerce.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isStockAvailable(String skuCode) {
       return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
