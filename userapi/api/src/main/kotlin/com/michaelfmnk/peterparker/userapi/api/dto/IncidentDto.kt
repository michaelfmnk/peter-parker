package com.michaelfmnk.peterparker.userapi.api.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Null

data class IncidentDto(
        @field:Null
        var id: Long? = null,
        @field:Null
        var createdDate: LocalDateTime? = null,
        var location: LocationDto,
        @field:NotBlank
        var description: String
)
