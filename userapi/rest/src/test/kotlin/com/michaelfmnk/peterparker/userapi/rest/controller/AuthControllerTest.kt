package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.CodeContainer
import com.michaelfmnk.peterparker.userapi.api.dto.LoginRequest
import com.michaelfmnk.peterparker.userapi.api.dto.SignUpRequest
import com.michaelfmnk.peterparker.userapi.domain.exception.BadCredentialsException
import com.michaelfmnk.peterparker.userapi.domain.exception.InvalidOtpException
import com.michaelfmnk.peterparker.userapi.domain.model.BasicUserInfo
import com.michaelfmnk.peterparker.userapi.domain.model.Token
import com.michaelfmnk.peterparker.userapi.domain.model.entity.RoleType
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.service.AuthService
import com.michaelfmnk.peterparker.userapi.rest.doing
import com.ninjasquad.springmockk.MockkBean
import io.mockk.called
import io.mockk.every
import io.mockk.verify
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.apache.http.HttpStatus
import org.hamcrest.Matchers
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(AuthController::class)
open class AuthControllerTest : BaseControllerTest() {

    @MockkBean
    lateinit var authService: AuthService

    @Nested
    inner class LoginTest : AuthControllerTest() {

        @Test
        fun `should not login user`() {
            val loginRequest = LoginRequest("test@test.com", "secret")

            every { authService.createToken(any(), any()) } throws BadCredentialsException()

            given()
                    .contentType(ContentType.JSON)
                    .body(loginRequest)
                    .doing()
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
                    .doing()
                    .post(Api.BASE_PATH + Api.Auth.LOGIN)
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("token", Matchers.equalTo("preparedToken"))
                    .body("user.userId", Matchers.equalTo(1))
        }
    }

    @Nested
    inner class SignUpTest : AuthControllerTest() {
        @Test
        fun `should sign up user`() {
            val signUpRequest = SignUpRequest("phone", "secret")

            every { authService.signUp(any(), any()) } returns User(
                    phone = "phone",
                    password = "pass",
                    role = RoleType.WATCHER.instance).apply { id = 100 }

            given()
                    .contentType(ContentType.JSON)
                    .body(signUpRequest)
                    .doing()
                    .post(Api.BASE_PATH + Api.Auth.SIGN_UP).prettyPeek()
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", Matchers.notNullValue())

        }

        @Test
        fun `should validate dto on sign up`() {
            val signUpRequest = SignUpRequest("   ", "secret")

            every { authService.signUp(any(), any()) } returns User(
                    phone = "phone",
                    password = "pass",
                    role = RoleType.WATCHER.instance).apply { id = 100 }

            given()
                    .contentType(ContentType.JSON)
                    .body(signUpRequest)
                    .doing()
                    .post(Api.BASE_PATH + Api.Auth.SIGN_UP).prettyPeek()
                    .then()
                    .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
                    .body("status", Matchers.equalTo(422))
                    .body("message", Matchers.equalTo("error.validation.failure"))
                    .body("errors[0].field", Matchers.equalTo("phone"))
                    .body("errors[0].reason", Matchers.equalTo("must not be blank"))



            verify { authService.signUp(any(), any()) wasNot called }
        }
    }

    @Nested
    inner class OtpTest : AuthControllerTest() {
        @Test
        fun `should check otp with success`() {
            every { authService.confirmOtp(any()) } returns Token("token", BasicUserInfo(
                    userId = 200
            ))

            given()
                    .contentType(ContentType.JSON)
                    .body(CodeContainer("code"))
                    .doing()
                    .post(Api.BASE_PATH + Api.Auth.CODE).prettyPeek()
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("token", Matchers.equalTo("token"))
                    .body("user.userId", Matchers.equalTo(200))


            verify { authService.confirmOtp("code") }
        }

        @Test
        fun `should check otp and fail`() {
            every { authService.confirmOtp(any()) } throws InvalidOtpException()

            given()
                    .contentType(ContentType.JSON)
                    .body(CodeContainer("code"))
                    .doing()
                    .post(Api.BASE_PATH + Api.Auth.CODE).prettyPeek()
                    .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("message", Matchers.equalTo("error.invalid.code"))
        }

        @Test
        fun `should not check blank otp`() {
            given()
                    .contentType(ContentType.JSON)
                    .body(CodeContainer("   "))
                    .doing()
                    .post(Api.BASE_PATH + Api.Auth.CODE).prettyPeek()
                    .then()
                    .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
                    .body("message", Matchers.equalTo("error.validation.failure"))

            verify { authService.confirmOtp(any()) wasNot called }
        }
    }

}
