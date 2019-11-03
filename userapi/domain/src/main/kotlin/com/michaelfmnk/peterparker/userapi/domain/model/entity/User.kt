package com.michaelfmnk.peterparker.userapi.domain.model.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        val phone: String,
        val password: String,
        @ManyToOne
        @JoinColumn(name = "role_id")
        val role: Role,
        var enabled: Boolean = false,
        var lastName: String? = null,
        var firstName: String? = null,
        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
        val otps: MutableList<Otp> = mutableListOf()
) : JpaPersistable<Long>() {

    val fullName: String?
        get() = if (lastName != null && firstName != null) "$firstName $lastName" else null

    fun addOtp(otpValue: UUID) {
        val otp = Otp(
                value = otpValue,
                date = LocalDateTime.now(),
                user = this
        )
        otps.add(otp)
    }
}
