package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.CodeContainer
import com.michaelfmnk.peterparker.userapi.api.dto.LoginRequest
import com.michaelfmnk.peterparker.userapi.api.dto.SignUpRequest
import com.michaelfmnk.peterparker.userapi.api.dto.SignUpResponseDto
import com.michaelfmnk.peterparker.userapi.domain.model.Token
import com.michaelfmnk.peterparker.userapi.domain.service.AuthService
import com.michaelfmnk.peterparker.userapi.rest.toSignUpResponseDto
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.BASE_PATH)
class AuthController(
        private val authService: AuthService
) {

    @PostMapping(Api.Auth.LOGIN)
    fun login(@Validated @RequestBody loginRequest: LoginRequest): Token {
        return authService.createToken(loginRequest.phone, loginRequest.password)
    }

    @PostMapping(Api.Auth.SIGN_UP)
    fun signUp(@Validated @RequestBody signUpRequest: SignUpRequest): SignUpResponseDto {
        return authService.signUp(signUpRequest.phone, signUpRequest.password).toSignUpResponseDto()
    }

    @PostMapping(Api.Auth.CODE)
    fun confirmCode(@Validated @RequestBody codeContainer: CodeContainer) {
        authService.confirmOtp(codeContainer.code)
    }
}
