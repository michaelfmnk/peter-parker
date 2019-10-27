package com.michaelfmnk.peterparker.userapi.domain.repository

import com.michaelfmnk.peterparker.userapi.domain.model.Incident
import org.springframework.data.jpa.repository.JpaRepository

interface IncidentRepository : JpaRepository<Incident, Long>
