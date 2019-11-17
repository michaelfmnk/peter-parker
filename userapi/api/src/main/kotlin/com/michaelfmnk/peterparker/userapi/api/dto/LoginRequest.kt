package com.michaelfmnk.peterparker.userapi.api.dto

import javax.validation.constraints.NotBlank

data class LoginRequest(
        @field:NotBlank
        val phone: String,
        @field:NotBlank
        val password: String
)
