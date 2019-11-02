package com.michaelfmnk.peterparker.userapi.domain.model.entity

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
        @Enumerated(EnumType.STRING)
        @Column(name = "api_key", updatable = false, insertable = false)
        val name: RoleType
) : JpaPersistable<Long>()

enum class RoleType(private val id: Long) : TypeCreator<Role> {
    ADMIN(1),
    WATCHER(2),
    OFFICER(3);

    override val instance: Role
        get() = Role(this).also { it.id = this.id }
}
