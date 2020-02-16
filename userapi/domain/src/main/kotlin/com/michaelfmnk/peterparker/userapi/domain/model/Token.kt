package com.michaelfmnk.peterparker.userapi.domain.model

import com.michaelfmnk.peterparker.userapi.domain.model.entity.RoleType

data class Token(
        val token: String,
        val user: BasicUserInfo,
        val role: RoleType
)
