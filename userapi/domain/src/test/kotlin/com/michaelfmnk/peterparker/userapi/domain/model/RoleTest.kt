package com.michaelfmnk.peterparker.userapi.domain.model

import assertk.assertThat
import assertk.assertions.containsAll
import com.michaelfmnk.peterparker.userapi.domain.DomainTestConfiguration
import com.michaelfmnk.peterparker.userapi.domain.repository.RoleRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DomainTestConfiguration::class])
class RoleTest {

    @Autowired
    lateinit var roleRepository: RoleRepository


    @Test
    fun `should return all roles`() {
        val allRoles = roleRepository.findAll()

        assertThat(allRoles.map { it.name }).containsAll(*RoleType.values())
    }

}
