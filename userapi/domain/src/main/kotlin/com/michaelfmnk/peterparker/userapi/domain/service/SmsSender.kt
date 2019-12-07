package com.michaelfmnk.peterparker.userapi.domain.service

import org.springframework.scheduling.annotation.Async

interface SmsSender {
    @Async
    fun sendSignUpCode(phone: String, code: String)

    @Async
    fun sendParkingWarning(phone: String, plateNumber: String)
}
