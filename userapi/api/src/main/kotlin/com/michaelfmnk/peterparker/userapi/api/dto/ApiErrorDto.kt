package com.michaelfmnk.peterparker.userapi.api.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class ApiErrorDto(
        val status: Int,
        val message: String,
        val timestamp: LocalDateTime,
        val endpoint: String,
        @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
        val errors: List<ApiErrorDetails> = emptyList()
)
