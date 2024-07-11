package com.product.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDto {
    private String Id;
    private String productName;
    private String description;
    private int price;
}

