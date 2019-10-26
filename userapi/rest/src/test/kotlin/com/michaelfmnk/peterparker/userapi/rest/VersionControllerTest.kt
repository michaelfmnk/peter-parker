package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.rest.controller.VersionController
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

@ContextConfiguration(classes = [UserApiApplication::class, FixedClockConfig::class])
@WebMvcTest(VersionController::class)
class VersionControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc)
    }

    @Test
    fun `should return version`() {
        given()
                .`when`()
                .get("/version").prettyPeek()
                .then()
                .statusCode(200)
                .body("version", Matchers.equalTo("2007-12-03T12:15:30"))
    }

}
