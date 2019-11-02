package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.rest.UserApiApplication
import com.michaelfmnk.peterparker.userapi.rest.config.FixedClockConfig
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

@ContextConfiguration(classes = [UserApiApplication::class, FixedClockConfig::class])
abstract class BaseControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc)
    }
}
