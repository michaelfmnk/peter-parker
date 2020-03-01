package com.michaelfmnk.peterparker.userapi.domain.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicInsert
import org.locationtech.jts.geom.Point
import org.locationtech.spatial4j.context.SpatialContext
import org.locationtech.spatial4j.distance.DistanceUtils
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@DynamicInsert
@Table(name = "incidents")
class Incident(
        val documentId: String? = null,
        val plateNumber: String? = null,
        @CreationTimestamp
        val createdDate: LocalDateTime? = null,
        @field:Column(columnDefinition = "geometry(Point,4326)")
        val location: Point,
        val description: String,
        val reporterId: Long
) : JpaPersistable<Long>() {

    fun distanceTo(toPoint: Point): Double {
        val shapeFactory = SpatialContext.GEO.shapeFactory
        val from = shapeFactory.pointXY(location.y, location.x)
        val to = shapeFactory.pointXY(toPoint.x, toPoint.y)
        return SpatialContext.GEO.calcDistance(from, to) * DistanceUtils.DEG_TO_KM
    }
}

