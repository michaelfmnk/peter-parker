package com.michaelfmnk.peterparker.userapi.domain.model.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "otps")
class Otp(
        @Id
        val value: UUID,
        val date: LocalDateTime,
        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User
) {
    override fun toString() = "Entity of type ${this.javaClass.name} with id: $value"

    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Otp

        if (value != other.value) return false

        return true
    }

}

