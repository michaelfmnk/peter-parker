package com.michaelfmnk.peterparker.userapi.rest

import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification

fun MockMvcRequestSpecification.doing() = `when`()
