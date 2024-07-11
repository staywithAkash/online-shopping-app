package com.inventoryservice.controller;

import com.inventoryservice.dto.InventoryDto;
import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private static InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

//    @GetMapping("/{sku-code}")
    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getProduct(
            @RequestParam List<String> skuCode
    ){
        List<InventoryResponse> productAvailable = inventoryService.isProductAvailable(skuCode);
        return new ResponseEntity<>(productAvailable, HttpStatus.OK);

    }
}
