package com.michaelfmnk.peterparker.userapi.api.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Null

data class IncidentDto(
        @Null
        var id: Long? = null,
        var createdDate: LocalDateTime,
        var location: LocationDto,
        @NotBlank
        var description: String
)
