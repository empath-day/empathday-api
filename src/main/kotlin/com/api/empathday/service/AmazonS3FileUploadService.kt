package com.api.empathday.service

import com.api.empathday.config.AmazonS3Config
import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest
import java.time.Duration
import java.time.LocalDateTime

@Service
class AmazonS3FileUploadService(
    private val amazonS3Config: AmazonS3Config,
) {
    fun getPreSignedUrl(fileName: String): Map<String, Any?> {
        val encodedFileName = "${fileName}_${LocalDateTime.now()}"
        val objectKey = "test/${encodedFileName}"

        // AWS SDK for Java 2.x 버전에서 S3Presigner를 생성합니다.
        val s3Presigner: S3Presigner = S3Presigner.builder()
            .region(amazonS3Config.region.static)
            .credentialsProvider { AwsBasicCredentials.create(amazonS3Config.credentials.accessKey, amazonS3Config.credentials.secretKey) }
            .build()

        // Pre-sign URL 만료 시간을 설정합니다. 예시로 3분으로 설정합니다.
        val expirationDuration = Duration.ofMinutes(3)

        // PutObjectRequest를 생성합니다.
        val putObjectRequest: PutObjectRequest = PutObjectRequest.builder()
            .bucket(amazonS3Config.s3.bucket)
            .key(objectKey)
            .build()

        // Presigner를 사용하여 Pre-signed URL을 생성합니다.
        val preSignedUrl: PresignedPutObjectRequest? = s3Presigner.presignPutObject {
            it.signatureDuration(expirationDuration)
            it.putObjectRequest(putObjectRequest)
        }

        // 생성된 Pre-signed URL과 파일명을 Map으로 반환합니다.
        return mapOf(
            "preSignedUrl" to preSignedUrl?.url(),
            "encodedFileName" to encodedFileName
        )
    }
}