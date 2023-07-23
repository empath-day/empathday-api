package com.api.empathday

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class EmpathDayApplication

fun main(args: Array<String>) {
    runApplication<EmpathDayApplication>(*args)
}
