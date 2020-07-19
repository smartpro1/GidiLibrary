package com.gidilibrary.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig{
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo  apiInfo(){
        Contact contact = new Contact("Akeni Promise", "https://github/smartpro",
                "smartpromise380@gmail.com");
        return new ApiInfo(
                "GidiLibrary",
                "GidiLibrary API Documentation",
                "1.0",
                "Terms of Service: use responsively",
                contact,
                "Licence version 2.0",
                "https://www.apache.org/licences/LICENCE-2.0",
                Collections.emptyList()
        );
    }
}
