package com.michaelfmnk.peterparker.userapi.infrastructure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "rest")
data class RestClientProperties(
        val esputnikUrl: String,
        val esputnikUsername: String,
        val esputnikPassword: String
)
