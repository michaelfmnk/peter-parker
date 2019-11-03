package com.michaelfmnk.peterparker.userapi.infrastructure.config

import com.michaelfmnk.peterparker.userapi.infrastructure.RestClientProperties
import com.michaelfmnk.peterparker.userapi.infrastructure.smssender.EsputnikRestClient
import feign.Feign
import feign.auth.BasicAuthRequestInterceptor
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RestClientConfig(
        private val restProperties: RestClientProperties
) {
    @Bean
    fun esputnikRestClient(): EsputnikRestClient {
        val authInterceptor = BasicAuthRequestInterceptor(
                restProperties.esputnikUsername,
                restProperties.esputnikPassword
        )

        return Feign.builder()
                .requestInterceptor(authInterceptor)
                .encoder(JacksonEncoder())
                .decoder(JacksonDecoder())
                .target(EsputnikRestClient::class.java, restProperties.esputnikUrl)
    }
}
