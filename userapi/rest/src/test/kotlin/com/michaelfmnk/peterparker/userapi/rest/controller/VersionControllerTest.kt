package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.rest.performing
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(VersionController::class)
class VersionControllerTest : BaseControllerTest() {

    @Test
    fun `should return version`() {
        given()
                .performing()
                .get("/version").prettyPeek()
                .then()
                .statusCode(200)
                .body("version", Matchers.equalTo("2007-12-03T12:15:30"))
    }

}