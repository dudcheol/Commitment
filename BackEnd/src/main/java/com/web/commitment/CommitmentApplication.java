package com.web.commitment;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;

@SpringBootApplication
public class CommitmentApplication {
	@Bean
	public MultipartConfigElement multipartConfigElement() {
	MultipartConfigFactory factory = new MultipartConfigFactory();
//	factory.setMaxFileSize("512MB");
//	factory.setMaxRequestSize("512MB");
	return factory.createMultipartConfig();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(512000000);
		return multipartResolver;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CommitmentApplication.class, args);
	}
}