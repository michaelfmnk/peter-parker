package com.michaelfmnk.peterparker.userapi.domain.service

import com.michaelfmnk.peterparker.userapi.domain.event.IncidentCreatedEvent
import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.repository.IncidentRepository
import org.locationtech.jts.geom.Point
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class IncidentService(
        private val incidentRepository: IncidentRepository,
        private val eventPublisher: ApplicationEventPublisher
) {

    fun createIncident(incident: Incident, userId: Long) = incidentRepository.save(incident).also {
        eventPublisher.publishEvent(IncidentCreatedEvent(this, it))
    }

    fun getIncidents(center: Point, pageable: Pageable, plateNumber: String? = null): Page<Incident> {
        return if (plateNumber == null) {
            incidentRepository.findNearest(center, pageable)
        } else {
            incidentRepository.findNearest(center, plateNumber, pageable)
        }
    }

    fun getReportedIncidents(userLocation: Point, userId: Long, pageable: PageRequest): Page<Incident> {
        return incidentRepository.findNearestByReporter(userLocation, userId, pageable)
    }

}
