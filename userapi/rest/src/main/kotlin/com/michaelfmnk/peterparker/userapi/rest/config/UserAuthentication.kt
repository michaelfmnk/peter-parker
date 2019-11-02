package com.michaelfmnk.peterparker.userapi.rest.config

import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserAuthentication(
        private val user: User
) : Authentication {

    private var authenticated: Boolean = user.enabled

    override fun getDetails() = user

    override fun isAuthenticated() = authenticated

    override fun getPrincipal() = user.id

    override fun getCredentials() = user.id

    override fun getName() = user.fullName

    override fun setAuthenticated(authenticated: Boolean) {
        this.authenticated = authenticated
    }

    override fun getAuthorities() = listOf(user.role)
            .map { SimpleGrantedAuthority(it.name.name) }
}
