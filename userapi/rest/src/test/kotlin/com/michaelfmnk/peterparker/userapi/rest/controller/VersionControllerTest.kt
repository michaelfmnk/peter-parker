package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.rest.doing
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(VersionController::class)
class VersionControllerTest : BaseControllerTest() {

    @Test
    fun `should return version`() {
        given()
                .doing()
                .get("/version").prettyPeek()
                .then()
                .statusCode(200)
                .body("version", Matchers.equalTo("2007-12-03T10:15:30"))
    }

}
