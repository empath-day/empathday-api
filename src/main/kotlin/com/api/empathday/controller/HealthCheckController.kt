package com.api.empathday.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/api/health")
    fun healthCheck(): String {
        return "hello world jaemin2"
    }

    @GetMapping("/api/health2")
    fun healthCheck2(): String {
        return "hello world jaemin2zzz"
    }
}