package com.michaelfmnk.peterparker.userapi.rest

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import org.junit.jupiter.api.Test
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import java.time.LocalDateTime

class ConvertersKtTest {

    @Test
    fun `should convert Incident correctly`() {
        val pointCoords = Coordinate(90.7, 60.6)
        val entity = Incident("", "plateNum", LocalDateTime.now(), GeometryFactory().createPoint(pointCoords), "desc").apply {
            id = 1L
        }

        val dto = entity.toDto()

        assertAll {
            assertThat(dto.createdDate).isEqualTo(entity.createdDate)
            assertThat(dto.id).isEqualTo(entity.id)
        }
    }
}
