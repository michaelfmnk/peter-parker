package com.michaelfmnk.peterparker.userapi.domain.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "auth")
data class AuthProperties(
        val privateKeyPath: String
)
