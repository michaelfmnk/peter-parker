package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.dto.ErrorDto
import com.michaelfmnk.peterparker.userapi.domain.exception.BadCredentialsException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDateTime

@ControllerAdvice
class ErrorHandlingController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handle(ex: RuntimeException) = ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal server error", LocalDateTime.now())

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handle(ex: BadCredentialsException) = ErrorDto(HttpStatus.UNAUTHORIZED.value(), "bad credentials", LocalDateTime.now())
}
