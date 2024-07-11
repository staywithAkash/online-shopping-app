package com.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    private String productId;
    @Column(name = "product_name",nullable = false)
    private String productName;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name="product_price")
    private int price;
}
