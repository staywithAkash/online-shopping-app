package com.inventoryservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI inventoryOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("inventory Service Api Document")
                        .description("I have created inventory service with spring boot following microservice architecture")
                        .version("0.0.1")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("This is external document")
                        .url("http//inventory-service-dummy-url.com/api-docs")
                );
    }
}

