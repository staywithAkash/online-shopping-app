package com.product.service;

import com.product.entity.Product;
import com.product.payload.ProductDto;
import com.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private static ModelMapper modelMapper;
    private ProductRepository productRepository;
    public ProductServiceImpl(ModelMapper mapper,ProductRepository prodRepository){
        modelMapper=mapper;
        productRepository=prodRepository;
    }
    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = mapToProductEntity(productDto);
        String id= UUID.randomUUID().toString();
        product.setProductId(id);
        Product savedProduct = this.productRepository.save(product);
        return mapToProductDto(savedProduct);
    }

    @Override
    public List<ProductDto> getProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList1 = productList.stream().map(ProductServiceImpl::mapToProductDto).collect(Collectors.toList());
        return productDtoList1;
    }

    @Override
    public ProductDto getProduct(String pId) {
        return null;
    }

    @Override
    public String deleteProduct(String pId) {
        return null;
    }

    @Override
    public ProductDto updateProduct(String pId) {
        return null;
    }
    public static Product mapToProductEntity(ProductDto productDto){
        Product mappedToProd = modelMapper.map(productDto, Product.class);
        return mappedToProd;
    }
    public static ProductDto mapToProductDto(Product prod){
        ProductDto mappedToProdDto = modelMapper.map(prod, ProductDto.class);
        return mappedToProdDto;
    }
}
