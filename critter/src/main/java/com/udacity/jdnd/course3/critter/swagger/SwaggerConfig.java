package com.udacity.jdnd.course3.critter.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
//@PropertySource("classpath:swagger.properties")
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                //return ApiSelectorBuilder: control những endpoint được tìm ra bởi swagger
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                 //ta sẽ customize lại response
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Dog API",
                "This API returns a list of dogs.",
                "1.0",
                "trungvq8",
                new Contact("Vu Quoc Trung", "www.fpt.com", "trungvq8@fpt.com"),
                "License of API", "trungvq8@fpt.com", Collections.emptyList());

    }

}

