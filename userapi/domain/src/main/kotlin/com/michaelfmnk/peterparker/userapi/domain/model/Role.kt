package com.michaelfmnk.peterparker.userapi.domain.model

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
        @Enumerated(EnumType.STRING)
        @Column(name = "api_key", updatable = false, insertable = false)
        val name: RoleType
) : JpaPersistable<Long>()

enum class RoleType {
    ADMIN, WATCHER, OFFICER
}
