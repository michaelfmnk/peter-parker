package com.michaelfmnk.peterparker.userapi.api.param

interface Sorting {
    val asc: Boolean
    val sort: Enum<*>
}