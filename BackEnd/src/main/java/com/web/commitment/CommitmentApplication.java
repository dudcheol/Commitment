package com.web.commitment;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;

@SpringBootApplication
public class CommitmentApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CommitmentApplication.class, args);
	}
}
