package com.michaelfmnk.peterparker.userapi.domain.model

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isCloseTo
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.michaelfmnk.peterparker.userapi.domain.DomainTestConfiguration
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.pointOf
import com.michaelfmnk.peterparker.userapi.domain.repository.IncidentRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.locationtech.spatial4j.distance.DistanceUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDateTime

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DomainTestConfiguration::class])
class IncidentTest {

    @Autowired
    lateinit var incidentRepository: IncidentRepository

    @ParameterizedTest
    @CsvSource(value = ["90, 100", "80, 70", "333, 99"])
    fun `should persist incident and provide id`(x: Double, y: Double) {
        // given
        val point = pointOf(x, y)

        val incident = Incident(
                documentId = "documentId",
                createdDate = LocalDateTime.now(),
                location = point,
                description = "description"
        )

        // when
        val savedEntity = incidentRepository.save(incident)

        // then
        println("---------my id is ${savedEntity.id}")
        assertThat(savedEntity.id).isNotNull()
        assertThat(savedEntity.location).isEqualTo(point)
    }

    @Test
    fun `should get with sorting by distance`() {
        val tenMeters = 0.01 * DistanceUtils.KM_TO_DEG
        val homeTown = pointOf(48.517607, 34.967458) // Dnipro
        val points = listOf(
                47.79813 to 35.188075, // Zaporizhia,
                -33.882478 to 18.313917, // Cape Town
                50.459935 to 30.530606, // Kyiv
                50.832983 to 4.497808 // Brussel
        )
        points.forEach { addIncidentInLocation(it.first, it.second) }

        val sortedPointsPage = incidentRepository.findNearest(homeTown, Pageable.unpaged())


        sortedPointsPage.content.forEach {
            println("$it distanceTo: ${it.distanceTo(homeTown)}")
        }
        assertThat(sortedPointsPage.content).all {
            hasSize(4)

            transform { it[0].id }.isEqualTo(1)
            transform { it[0].distanceTo(homeTown) }.isCloseTo(69.91643870993182, tenMeters)

            transform { it[1].id }.isEqualTo(3)
            transform { it[1].distanceTo(homeTown) }.isCloseTo(525.6977396064317, tenMeters)

            transform { it[2].id }.isEqualTo(4)
            transform { it[2].distanceTo(homeTown) }.isCloseTo(3396.4456700265664, tenMeters)

            transform { it[3].id }.isEqualTo(2)
            transform { it[3].distanceTo(homeTown) }.isCloseTo(8179.749011169173, tenMeters)
        }
    }

    @Test
    fun `should get with sorting by distance and pagination`() {
        val tenMeters = 0.01 * DistanceUtils.KM_TO_DEG
        val homeTown = pointOf(48.517607, 34.967458) // Dnipro
        val points = listOf(
                47.79813 to 35.188075, // Zaporizhia,
                -33.882478 to 18.313917, // Cape Town
                50.459935 to 30.530606, // Kyiv
                50.832983 to 4.497808 // Brussel
        )
        points.forEach { addIncidentInLocation(it.first, it.second) }

        val sortedPointsPage = incidentRepository.findNearest(homeTown, PageRequest.of(1, 1))


        sortedPointsPage.content.forEach {
            println("$it distanceTo: ${it.distanceTo(homeTown)}")
        }
        assertThat(sortedPointsPage.content).all {
            hasSize(1)

            transform { it[0].id }.isEqualTo(3)
            transform { it[0].distanceTo(homeTown) }.isCloseTo(525.6977396064317, tenMeters)
        }
        assertThat(sortedPointsPage.totalElements).isEqualTo(4)
    }

    @Test
    fun `should find by plate number`() {
        val homeTown = pointOf(48.517607, 34.967458) // Dnipro

        addIncidentInLocation(47.79813, 35.188075, "809080")
        addIncidentInLocation(47.79813, 35.188075, "809070")
        addIncidentInLocation(50.832983, 50.832983, "804080")

        val sortedPointsPage = incidentRepository.findNearest(homeTown, "809", Pageable.unpaged())


        sortedPointsPage.content.forEach {
            println("$it distanceTo: ${it.distanceTo(homeTown)}")
        }
        assertThat(sortedPointsPage.content).all {
            hasSize(2)

            transform { it[0].id }.isEqualTo(1)
            transform { it[0].plateNumber }.isEqualTo("809080")
            transform { it[1].id }.isEqualTo(2)
            transform { it[1].plateNumber }.isEqualTo("809070")

        }
    }

    @Test
    fun `should find by plate number and with pagination`() {
        val homeTown = pointOf(48.517607, 34.967458) // Dnipro

        addIncidentInLocation(47.79813, 35.188075, "809080")
        addIncidentInLocation(47.79813, 35.188075, "809070")
        addIncidentInLocation(50.832983, 50.832983, "804080")

        val sortedPointsPage = incidentRepository.findNearest(homeTown, "809", PageRequest.of(1, 1))


        sortedPointsPage.content.forEach {
            println("$it distanceTo: ${it.distanceTo(homeTown)}")
        }
        assertThat(sortedPointsPage.content).all {
            hasSize(1)

            transform { it[0].id }.isEqualTo(2)
            transform { it[0].plateNumber }.isEqualTo("809070")

        }
        assertThat(sortedPointsPage.totalElements).isEqualTo(2)
        assertThat(sortedPointsPage.totalPages).isEqualTo(2)
    }

    private fun addIncidentInLocation(lat: Double, lng: Double, plateNumber: String? = null) {
        val point = pointOf(lat, lng)
        val incident = Incident(
                documentId = "documentId",
                plateNumber = plateNumber,
                createdDate = LocalDateTime.now(),
                location = point,
                description = "description"
        )
        incidentRepository.save(incident)
    }
}
