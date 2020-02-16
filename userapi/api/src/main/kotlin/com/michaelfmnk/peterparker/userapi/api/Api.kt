package com.michaelfmnk.peterparker.userapi.api

object Api {
    const val BASE_PATH = "/v1"

    object Common {
        const val VERSION = "/version"
    }

    object Auth {
        const val LOGIN = "/auth/login"
        const val SIGN_UP = "/auth/sign-up"
        const val CODE = "/auth/confirm-code"
    }

    object Incidents {
        const val INCIDENTS = "/incidents"
        const val REPORTED_INCIDENTS = "/incidents/reported"
        const val OWN_INCIDENTS = "/incidents/own"
    }

}
