package com.michaelfmnk.peterparker.userapi.domain.model

import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
abstract class JpaPersistable<T : Serializable> {
    companion object {
        private val serialVersionUID = -5554308939380869754L
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = null

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as JpaPersistable<*>

        return if (null == id) false else id == other.id
    }

    override fun hashCode() = 31

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}
