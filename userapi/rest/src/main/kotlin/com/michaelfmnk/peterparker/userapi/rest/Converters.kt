package com.michaelfmnk.peterparker.userapi.rest

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident

fun Incident.toDto() = IncidentDto(id, createdDate)
