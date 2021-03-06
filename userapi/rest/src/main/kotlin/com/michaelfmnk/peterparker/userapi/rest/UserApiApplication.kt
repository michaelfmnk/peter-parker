package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.domain.property.AuthProperties
import com.michaelfmnk.peterparker.userapi.infrastructure.RestClientProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = [
    "com.michaelfmnk.peterparker.userapi.infrastructure",
    "com.michaelfmnk.peterparker.userapi.domain",
    "com.michaelfmnk.peterparker.userapi.rest"
])
@EntityScan("com.michaelfmnk.peterparker.userapi.domain.model.entity")
@EnableJpaRepositories("com.michaelfmnk.peterparker.userapi.domain.repository")
@EnableConfigurationProperties(value = [AuthProperties::class, RestClientProperties::class])
class UserApiApplication

fun main(args: Array<String>) {
    runApplication<UserApiApplication>(*args)
}
