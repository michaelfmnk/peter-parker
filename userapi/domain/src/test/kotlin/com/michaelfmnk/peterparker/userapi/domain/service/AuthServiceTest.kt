package com.michaelfmnk.peterparker.userapi.domain.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.michaelfmnk.peterparker.userapi.domain.exception.BadCredentialsException
import com.michaelfmnk.peterparker.userapi.domain.model.entity.RoleType
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.repository.OtpRepository
import com.michaelfmnk.peterparker.userapi.domain.repository.UserRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder

class AuthServiceTest {
    val smsSender: SmsSender = mockk(relaxUnitFun = true)
    val jwtService: JwtService = mockk()
    val userRepository: UserRepository = mockk()
    val passwordEncoder: PasswordEncoder = mockk()
    val otpRepository: OtpRepository = mockk()

    lateinit var authService: AuthService

    @BeforeEach
    fun `set up`() {
        MockKAnnotations.init(this)
        authService = AuthService(smsSender, jwtService, otpRepository, CodeGenerator(), userRepository, passwordEncoder)
    }

    @Test
    fun `should create token`() {
        // given
        val user = User(
                phone = "08080808080",
                password = "HashedPassword",
                firstName = "firstName",
                lastName = "lastName",
                role = RoleType.ADMIN.instance
        ).apply { id = 10 }
        every { userRepository.findByPhone(any()) } returns user
        every { passwordEncoder.matches(any(), any()) } returns true
        every { jwtService.generateToken(any()) } returns "preparedToken"

        // when
        val token = authService.createToken("phone", "RawPassword")

        // then
        assertThat(token.token).isEqualTo("preparedToken")
        assertThat(token.user.userId).isEqualTo(10)

        verify { jwtService.generateToken(user) }
        verify { userRepository.findByPhone("phone") }
        verify { passwordEncoder.matches("RawPassword", "HashedPassword") }
    }

    @Test
    fun `should throw bad credentials`() {
        val user = User(
                phone = "08080808080",
                password = "HashedPassword",
                firstName = "firstName",
                lastName = "lastName",
                role = RoleType.ADMIN.instance
        ).apply { id = 10 }

        every { userRepository.findByPhone(any()) } returns user
        every { passwordEncoder.matches(any(), any()) } returns false

        Assertions.assertThrows(BadCredentialsException::class.java) {
            authService.createToken("phone", "RawPassword")
        }
    }

    @Test
    fun `should save user data on sign up`() {
        every { passwordEncoder.encode(any()) } returns "encoded"
        every { userRepository.save(any() as User) } answers {
            val user = invocation.args[0] as User
            user.id = 10L
            user
        }

        val signUp = authService.signUp("phone", "password")

        assertThat(signUp.id).isEqualTo(10L)
        val user = CapturingSlot<User>()
        verify { userRepository.save(capture(user)) }
        assertThat(user.captured.password).isEqualTo("encoded")
    }
}
