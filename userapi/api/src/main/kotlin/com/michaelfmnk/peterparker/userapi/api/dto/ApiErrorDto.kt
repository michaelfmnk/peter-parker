package com.michaelfmnk.peterparker.userapi.api.dto

import java.time.LocalDateTime

data class ApiErrorDto(
        val status: Int,
        val message: String,
        val timestamp: LocalDateTime,
        val endpoint: String,
        val errors: List<ApiErrorDetails> = emptyList()
)
