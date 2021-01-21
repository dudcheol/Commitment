package com.web.commitment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

//    @Bean
//    public AmazonS3 generateS3Client() {
//        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("AKIAJ4R5VZAHPO4WYRAA", "qEKQZL0GGrc1WRCsVCkxs5pKJSSvYZ+0ISkZFcr3");
//        // Get AmazonS3 client and return the s3Client object.
//        return AmazonS3ClientBuilder
//                .standard()
//                .withRegion(Regions.fromName("ap-northeast-2"))
//                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
//                .build();
//    }
    
    @Bean
    public AmazonS3Client generateS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials("AKIAJ4R5VZAHPO4WYRAA","qEKQZL0GGrc1WRCsVCkxs5pKJSSvYZ+0ISkZFcr3");
        AmazonS3Client client = new AmazonS3Client(credentials);
        return client;
    }
}