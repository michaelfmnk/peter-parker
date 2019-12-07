package com.michaelfmnk.peterparker.userapi.domain.event

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Incident
import org.springframework.context.ApplicationEvent

class IncidentCreatedEvent(source: Any, val incident: Incident) : ApplicationEvent(source)
