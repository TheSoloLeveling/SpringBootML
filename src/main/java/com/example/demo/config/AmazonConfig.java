package com.example.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIAY34MEQND7CIHQEGA",
                "Id9LtvkHATjnUPdWNerG+bKVUSKfjCdyjppjJkeC"
        );

        return AmazonS3Client.builder()
                .withRegion("eu-west-3")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }




}
