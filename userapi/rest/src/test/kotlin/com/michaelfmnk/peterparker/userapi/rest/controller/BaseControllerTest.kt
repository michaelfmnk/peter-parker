package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.domain.model.entity.RoleType
import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.service.JwtService
import com.michaelfmnk.peterparker.userapi.domain.service.UserService
import com.michaelfmnk.peterparker.userapi.rest.config.FixedClockConfig
import com.michaelfmnk.peterparker.userapi.rest.config.UnauthorizedEntryPoint
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.restassured.config.HeaderConfig
import io.restassured.config.LogConfig
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
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
    @Autowired
    private lateinit var jwtService: JwtService

    @MockkBean
    private lateinit var userService: UserService

    @BeforeEach
    internal fun setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc)
        RestAssuredMockMvc.config()
                .logConfig(LogConfig.logConfig()
                        .enablePrettyPrinting(true)
                        .enableLoggingOfRequestAndResponseIfValidationFails())
                .headerConfig(HeaderConfig.headerConfig()
                        .overwriteHeadersWithName("Accept", "application/json")
                        .overwriteHeadersWithName("Content-Type", "application/json"))
    }

    internal fun MockMvcRequestSpecification.jwtAuth() = jwtAuth(15, "0987777777")

    internal fun MockMvcRequestSpecification.jwtAuth(userId: Long, phone: String): MockMvcRequestSpecification {
        val user = User(
                phone = phone,
                enabled = true,
                role = RoleType.WATCHER.instance,
                password = "smth"
        ).apply { id = userId }

        every { userService.findUserById(userId) } returns user

        val token = jwtService.generateToken(user)
        return header("Authorization", "Bearer $token")
    }

}
