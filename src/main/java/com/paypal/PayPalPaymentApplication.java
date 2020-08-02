package com.paypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class PayPalPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayPalPaymentApplication.class, args);
	}
	@Bean
    public ObjectMapper customJson() {

        return new Jackson2ObjectMapperBuilder()
            .indentOutput(true)
            .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
            .build();
    }
	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.paypal.controller"))              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
}
