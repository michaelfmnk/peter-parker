package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.LoginRequest
import com.michaelfmnk.peterparker.userapi.domain.service.AuthService
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
    fun login(@RequestBody loginRequest: LoginRequest) = authService.createToken(loginRequest.phone, loginRequest.password)
}
