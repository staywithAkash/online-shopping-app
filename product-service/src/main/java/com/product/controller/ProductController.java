package com.product.controller;

import com.product.payload.ProductDto;
import com.product.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductServiceImpl prodServiceImpl;
    public ProductController(ProductServiceImpl prodServiceImpl){
        this.prodServiceImpl=prodServiceImpl;
    }
    @PostMapping("/post")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto prodDto
    ){
        ProductDto productDto = prodServiceImpl.saveProduct(prodDto);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> product = prodServiceImpl.getProduct();
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
