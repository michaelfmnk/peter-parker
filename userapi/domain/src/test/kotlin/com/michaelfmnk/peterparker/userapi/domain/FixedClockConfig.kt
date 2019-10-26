package com.michaelfmnk.peterparker.userapi.domain

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@TestConfiguration
class FixedClockConfig {
    @Bean
    fun clock(): Clock = Clock.fixed(Instant.parse("2007-12-03T10:15:30.00Z"), ZoneId.systemDefault())
}
