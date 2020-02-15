package com.michaelfmnk.peterparker.userapi.domain.config

import assertk.assertThat
import assertk.assertions.isNotEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

internal class PasswordEncoderTest {
    private val passwordEncoder: PasswordEncoder = PasswordEncoder()


    @Test
    internal fun `should encode and check password`() {
        val rawPassword = "secret"
        val encoded = passwordEncoder.encode(rawPassword)

        println("encoded password $encoded")
        val matches = passwordEncoder.matches(rawPassword, encoded)

        assertThat(rawPassword).isNotEqualTo(encoded)
        assertThat(matches).isTrue()
    }
}
