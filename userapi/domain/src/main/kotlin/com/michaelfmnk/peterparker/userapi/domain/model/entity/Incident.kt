package com.michaelfmnk.peterparker.userapi.domain.model.entity

import org.locationtech.jts.geom.Point
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "incidents")
class Incident(
        val documentId: String,
        val createdDate: LocalDateTime,
        @field:Column(columnDefinition = "geometry(Point,4326)")
        val location: Point
) : JpaPersistable<Long>()

