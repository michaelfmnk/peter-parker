package com.michaelfmnk.peterparker.userapi.api.param

import javax.validation.constraints.NotNull


class IncidentParams(override val size: Int = 10,
                     override val page: Int = 0,
                     @field:NotNull
                     private val lat: Double? = null,
                     @field:NotNull
                     private val lng: Double? = null,
                     private val plateNumber: String? = null) : Pagination {

    val iLat get() = lat!!
    val iLng get() = lng!!
}
