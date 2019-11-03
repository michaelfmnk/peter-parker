package com.michaelfmnk.peterparker.userapi.api.dto

import javax.validation.constraints.NotBlank

data class LoginRequest(
        @NotBlank
        val phone: String,
        @NotBlank
        val password: String
)