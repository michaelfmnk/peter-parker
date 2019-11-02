package com.michaelfmnk.peterparker.userapi.domain.service

import com.michaelfmnk.peterparker.userapi.domain.exception.EntityNotFoundException
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun findUserById(userId: Long): User = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("user $userId was not found") }

}
