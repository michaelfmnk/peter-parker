package com.michaelfmnk.peterparker.userapi.domain.service

import com.michaelfmnk.peterparker.userapi.domain.exception.BadCredentialsException
import com.michaelfmnk.peterparker.userapi.domain.model.BasicUserInfo
import com.michaelfmnk.peterparker.userapi.domain.model.Token
import com.michaelfmnk.peterparker.userapi.domain.model.entity.RoleType
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class AuthService(
        private val smsSender: SmsSender,
        private val jwtService: JwtService,
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder
) {

    fun createToken(phone: String, password: String): Token {
        val user = userRepository.findByPhone(phone) ?: throw BadCredentialsException()
        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException()
        }

        val jwtToken = jwtService.generateToken(user)
        return Token(jwtToken, BasicUserInfo(user))
    }

    @Transactional
    fun signUp(phone: String, password: String): User {
        val user = User(
                phone = phone,
                enabled = false,
                role = RoleType.WATCHER.instance,
                password = passwordEncoder.encode(password)
        )
        val otpValue = UUID.randomUUID()
        user.addOtp(otpValue)

        smsSender.sendSignUpCode(phone = phone, code = otpValue.toString())
        return userRepository.save(user)
    }
}
