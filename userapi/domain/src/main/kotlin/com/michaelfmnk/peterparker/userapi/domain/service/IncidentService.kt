package com.michaelfmnk.peterparker.userapi.domain.service

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import com.michaelfmnk.peterparker.userapi.domain.repository.IncidentRepository
import org.springframework.stereotype.Service

@Service
class IncidentService(
        private val incidentRepository: IncidentRepository
) {

    fun createIncident(incident: Incident, userId: Long) {
        incidentRepository.save(incident)
    }


}
