package com.michaelfmnk.peterparker.userapi.domain.config

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder : BCryptPasswordEncoder()
