package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.domain.service.JwtService
import com.michaelfmnk.peterparker.userapi.domain.service.UserService
import com.michaelfmnk.peterparker.userapi.rest.config.FixedClockConfig
import com.michaelfmnk.peterparker.userapi.rest.config.UnauthorizedEntryPoint
import com.ninjasquad.springmockk.MockkBean
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

@ContextConfiguration(classes = [ControllerTestApplication::class, FixedClockConfig::class])
@Import(value = [UnauthorizedEntryPoint::class, JwtService::class])
abstract class BaseControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    protected lateinit var userService: UserService

    @BeforeEach
    internal fun setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc)
    }
}
