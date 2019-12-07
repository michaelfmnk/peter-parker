package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.api.dto.PageDto
import com.michaelfmnk.peterparker.userapi.api.param.IncidentParams
import com.michaelfmnk.peterparker.userapi.domain.pointOf
import com.michaelfmnk.peterparker.userapi.domain.service.IncidentService
import com.michaelfmnk.peterparker.userapi.rest.config.UserAuthentication
import com.michaelfmnk.peterparker.userapi.rest.toDto
import com.michaelfmnk.peterparker.userapi.rest.toEntity
import com.michaelfmnk.peterparker.userapi.rest.toJpaPageable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Api.BASE_PATH)
class IncidentController(
        private val incidentService: IncidentService
) {

    @PostMapping(Api.Incidents.INCIDENTS)
    fun createIncident(@Validated @RequestBody incident: IncidentDto, auth: UserAuthentication) {
        incidentService.createIncident(incident.toEntity(), auth.userId)
    }

    @GetMapping(Api.Incidents.INCIDENTS)
    fun getIncidents(@Validated params: IncidentParams): PageDto<IncidentDto> {
        val userLocation = pointOf(params.iLat, params.iLng)

        val page = incidentService.getIncidents(userLocation, params.toJpaPageable())
        return page.toDto(userLocation)
    }
}


