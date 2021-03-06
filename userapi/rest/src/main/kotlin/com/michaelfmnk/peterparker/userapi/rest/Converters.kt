package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.api.dto.LocationDto
import com.michaelfmnk.peterparker.userapi.api.dto.PageDto
import com.michaelfmnk.peterparker.userapi.api.dto.SignUpResponseDto
import com.michaelfmnk.peterparker.userapi.api.param.Pagination
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.pointOf
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

fun Incident.toDto() = IncidentDto(
        id = id,
        createdDate = createdDate!!,
        location = location.toDto(),
        description = description,
        photo = documentId
)

fun Point.toDto() = LocationDto(x, y)

fun Page<Incident>.toDto(relativeTo: Point?): PageDto<IncidentDto> {
    val converted = content.map { incident ->
        val dto = incident.toDto()
        relativeTo?.let { point -> dto.distance = incident.distanceTo(point) }
        dto
    }
    return PageDto(totalElements, number.toLong(), size.toLong(), converted)
}

fun User.toSignUpResponseDto() = SignUpResponseDto(
        id = this.id!!,
        phone = phone
)

fun IncidentDto.toEntity(userId: Long): Incident = Incident(
        location = GeometryFactory().createPoint(Coordinate(location.lat, location.lng)),
        description = description,
        reporterId = userId,
        documentId = photo
)

fun Pagination.toJpaPageable(): PageRequest = PageRequest.of(page, size)

fun LocationDto.toPoint() = pointOf(lat, lng)

