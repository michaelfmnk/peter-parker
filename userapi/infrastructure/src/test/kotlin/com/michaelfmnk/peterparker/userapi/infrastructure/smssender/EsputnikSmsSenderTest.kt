package com.michaelfmnk.peterparker.userapi.infrastructure.smssender

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.michaelfmnk.peterparker.userapi.infrastructure.smssender.model.EsputnikSms
import io.mockk.CapturingSlot
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class EsputnikSmsSenderTest {

    @MockK(relaxUnitFun = true)
    lateinit var restClient: EsputnikRestClient

    lateinit var seder: EsputnikSmsSender

    @BeforeEach
    fun `set up`() {
        MockKAnnotations.init(this)
        seder = EsputnikSmsSender(restClient)
    }

    @Test
    fun `should send sms`() {
        seder.sendSignUpCode("phone", "888777")


        val sms = CapturingSlot<EsputnikSms>()
        verify { restClient.sendSms(capture(sms)) }
        assertAll {
            assertThat(sms.isCaptured).isTrue()

            assertThat(sms.captured.from).isEqualTo("Peter Parker App")
            assertThat(sms.captured.text).isEqualTo("Thanks for signing up! Your code is 888777")
            assertThat(sms.captured.phoneNumbers).hasSize(1)
            assertThat(sms.captured.phoneNumbers[0]).isEqualTo("phone")
        }
    }

}
