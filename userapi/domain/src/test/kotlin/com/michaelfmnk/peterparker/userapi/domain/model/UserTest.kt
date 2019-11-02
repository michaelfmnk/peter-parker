package com.michaelfmnk.peterparker.userapi.domain.model

import assertk.assertThat
import assertk.assertions.isNotNull
import com.michaelfmnk.peterparker.userapi.domain.DomainTestConfiguration
import com.michaelfmnk.peterparker.userapi.domain.model.entity.RoleType
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DomainTestConfiguration::class])
class UserTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `should save user`() {
        val user = User(password = "password", phone = "", role = RoleType.ADMIN.instance)

        userRepository.save(user)

        assertThat(user.id).isNotNull()
    }
}
