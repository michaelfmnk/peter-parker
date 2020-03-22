package com.michaelfmnk.peterparker.userapi.api.dto

import javax.validation.constraints.NotBlank

data class PlateNumberDto(
        @field:NotBlank
        val plateNumber: String
)