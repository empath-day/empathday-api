package com.api.empathday.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.services.s3.S3Client

@Configuration
class S3ClientConfig {

    @Bean
    fun s3Client(amazonS3Config: AmazonS3Config): S3Client {
        return S3Client.builder()
            .credentialsProvider { AwsBasicCredentials.create(amazonS3Config.credentials.accessKey, amazonS3Config.credentials.secretKey) }
            .region(amazonS3Config.region.static)
            .build()
    }
}