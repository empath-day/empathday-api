package com.api.empathday.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@ConfigurationProperties("cloud.aws")
data class AmazonS3Config(

    val s3: S3Properties = S3Properties(),
    val credentials: CredentialsProperties = CredentialsProperties(),
    val region: RegionProperties = RegionProperties()
) {
    @Bean
    fun s3Client(): S3Client {
        return S3Client.builder()
            .credentialsProvider { AwsBasicCredentials.create(credentials.accessKey, credentials.secretKey) }
            .region(region.static)
            .build()
    }
}

class S3Properties (
    val bucket: String = ""
)


class CredentialsProperties (
    val accessKey: String = "",
    val secretKey: String = ""
)

class RegionProperties (
    val static: Region = Region.AP_NORTHEAST_2
)