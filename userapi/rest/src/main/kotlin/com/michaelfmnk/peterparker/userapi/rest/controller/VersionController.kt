package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.VersionDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Clock
import java.time.LocalDateTime


@RestController
class VersionController(private val clock: Clock) {

    @GetMapping(Api.Common.VERSION)
    fun getVersion() = VersionDto(LocalDateTime.now(clock).toString())

}
