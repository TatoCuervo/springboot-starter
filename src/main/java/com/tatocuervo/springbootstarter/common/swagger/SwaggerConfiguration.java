package com.tatocuervo.springbootstarter.common.swagger;

import com.tatocuervo.springbootstarter.routes.Routes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static java.lang.String.format;

@Configuration
public class SwaggerConfiguration {

    public static final String GROUP_EMSP_ADMIN = "emsp admin";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant(format("/%s/**", Routes.API_VERSION))) // Filter local APIs to expose
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Courses API")
                .description("SpringBoot sample APIs")
                .version("v1.0")
                .build();
    }
}
