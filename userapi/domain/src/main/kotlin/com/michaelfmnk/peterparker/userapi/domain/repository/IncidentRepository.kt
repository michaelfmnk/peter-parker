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

    @Query(nativeQuery = true,
            value = """SELECT * FROM incidents inc 
                WHERE inc.reporter_id = :userId
                ORDER BY st_distance_sphere(inc.location, POINT (:#{#point.x}, :#{#point.x})\:\:geometry)""",
            countQuery = "SELECT count(1) FROM incidents WHERE reporter_id = :userId")
    fun findNearestByReporter(@Param("point") point: Point, @Param("userId") userId: Long, pageable: Pageable): Page<Incident>

    @Query(nativeQuery = true,
            value = """SELECT * FROM incidents inc 
                WHERE STRPOS(plate_number, :plateNumber) > 0
                ORDER BY st_distance_sphere(inc.location, POINT (:#{#point.x}, :#{#point.x})\:\:geometry)""",
            countQuery = "SELECT count(1) FROM incidents WHERE STRPOS(plate_number, :plateNumber) > 0")
    fun findNearest(@Param("point") point: Point, @Param("plateNumber") plateNumber: String, pageable: Pageable): Page<Incident>

    fun findByPlateNumber(plateNumber: String, pageable: Pageable): Page<Incident>
}
