package com.order.controller;

import com.order.dto.OrderRequest;
import com.order.service.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")

public class OrderController {
    private OrderServiceImpl orderService;
    public OrderController(OrderServiceImpl orderService){
        this.orderService=orderService;
    }
//    @PostMapping
//    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest){
//        String msg = orderService.placeOrder(orderRequest);
//        return new ResponseEntity<>(msg, HttpStatus.CREATED);
//    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "createOrderFallback")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> createOrder(@RequestBody OrderRequest orderRequest){
        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
    }
    public CompletableFuture<String> createOrderFallback(OrderRequest orderRequest,RuntimeException e){
        return CompletableFuture.supplyAsync(()->"oops something went wrong!!");
    }


}
