package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.domain.property.AuthProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
        "com.michaelfmnk.peterparker.userapi.domain",
        "com.michaelfmnk.peterparker.userapi.rest"
)
@EnableConfigurationProperties(AuthProperties::class)
class UserApiApplication

fun main(args: Array<String>) {
    runApplication<UserApiApplication>(*args)
}
