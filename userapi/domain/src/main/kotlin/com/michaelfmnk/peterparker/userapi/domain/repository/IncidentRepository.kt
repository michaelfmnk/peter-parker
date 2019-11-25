package com.michaelfmnk.peterparker.userapi.domain.repository

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import org.locationtech.jts.geom.Point
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface IncidentRepository : JpaRepository<Incident, Long> {

    @Query(nativeQuery = true,
            value = """SELECT * FROM incidents inc 
                ORDER BY st_distance_sphere(inc.location, POINT (:#{#point.x}, :#{#point.x})\:\:geometry)""",
            countQuery = "SELECT count(1) FROM incidents")
    fun findNearest(@Param("point") point: Point, pageable: Pageable): Page<Incident>
}
