package com.michaelfmnk.peterparker.userapi.domain.service

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.michaelfmnk.peterparker.userapi.domain.DomainTestConfiguration
import com.michaelfmnk.peterparker.userapi.domain.pointOf
import com.michaelfmnk.peterparker.userapi.domain.repository.IncidentRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup


@DataJpaTest
@SqlGroup(value = [Sql(value = ["classpath:clear.sql"]), Sql(value = ["classpath:incidents.sql"])])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DomainTestConfiguration::class])
internal class IncidentServiceTest {

    @Autowired
    lateinit var incidentRepository: IncidentRepository

    lateinit var incidentService: IncidentService

    @BeforeEach
    internal fun setUp() {
        incidentService = IncidentService(incidentRepository)
    }


    @Test
    internal fun `should get second page of incidents`() {
        val incidents = incidentService.getIncidents(pointOf(48.517607, 34.967458), PageRequest.of(1, 2))

        assertAll {
            assertThat(incidents.elementAt(0).description).isEqualTo("Brussel")
            assertThat(incidents.elementAt(1).description).isEqualTo("Cape Town")
        }
    }


    @Test
    internal fun `should get first page of incidents`() {
        val incidents = incidentService.getIncidents(pointOf(48.517607, 34.967458), PageRequest.of(0, 3))

        assertAll {
            assertThat(incidents.elementAt(0).description).isEqualTo("Zaporizhia")
            assertThat(incidents.elementAt(1).description).isEqualTo("Kyiv")
            assertThat(incidents.elementAt(2).description).isEqualTo("Brussel")
        }
    }
}