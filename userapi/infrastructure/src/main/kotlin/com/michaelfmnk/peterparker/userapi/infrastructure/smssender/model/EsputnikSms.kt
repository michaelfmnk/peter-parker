package com.michaelfmnk.peterparker.userapi.infrastructure.smssender.model

data class EsputnikSms(
        val from: String,
        val text: String,
        val phoneNumbers: List<String> = emptyList()
)
