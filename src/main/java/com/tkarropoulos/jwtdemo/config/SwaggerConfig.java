package com.tkarropoulos.jwtdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiConfig(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket.select()
                .apis(RequestHandlerSelectors.basePackage("com.tkarropoulos.jwtdemo.security.controller"))
                .paths(PathSelectors.any())
                .build();

        docket.apiInfo(apiInfo());
        return docket;
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Kapa Team",
                "https://github.com/TheodorosKarropoulos/spring-jwt.git", "some@mail.com");

        return new ApiInfo("JWT Authentication Server",
                "Spring JWT Authentication Server",
                "0.1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
