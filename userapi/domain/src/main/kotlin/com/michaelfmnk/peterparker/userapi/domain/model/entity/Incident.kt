package com.michaelfmnk.peterparker.userapi.domain.model.entity

import org.locationtech.jts.geom.Point
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "incidents")
class Incident(
        val documentId: String? = null,
        val createdDate: LocalDateTime? = null,
        @field:Column(columnDefinition = "geometry(Point,4326)")
        val location: Point,
        val description: String
) : JpaPersistable<Long>()

