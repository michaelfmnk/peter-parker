package com.michaelfmnk.peterparker.userapi.api

object Api {
    const val BASE_PATH = "/v1"
    const val VERSION = "/version"

    object Auth {
        const val LOGIN = "/auth/login"
        const val SIGN_UP = "/auth/sign-up"
        const val CODE = "/auth/confirm-code"
    }

}
