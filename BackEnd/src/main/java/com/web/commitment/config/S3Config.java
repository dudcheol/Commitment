package com.web.commitment.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	@Bean
	public AmazonS3Client generateS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials("AKIAJ4R5VZAHPO4WYRAA",
				"qEKQZL0GGrc1WRCsVCkxs5pKJSSvYZ+0ISkZFcr3");
		AmazonS3Client client = new AmazonS3Client(credentials);
		return client;
	}
}
