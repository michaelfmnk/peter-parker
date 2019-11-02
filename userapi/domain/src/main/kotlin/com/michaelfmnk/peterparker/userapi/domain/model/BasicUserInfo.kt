package com.michaelfmnk.peterparker.userapi.domain.model

import com.michaelfmnk.peterparker.userapi.domain.model.entity.User

data class BasicUserInfo(
        val userId: Long?
) {
    constructor(user: User) : this(user.id)
}
