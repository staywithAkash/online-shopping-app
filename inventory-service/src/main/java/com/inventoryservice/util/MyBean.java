package com.inventoryservice.util;

import com.inventoryservice.model.Inventory;
import com.inventoryservice.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
    private static InventoryRepository inventoryRepository;

    public MyBean(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @PostConstruct
    public void saveData(){
        Inventory inventory1=new Inventory();
        inventory1.setSkuCode("iphone");
        inventory1.setQuantity(100);
        Inventory inventory2=new Inventory();
        inventory2.setSkuCode("Bat");
        inventory2.setQuantity(100);
        inventoryRepository.save(inventory1);
        inventoryRepository.save(inventory2);
    }
}
