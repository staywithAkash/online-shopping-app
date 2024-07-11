package com.inventoryservice.service;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.model.Inventory;
import com.inventoryservice.repository.InventoryRepository;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InventoryService {
    private static InventoryRepository inventoryRepository;
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository=inventoryRepository;
    }
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isProductAvailable(List<String> skuCode){

        //SEARCHING FOR PRODUCT
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCode);
        //SETTING INVENTORY-RESPONSE DTO
        List<InventoryResponse> inventoryResponses = inventoryList.stream()
                .map(e -> InventoryResponse.builder().skuCode(e.getSkuCode()).inStock(e.getQuantity() > 0).build())
                .collect(Collectors.toList());
        return inventoryResponses;
    }
}
