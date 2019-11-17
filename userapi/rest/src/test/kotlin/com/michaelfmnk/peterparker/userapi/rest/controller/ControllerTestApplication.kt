package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.domain.property.AuthProperties
import com.michaelfmnk.peterparker.userapi.infrastructure.RestClientProperties
import com.michaelfmnk.peterparker.userapi.rest.UserApiApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = [
    "com.michaelfmnk.peterparker.userapi.infrastructure",
    "com.michaelfmnk.peterparker.userapi.domain",
    "com.michaelfmnk.peterparker.userapi.rest"
])
@EnableConfigurationProperties(value = [AuthProperties::class, RestClientProperties::class])
class ControllerTestApplication

fun main(args: Array<String>) {
    runApplication<UserApiApplication>(*args)
}
