package com.michaelfmnk.peterparker.userapi.rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
        "com.michaelfmnk.peterparker.userapi.rest",
        "com.michaelfmnk.peterparker.userapi.domain.config"
)
class UserApiApplication

fun main(args: Array<String>) {
    runApplication<UserApiApplication>(*args)
}
