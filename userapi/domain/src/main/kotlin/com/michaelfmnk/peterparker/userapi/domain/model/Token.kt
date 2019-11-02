package com.michaelfmnk.peterparker.userapi.domain.model

data class Token(
        val token: String,
        val user: BasicUserInfo
)
