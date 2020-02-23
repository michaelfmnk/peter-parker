package com.michaelfmnk.peterparker.userapi.domain.service

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.michaelfmnk.peterparker.userapi.domain.DomainTestConfiguration
import com.michaelfmnk.peterparker.userapi.domain.event.IncidentCreatedEvent
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.pointOf
import com.michaelfmnk.peterparker.userapi.domain.repository.IncidentRepository
import io.mockk.CapturingSlot
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import java.time.LocalDateTime


@DataJpaTest
@SqlGroup(value = [Sql(value = ["classpath:clear.sql"]), Sql(value = ["classpath:incidents.sql"])])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DomainTestConfiguration::class])
class IncidentServiceTest {

    @Autowired
    lateinit var incidentRepository: IncidentRepository

    lateinit var incidentService: IncidentService
    lateinit var eventPublisher: ApplicationEventPublisher
    lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        eventPublisher = mockk(relaxUnitFun = true)
        userService = mockk()
        incidentService = IncidentService(incidentRepository, eventPublisher, userService)
    }

    @Test
    fun `should create incident`() {
        // given
        val incident = Incident(
                documentId = "documentId",
                createdDate = LocalDateTime.now(),
                location = pointOf(1, 9),
                description = "description",
                reporterId = 1
        )

        // when
        val id = incidentService.createIncident(incident, 7).id ?: fail("id not provided")

        // then
        val incidentFromDb = incidentRepository.getOne(id).also { it.id = null }
        assertThat(incidentFromDb).isEqualTo(incident)
    }

    @Test
    fun `should publish incident creation event`() {
        // given
        val incident = Incident(
                documentId = "documentId",
                createdDate = LocalDateTime.now(),
                location = pointOf(1, 9),
                description = "description",
                reporterId = 1
        )

        // when
        val id = incidentService.createIncident(incident, 7).id ?: fail("id not provided")

        // then
        val capturingSlot = CapturingSlot<IncidentCreatedEvent>()
        verify { eventPublisher.publishEvent(capture(capturingSlot)) }
        assertThat(capturingSlot.captured.incident.id).isEqualTo(id)
    }

    @Test
    fun `should get second page of incidents`() {
        val incidents = incidentService.getIncidents(pointOf(48.517607, 34.967458), PageRequest.of(1, 2))

        assertAll {
            assertThat(incidents.elementAt(0).description).isEqualTo("Brussel")
            assertThat(incidents.elementAt(1).description).isEqualTo("Cape Town")
        }
    }


    @Test
    fun `should get first page of incidents`() {
        val incidents = incidentService.getIncidents(pointOf(48.517607, 34.967458), PageRequest.of(0, 3))

        assertAll {
            assertThat(incidents.elementAt(0).description).isEqualTo("Zaporizhia")
            assertThat(incidents.elementAt(1).description).isEqualTo("Kyiv")
            assertThat(incidents.elementAt(2).description).isEqualTo("Brussel")
        }
    }
}
