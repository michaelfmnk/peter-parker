package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.api.dto.VersionDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Clock
import java.time.LocalDateTime


@RestController
class VersionController {

    private final val clock: Clock

    constructor(clock: Clock) {
        print("CREATING")
        this.clock = clock
    }

    @GetMapping(Api.VERSION)
    fun getVersion() = VersionDto(LocalDateTime.now(clock).toString())

}
