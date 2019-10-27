package com.michaelfmnk.peterparker.userapi.domain.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "incidents")
class Incident(
        val documentId: String,
        val createdDate: LocalDateTime
) : JpaPersistable<Long>()

