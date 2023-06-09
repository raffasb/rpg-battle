package com.avanade.rpgbattle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi( ) {
        return new Docket( DocumentationType.SWAGGER_2 )
                .select( )
                .apis( RequestHandlerSelectors.any() )
                .paths( regex( "/api.*" ) )
                .build( )
                .apiInfo( metaInfo( ) );
    }

    private ApiInfo metaInfo( ) {

        ApiInfo apiInfo = new ApiInfo( "RPG Battle",
                "Back-end for RPG battle application using Java, Spring Boot and H2 database",
                "1.0.0",
                "https://en.wikipedia.org/wiki/Terms_of_service",
                new Contact( "Rafael Barros",
                "https://github.com/raffasb",
                "r.barros.dos.santos@avanade.com" ),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",
                new ArrayList<VendorExtension>( ) );

        return apiInfo;
    }

}
