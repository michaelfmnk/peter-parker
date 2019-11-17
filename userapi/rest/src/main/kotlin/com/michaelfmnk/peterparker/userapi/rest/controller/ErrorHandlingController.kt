package com.michaelfmnk.peterparker.userapi.rest.controller

import com.michaelfmnk.peterparker.userapi.api.dto.ApiErrorDetails
import com.michaelfmnk.peterparker.userapi.api.dto.ApiErrorDto
import com.michaelfmnk.peterparker.userapi.api.dto.ValidationError
import com.michaelfmnk.peterparker.userapi.domain.exception.BadCredentialsException
import com.michaelfmnk.peterparker.userapi.domain.exception.InvalidOtpException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest


@ResponseBody
@ControllerAdvice
class ErrorHandlingController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handle(ex: RuntimeException, request: HttpServletRequest): ApiErrorDto {
        return ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error.internal.server", LocalDateTime.now(), request.requestURI)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handle(ex: BadCredentialsException, request: HttpServletRequest): ApiErrorDto {
        return ApiErrorDto(HttpStatus.UNAUTHORIZED.value(), "error.bad.credentials", LocalDateTime.now(), request.requestURI)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(ex: InvalidOtpException, request: HttpServletRequest): ApiErrorDto {
        return ApiErrorDto(HttpStatus.BAD_REQUEST.value(), "error.invalid.code", LocalDateTime.now(), request.requestURI)
    }

    @ExceptionHandler
    fun handleException(exception: BindException, request: HttpServletRequest): ResponseEntity<ApiErrorDto> {
        return createApiErrorWithBindingResult(request, exception.bindingResult)
    }

    @ExceptionHandler
    fun handleException(exception: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ApiErrorDto> {
        return createApiErrorWithBindingResult(request, exception.bindingResult)
    }

    private fun createApiErrorWithBindingResult(request: HttpServletRequest, bindingResult: BindingResult): ResponseEntity<ApiErrorDto> {
        val apiError = ApiErrorDto(
                timestamp = LocalDateTime.now(),
                message = "error.validation.failure",
                status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
                endpoint = request.requestURI,
                errors = createErrorDetailsList(bindingResult)
        )

        return ResponseEntity(apiError, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    private fun createErrorDetailsList(bindingResult: BindingResult): List<ApiErrorDetails> {
        return bindingResult.fieldErrors
                .map {
                    ValidationError(
                            field = it.field,
                            rejectedValue = it.rejectedValue,
                            reason = it.defaultMessage
                    )
                }

    }
}
