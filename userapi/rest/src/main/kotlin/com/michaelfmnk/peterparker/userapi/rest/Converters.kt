package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.api.dto.LocationDto
import com.michaelfmnk.peterparker.userapi.api.dto.SignUpResponseDto
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import org.locationtech.jts.geom.Point

fun Incident.toDto() = IncidentDto(id, createdDate, location.toDto())

fun Point.toDto() = LocationDto(x, y)

fun User.toSignUpResponseDto() = SignUpResponseDto(
        id = this.id!!,
        phone = phone
)
