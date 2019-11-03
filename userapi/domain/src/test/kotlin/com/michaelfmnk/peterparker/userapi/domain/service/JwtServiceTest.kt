package com.michaelfmnk.peterparker.userapi.domain.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import assertk.assertions.isTrue
import com.michaelfmnk.peterparker.userapi.domain.model.entity.RoleType
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.property.AuthProperties
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JwtServiceTest {

    lateinit var jwtService: JwtService

    @BeforeEach
    fun `set up`() {
        jwtService = JwtService(AuthProperties("classpath:fake-privatekey.der")).apply { init() }
    }

    @Test
    fun `should create token`() {
        val user = User(
                phone = "08080808080",
                password = "password",
                firstName = "firstName",
                lastName = "lastName",
                role = RoleType.ADMIN.instance
        ).apply { id = 10 }

        val createdToken = jwtService.generateToken(user)

        println("createdToken = $createdToken")
        assertThat(createdToken).isNotEmpty()
    }

    @Test
    fun `should get phone from token`() {
        val user = User(
                phone = "08080808080",
                password = "password",
                firstName = "firstName",
                lastName = "lastName",
                role = RoleType.ADMIN.instance
        ).apply { id = 10 }

        val createdToken = jwtService.generateToken(user)
        val phoneFromToken = jwtService.getPhoneFromToken(createdToken)

        assertThat(phoneFromToken).isEqualTo(user.phone)
    }

    @Test
    fun `should get userId from token`() {
        val user = User(
                phone = "08080808080",
                password = "password",
                firstName = "firstName",
                lastName = "lastName",
                role = RoleType.ADMIN.instance
        ).apply { id = 10 }

        val createdToken = jwtService.generateToken(user)
        val userIdFromToken = jwtService.getUserIdFromToken(createdToken)

        assertThat(userIdFromToken).isEqualTo(user.id)
    }

    @Test
    fun `should recognize token as valid`() {
        val user = User(
                phone = "080808080",
                password = "password",
                firstName = "firstName",
                lastName = "lastName",
                role = RoleType.ADMIN.instance,
                enabled = true
        ).apply { id = 10 }
        val createdToken = jwtService.generateToken(user)

        val isValid = jwtService.isTokenValid(createdToken, user)

        assertThat(isValid).isTrue()
    }
}
