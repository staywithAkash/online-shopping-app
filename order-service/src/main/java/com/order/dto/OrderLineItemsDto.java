package com.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    private int id;
    private String skuCode;
    private Long price;
    private String description;
    private int quantity;
}
