package com.michaelfmnk.peterparker.userapi.api.dto

interface ApiErrorDetails

data class ValidationError(
        val field: String,
        val rejectedValue: Any? = null,
        val reason: String? = null
) : ApiErrorDetails
