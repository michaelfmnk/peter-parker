package com.michaelfmnk.peterparker.userapi.api.dto

data class PageDto<T>(val total: Long,
                      val page: Long,
                      val size: Long,
                      val content: List<T>)