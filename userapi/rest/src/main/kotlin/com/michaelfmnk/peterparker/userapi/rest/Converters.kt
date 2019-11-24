package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.api.dto.LocationDto
import com.michaelfmnk.peterparker.userapi.api.dto.SignUpResponseDto
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point

fun Incident.toDto() = IncidentDto(id, createdDate!!, location.toDto(), description)

fun Point.toDto() = LocationDto(x, y)

fun User.toSignUpResponseDto() = SignUpResponseDto(
        id = this.id!!,
        phone = phone
)

fun IncidentDto.toEntity(): Incident = Incident(
        location = GeometryFactory().createPoint(Coordinate(location.lat, location.lng)),
        description = description
)
