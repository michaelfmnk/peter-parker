package com.michaelfmnk.peterparker.userapi.api.dto

import java.time.LocalDateTime

data class ErrorDto(
        val status: Int,
        val message: String,
        val timestamp: LocalDateTime
)
