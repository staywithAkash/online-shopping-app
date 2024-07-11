package com.product.service;

import com.product.payload.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);
    List<ProductDto> getProduct();
    ProductDto getProduct(String pId);
    String deleteProduct(String pId);
    ProductDto updateProduct(String pId);
}
