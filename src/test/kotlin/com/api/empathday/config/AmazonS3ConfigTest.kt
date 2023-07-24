package com.api.empathday.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import software.amazon.awssdk.regions.Region

@ActiveProfiles("dev")
@SpringBootTest
class AmazonS3ConfigTest(@Autowired val amazonS3Config: AmazonS3Config) {

    @Test
    fun testAmazonS3ConfigTest() {
        val bucket = amazonS3Config.s3.bucket
        val region = amazonS3Config.region.static

        assertThat(bucket).isEqualTo("empath-day-test-s3-upload-bucket")
        assertThat(region).isEqualTo(Region.AP_NORTHEAST_2)
    }
}