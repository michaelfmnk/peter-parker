package com.michaelfmnk.peterparker.userapi.domain.listener

import com.michaelfmnk.peterparker.userapi.domain.event.IncidentCreatedEvent
import com.michaelfmnk.peterparker.userapi.domain.exception.EntityNotFoundException
import com.michaelfmnk.peterparker.userapi.domain.service.SmsSender
import com.michaelfmnk.peterparker.userapi.domain.service.UserService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class IncidentEventListener(
        private val smsSender: SmsSender,
        private val userService: UserService
) {

    @EventListener
    fun onIncidentCreated(incidentCreatedEvent: IncidentCreatedEvent) {
        incidentCreatedEvent.incident.plateNumber?.let { plate ->
            val user = try {
                userService.findUserByPlateNumber(plate)
            } catch (e: EntityNotFoundException) {
                null
            }

            user?.let {
                smsSender.sendParkingWarning(it.phone, plate)
            }
        }
    }
}
