package com.michaelfmnk.peterparker.userapi.domain.model

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import com.michaelfmnk.peterparker.userapi.domain.DomainTestConfiguration
import com.michaelfmnk.peterparker.userapi.domain.model.entity.RoleType
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.repository.OtpRepository
import com.michaelfmnk.peterparker.userapi.domain.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import java.util.*

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DomainTestConfiguration::class])
class UserTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var otpRepository: OtpRepository

    @Test
    fun `should save user`() {
        val user = User(password = "password", phone = "", role = RoleType.ADMIN.instance)

        userRepository.save(user)

        assertThat(user.id).isNotNull()
    }

    @Test
    fun `should save user with otp`() {
        val otpValue = UUID.randomUUID()
        val user = User(password = "password", phone = "", role = RoleType.ADMIN.instance)
        user.addOtp(otpValue)

        userRepository.save(user)

        assertThat(otpRepository.findById(otpValue).isPresent).isTrue()
    }
}
