package com.michaelfmnk.peterparker.userapi.domain.service

import org.springframework.stereotype.Component

@Component
class CodeGenerator {
    fun generateNumeric(length: Int = 6): String {
        val numbers = ('0'..'9')
        return List(length) { numbers.random() }.joinToString("")
    }
}
