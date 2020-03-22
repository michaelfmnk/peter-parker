package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.PlateNumberDto
import com.michaelfmnk.peterparker.userapi.domain.service.UserService
import com.michaelfmnk.peterparker.userapi.rest.config.UserAuthentication
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(Api.BASE_PATH)
class SettingsController(
        private val userService: UserService
) {

    @PatchMapping(Api.Settings.PLATE_NUM)
    fun updatePlateNumber(@RequestBody @Valid plateNumberDto: PlateNumberDto, auth: UserAuthentication) {
        userService.updatePlate(auth.userId, plateNumberDto.plateNumber)
    }

}