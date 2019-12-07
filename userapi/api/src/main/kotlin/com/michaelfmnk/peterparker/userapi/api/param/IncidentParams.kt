package com.michaelfmnk.peterparker.userapi.api.param

import javax.validation.constraints.NotNull


class IncidentParams(override val size: Int = 10,
                     override val page: Int = 0,
                     @field:NotNull
                     private val lat: Double? = null,
                     @field:NotNull
                     private val lng: Double? = null) : Pagination {

    val _lat get() = lat!!
    val _lng get() = lng!!
}