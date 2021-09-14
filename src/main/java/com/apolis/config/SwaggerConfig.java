package com.apolis.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("ANSHUL SHARMA", "http://www.anshulsoftware.co.in", "anshul@gmail.com");
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("API Title", "API for Search Service", "v1.0", "", DEFAULT_CONTACT, "", "", Arrays.asList());
	
	public static final Set<String> DEFAULT_PRODUCER_CONSUMER = new HashSet<>(Arrays.asList("application/json", "application/xml"));
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCER_CONSUMER)
				.consumes(DEFAULT_PRODUCER_CONSUMER)
				;
	}
}
