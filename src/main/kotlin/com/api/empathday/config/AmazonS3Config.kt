package com.api.empathday.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@ConfigurationProperties("cloud.aws")
data class AmazonS3Config(

    val s3: S3Properties = S3Properties(),
    val credentials: CredentialsProperties = CredentialsProperties(),
    val region: RegionProperties = RegionProperties()
)

data class S3Properties (
    var bucket: String = ""
)


data class CredentialsProperties(
    var accessKey: String = "",
    var secretKey: String = ""
)

data class RegionProperties (
    var static: Region = Region.AP_NORTHEAST_2
)