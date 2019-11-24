package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.domain.service.IncidentService
import com.michaelfmnk.peterparker.userapi.rest.config.UserAuthentication
import com.michaelfmnk.peterparker.userapi.rest.toEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.BASE_PATH)
class IncidentController(
        private val incidentService: IncidentService
) {

    @PostMapping(Api.Incidents.INCIDENTS)
    fun createIncident(@Validated @RequestBody incident: IncidentDto, auth: UserAuthentication) {
        incidentService.createIncident(incident.toEntity(), auth.userId)
    }
}


