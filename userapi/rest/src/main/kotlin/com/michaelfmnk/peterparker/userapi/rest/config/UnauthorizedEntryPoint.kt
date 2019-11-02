package com.michaelfmnk.peterparker.userapi.rest.config

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class UnauthorizedEntryPoint : AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, ex: AuthenticationException?) {
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
    }
}
