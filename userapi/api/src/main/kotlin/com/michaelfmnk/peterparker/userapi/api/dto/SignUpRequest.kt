package com.michaelfmnk.peterparker.userapi.api.dto

import javax.validation.constraints.NotBlank

data class SignUpRequest(
        @NotBlank
        val phone: String,
        val password: String
)
