package com.synectiks.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class Awssnsconfig {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;
    String ak="ASIAZSLS3RLM3W5PKVBE";
    String sk="mB4CPBeSb8pzpIMUBfGAmuOoNVq89vDjZE4btYM4";
    String r="us-east-1";

    @Primary
    @Bean
    public SnsClient snsClient() {
        System.out.println("Initializing AWS SNS Client...");
        return SnsClient.builder()
                .region(Region.of(r))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(ak, sk)
                        )
                )
                .build();
    }
}
