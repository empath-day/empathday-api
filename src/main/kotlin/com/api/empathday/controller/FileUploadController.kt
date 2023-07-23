package com.api.empathday.controller

import com.api.empathday.service.AmazonS3FileUploadService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FileUploadController(
    private val fileUploadService: AmazonS3FileUploadService
) {

    @PostMapping("/api/file/url")
    fun upload(@RequestParam("fileName") fileName: String): Map<String, Any?> {
        return fileUploadService.getPreSignedUrl(fileName)
    }
}