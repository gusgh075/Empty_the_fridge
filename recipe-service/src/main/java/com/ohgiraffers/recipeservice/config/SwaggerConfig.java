package com.ohgiraffers.recipeservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(swaggerInfo())
                .servers(List.of(
                        new Server().url("http://localhost:8000/api/v1/recipe-service").description("Gateway URL"),
                        new Server().url("http://localhost:${server.port}").description("Direct Service URL")
                ))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token (without 'Bearer ' prefix)")
                        )
                );
    }

    private Info swaggerInfo() {
        return new Info()
                .title("Recipe Service API")
                .description("Recipe management with AI-powered recommendations using Google Gemini")
                .version("1.0.5")
                .contact(new Contact()
                        .name("Empty The Fridge Team")
                        .email("support@emptythefridge.com"));
    }
}