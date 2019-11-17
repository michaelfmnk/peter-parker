package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.domain.service.IncidentService
import com.michaelfmnk.peterparker.userapi.rest.config.UserAuthentication
import com.michaelfmnk.peterparker.userapi.rest.toEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class IncidentController(
        private val incidentService: IncidentService
) {

    @GetMapping
    fun createIncident(@Valid @RequestBody incident: IncidentDto, auth: UserAuthentication) {
        incidentService.createIncident(incident.toEntity(), auth.userId)
    }
}


