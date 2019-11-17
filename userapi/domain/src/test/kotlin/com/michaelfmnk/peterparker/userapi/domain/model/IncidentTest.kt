package com.michaelfmnk.peterparker.userapi.domain.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.michaelfmnk.peterparker.userapi.domain.DomainTestConfiguration
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.repository.IncidentRepository
import org.junit.jupiter.api.Test
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDateTime

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DomainTestConfiguration::class])
class IncidentTest {

    @Autowired
    lateinit var incidentRepository: IncidentRepository

    @Test
    fun `should persist incident and provide id`() {
        // given
        val point = createPoint(90.0, 100.0)

        val incident = Incident("documentId", LocalDateTime.now(), point)

        // when
        val savedEntity = incidentRepository.save(incident)

        // then
        println("---------my id is ${savedEntity.id}")
        assertThat(savedEntity.id).isNotNull()
        assertThat(savedEntity.location).isEqualTo(point)
    }

    private fun createPoint(x: Double, y: Double): Point {
        val pointCoords = Coordinate(x, y)
        val geomFactory = GeometryFactory()
        return geomFactory.createPoint(pointCoords)
    }

}
