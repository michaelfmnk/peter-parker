package com.michaelfmnk.peterparker.userapi.rest

import assertk.assertThat
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [UserApiApplication::class])
class IntegrationBaseTest {

    @Test
    fun `should load context`() {
        assertThat(true).isTrue()
    }

}
