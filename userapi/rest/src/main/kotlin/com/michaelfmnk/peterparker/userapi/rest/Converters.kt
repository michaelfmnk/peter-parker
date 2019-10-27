package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.api.dto.IncidentDto
import com.michaelfmnk.peterparker.userapi.domain.model.Incident

fun Incident.toDto() = IncidentDto(id, createdDate)
