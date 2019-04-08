package com.qm.wxsmall;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by licy .
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qm.wxsmall"))
                .paths(Predicates.not(regex("/ssfError.*")))
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                ;

    }

    private ApiKey apiKey() {
        return new ApiKey("token", null, "header");
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("启明一汽物流团队", "http://www.qm.cn", "liyangzxx@126.com");
        return new ApiInfoBuilder()
                .title("")
                .description("yqwl base swagger api")
                .contact(contact)
                .version("1.0")
                .build();
    }
}