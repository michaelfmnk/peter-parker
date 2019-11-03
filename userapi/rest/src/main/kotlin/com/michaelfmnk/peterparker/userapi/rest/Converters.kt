package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.api.dto.SignUpResponseDto
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User

fun Incident.toDto() = IncidentDto(id, createdDate)

fun User.toSignUpResponseDto() = SignUpResponseDto(
        id = this.id!!,
        phone = phone
)
