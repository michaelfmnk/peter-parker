package com.michaelfmnk.peterparker.userapi.domain

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@SpringBootApplication(scanBasePackages = ["com.michaelfmnk.peterparker.userapi.domain"])
class DomainTestConfiguration {
    @Bean
    @Primary
    fun clock(): Clock = Clock.fixed(Instant.parse("2007-12-03T10:15:30.00Z"), ZoneId.systemDefault())
}
