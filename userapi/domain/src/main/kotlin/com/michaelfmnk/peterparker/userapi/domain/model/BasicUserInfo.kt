package com.michaelfmnk.peterparker.userapi.domain.model

import com.michaelfmnk.peterparker.userapi.domain.model.entity.User

data class BasicUserInfo(
        val userId: Long?,
        val firstName: String?,
        val lastName: String?,
        val phone: String?
) {
    constructor(user: User) : this(user.id, user.firstName, user.lastName, user.phone)
}
