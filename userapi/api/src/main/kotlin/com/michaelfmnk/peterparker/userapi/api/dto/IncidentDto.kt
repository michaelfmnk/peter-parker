package com.michaelfmnk.peterparker.userapi.api.dto

import java.time.LocalDateTime

data class IncidentDto(
        var id: Long? = null,
        var createdDate: LocalDateTime
)
