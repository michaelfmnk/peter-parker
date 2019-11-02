package com.michaelfmnk.peterparker.userapi.domain.model.entity

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
        val password: String,
        val phone: String? = null,
        @ManyToOne
        @JoinColumn(name = "role_id")
        val role: Role,
        var enabled: Boolean = false,
        var lastName: String? = null,
        var firstName: String? = null
) : JpaPersistable<Long>() {

    val fullName: String?
        get() = if (lastName != null && firstName != null) "$firstName $lastName" else null
}
