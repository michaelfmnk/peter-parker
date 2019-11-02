package com.michaelfmnk.peterparker.userapi.domain.service

import com.michaelfmnk.peterparker.userapi.domain.exception.BadCredentialsException
import com.michaelfmnk.peterparker.userapi.domain.model.BasicUserInfo
import com.michaelfmnk.peterparker.userapi.domain.model.Token
import com.michaelfmnk.peterparker.userapi.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
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
}
