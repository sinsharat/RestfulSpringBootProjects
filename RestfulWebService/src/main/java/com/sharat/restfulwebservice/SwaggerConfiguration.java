package com.sharat.restfulwebservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

	private static final Contact DEFAULT_CONTACT = new Contact("Sharat", "www.github.com/sinsharat",
			"sinsharat@gmail.com");

	private static final String API_VERSION = "1.0";

	private static final Set<String> DEFAULT_CONSUMES_SET = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	private static final Set<String> DEFAULT_PRODUCES_SET = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket apiMonitor() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).consumes(DEFAULT_CONSUMES_SET)
				.produces(DEFAULT_PRODUCES_SET);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("REST API").contact(DEFAULT_CONTACT).version(API_VERSION)
				.description("Servicesx").build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
