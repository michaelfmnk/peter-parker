package com.michaelfmnk.peterparker.userapi.infrastructure.smssender

import com.michaelfmnk.peterparker.userapi.domain.service.SmsSender
import com.michaelfmnk.peterparker.userapi.infrastructure.smssender.model.EsputnikSms
import org.springframework.stereotype.Component

@Component
class EsputnikSmsSender(
        private val esputnikRestClient: EsputnikRestClient
) : SmsSender {

    override fun sendSignUpCode(phone: String, code: String) {
        val sms = EsputnikSms(
                from = "Peter Parker App",
                phoneNumbers = listOf(phone),
                text = "Thanks for signing up! Your code is $code"
        )

        esputnikRestClient.sendSms(sms)
    }
}
