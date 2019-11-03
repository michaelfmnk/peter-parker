package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.LoginRequest
import com.michaelfmnk.peterparker.userapi.domain.exception.BadCredentialsException
import com.michaelfmnk.peterparker.userapi.domain.model.BasicUserInfo
import com.michaelfmnk.peterparker.userapi.domain.model.Token
import com.michaelfmnk.peterparker.userapi.domain.service.AuthService
import com.michaelfmnk.peterparker.userapi.rest.performing
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.apache.http.HttpStatus
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(AuthController::class)
class AuthControllerTest : BaseControllerTest() {

    @MockkBean
    lateinit var authService: AuthService

    @Test
    fun `should not login user`() {
        val loginRequest = LoginRequest("test@test.com", "secret")

        every { authService.createToken(any(), any()) } throws BadCredentialsException()

        given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .performing()
                .post(Api.BASE_PATH + Api.Auth.LOGIN)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
    }

    @Test
    fun `should login user`() {
        val loginRequest = LoginRequest("test@test.com", "secret")

        every { authService.createToken(any(), any()) } returns Token("preparedToken", BasicUserInfo(1))

        given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .performing()
                .post(Api.BASE_PATH + Api.Auth.LOGIN)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("token", Matchers.equalTo("preparedToken"))
                .body("user.userId", Matchers.equalTo(1))
    }

}
