package com.dio.cloudparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dio.cloudparking"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {//API Info sao os cabecarios apresentados no Swagger
        return new ApiInfoBuilder()
                .title("Parking REST API")
            .description("Projeto API Parking")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .build();
    }
}
