package com.michaelfmnk.peterparker.userapi.domain.service

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.repository.IncidentRepository
import org.locationtech.jts.geom.Point
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class IncidentService(
        private val incidentRepository: IncidentRepository
) {

    fun createIncident(incident: Incident, userId: Long) {
        incidentRepository.save(incident)
    }

    fun getIncidents(center: Point, pageable: Pageable): Page<Incident> {
        return incidentRepository.findNearest(center, pageable)
    }


}
