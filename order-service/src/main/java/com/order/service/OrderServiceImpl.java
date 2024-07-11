package com.order.service;

import ch.qos.logback.core.CoreConstants;
import com.order.dto.InventoryResponse;
import com.order.dto.OrderLineItemsDto;
import com.order.dto.OrderRequest;
import com.order.model.OrderDetails;
import com.order.model.OrderLineItems;
import com.order.repository.OrderDetailsRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl {
    @Autowired
    private EntityManager entityManager;
    private static ModelMapper mapper;
    private WebClient.Builder webClientBuilder;
    private static OrderDetailsRepository orderDetailsRepository;
    public OrderServiceImpl(ModelMapper mapper,OrderDetailsRepository orderDetailsRepository,WebClient.Builder webClientBuilder){
        this.mapper=mapper;
        this.orderDetailsRepository=orderDetailsRepository;
        this.webClientBuilder=webClientBuilder;
    }
    public String placeOrder(OrderRequest orderRequest){
        OrderDetails orderDetails =new OrderDetails();
        orderDetails.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> listOfOLI= orderRequest.getOrderLineItemsDtos()
                .stream()
                .map(OrderServiceImpl::mapToEntity)
                .toList();
        orderDetails.setOrderLineItems(listOfOLI);
        List<String> skuCodeList = orderDetails.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());
        InventoryResponse[] inevntoryResponses = webClientBuilder.build().get()
                .uri("http://inventory/api/inventory",
                        uriBuilder ->
                            uriBuilder.queryParam("skuCode", skuCodeList).build()
                        )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
//        boolean availabilityOfProduct =Arrays.stream(inevntoryResponses).allMatch(InventoryResponse::getInStock);
//        System.out.println("check" +availabilityOfProduct);
        //Arrays.stream(inevntoryResponses).allMatch(e -> skuCodeList.contains(e.getSkuCode()));
        boolean checkSkuCode=false;
        for(String skuCode:skuCodeList){
            checkSkuCode= Arrays.stream(inevntoryResponses).anyMatch(e->e.getSkuCode().equals(skuCode));
            if(!checkSkuCode){
                break;
            }
        }
        if(checkSkuCode){
            OrderDetails save = orderDetailsRepository.save(orderDetails);
            return "Order Placed Successfully!!";
        }
        else{
            throw new IllegalArgumentException("Product is out of stock!!");
        }
    }

    private static OrderLineItems mapToEntity(OrderLineItemsDto orderLineItemsDto) {
    return mapper.map(orderLineItemsDto,OrderLineItems.class);
    }
}
