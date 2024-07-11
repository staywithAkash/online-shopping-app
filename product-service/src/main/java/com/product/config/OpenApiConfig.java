package com.product.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI productOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("product Service Api Document")
                        .description("I have created product service with spring boot following microservice architecture")
                        .version("0.0.1")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("This is external document")
                        .url("http//product-service-dummy-url.com/api-docs")
                );
    }
}
