package com.michaelfmnk.peterparker.userapi.api.dto

import javax.validation.constraints.NotBlank

data class SignUpRequest(
        @field:NotBlank
        val phone: String,
        val password: String
)
