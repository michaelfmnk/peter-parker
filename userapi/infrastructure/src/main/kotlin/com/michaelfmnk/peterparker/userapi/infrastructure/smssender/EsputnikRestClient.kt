package com.michaelfmnk.peterparker.userapi.infrastructure.smssender

import com.michaelfmnk.peterparker.userapi.infrastructure.smssender.model.EsputnikSms
import feign.RequestLine

interface EsputnikRestClient {
    @RequestLine("POST /v1/message/sms")
    fun sendSms(sms: EsputnikSms)
}
