package com.michaelfmnk.peterparker.userapi.rest.controller

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.api.dto.LocationDto
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.service.IncidentService
import com.michaelfmnk.peterparker.userapi.rest.doing
import com.ninjasquad.springmockk.MockkBean
import io.mockk.CapturingSlot
import io.mockk.verify
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.apache.http.HttpStatus
import org.hamcrest.Matchers
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(IncidentController::class)
class IncidentControllerTest : BaseControllerTest() {

    @MockkBean(relaxUnitFun = true)
    lateinit var incidentService: IncidentService

    @Nested
    inner class CreateIncidentTest {

        @Test
        fun `should create incident`() {
            val incident = IncidentDto(
                    location = LocationDto(400.0, 300.9),
                    description = "my own description"
            )

            RestAssuredMockMvc.given()
                    .jwtAuth(15, "test@email.com")
                    .contentType(ContentType.JSON)
                    .body(incident)
                    .doing()
                    .post(Api.BASE_PATH + Api.Incidents.INCIDENTS)
                    .then()
                    .statusCode(HttpStatus.SC_OK)

            val incidentSlot = CapturingSlot<Incident>()
            verify { incidentService.createIncident(capture(incidentSlot), eq(15)) }

            assertAll {
                assertThat(incidentSlot.captured.location.x).isEqualTo(400.0)
                assertThat(incidentSlot.captured.location.y).isEqualTo(300.9)
            }
        }

        @Test
        fun `should check auth before creating`() {
            val incident = IncidentDto(
                    location = LocationDto(400.0, 300.9),
                    description = "my own description"
            )

            RestAssuredMockMvc.given()
                    .contentType(ContentType.JSON)
                    .body(incident)
                    .doing()
                    .post(Api.BASE_PATH + Api.Incidents.INCIDENTS).prettyPeek()
                    .then()
                    .statusCode(HttpStatus.SC_UNAUTHORIZED)
        }

        @Test
        fun `should validate incident`() {
            val incident = IncidentDto(
                    id = 5,
                    location = LocationDto(400.0, 300.9),
                    description = "  "
            )

            RestAssuredMockMvc.given()
                    .jwtAuth()
                    .contentType(ContentType.JSON)
                    .body(incident)
                    .doing()
                    .post(Api.BASE_PATH + Api.Incidents.INCIDENTS).prettyPeek()
                    .then()
                    .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
                    .body("errors.field", Matchers.containsInAnyOrder("description", "id"))
                    .body("errors.reason", Matchers.containsInAnyOrder("must be null", "must not be blank"))
        }
    }

}
