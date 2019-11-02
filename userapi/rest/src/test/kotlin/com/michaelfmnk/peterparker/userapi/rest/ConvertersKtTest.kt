package com.michaelfmnk.peterparker.userapi.rest

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ConvertersKtTest {

    @Test
    fun `should convert Incident correctly`() {
        val entity = Incident("", LocalDateTime.now()).apply {
            id = 1L
        }

        val dto = entity.toDto()

        assertAll {
            assertThat(dto.createdDate).isEqualTo(entity.createdDate)
            assertThat(dto.id).isEqualTo(entity.id)
        }
    }
}
