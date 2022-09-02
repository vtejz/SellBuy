package com.stackroute.OrderHistoryService.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public Docket productApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.OrderHistoryService"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(getinfo());
    }

    public ApiInfo getinfo()
    {
        ApiInfoBuilder apibuild=new ApiInfoBuilder();
        apibuild.title("Fav Service")
                .description("This is to access one to many relational dbs")
                .license("anushree.dabbe@delta.com");
        return apibuild.build();
    }
}
