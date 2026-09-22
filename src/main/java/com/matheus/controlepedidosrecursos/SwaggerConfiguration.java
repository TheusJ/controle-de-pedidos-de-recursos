package com.matheus.controlepedidosrecursos;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(
                new Info()
                        .title("Controle de Pedidos de Recursos API")
                        .version("1.0.0")
                        .description("Documentação do Controle de Pedidos de Recursos API")
        );
    }
}
