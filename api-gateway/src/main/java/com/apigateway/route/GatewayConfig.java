//package com.apigateway.route;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.ServerResponse;
//
//@Configuration
//public class MyRoutes {
//    public RouterFunction<ServerResponse> productServiceSwaggerRoute(){
//        return GatewayRouterFunctions.route("product-service-swagger");
//    }
//}


package com.apigateway.route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    //.route("product_service", r -> r.path("/aggregate/product-service/**")
    //                        .filters(f -> f.setPath("/api-docs"))
    //                        .uri("lb://PRODUCT-SERVICE"))

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product_service_swagger", r -> r.path("/aggregate/product-service/v3/api-docs")
                        .filters(f -> f.setPath("/api-docs"))
                        .uri("http://localhost:8082"))
                .route("order_service_swagger", r -> r.path("/aggregate/order-service/v3/api-docs")
                        .filters(f -> f.setPath("/api-docs"))
                        .uri("http://localhost:8081"))
                .route("inventory_service_swagger", r -> r.path("/aggregate/inventory-service/v3/api-docs")
                        .filters(f -> f.setPath("/api-docs"))
                        .uri("http://localhost:9090"))
                .route("identity_service_swagger", r -> r.path("/aggregate/identity-service/v3/api-docs")
                        .filters(f -> f.setPath("/api-docs"))
                        .uri("http://localhost:8083"))
                .build();
    }
}