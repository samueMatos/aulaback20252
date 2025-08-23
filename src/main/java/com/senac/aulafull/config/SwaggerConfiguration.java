package com.senac.aulafull.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpemAPI(){
        return new OpenAPI().info(new Info()
                .title("API Aula Full")
                .version("1.0.0")
                .description("API Para controle da daos da WEB"));
    }

}
