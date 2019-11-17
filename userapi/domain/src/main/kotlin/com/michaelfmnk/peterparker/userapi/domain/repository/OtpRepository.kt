package com.michaelfmnk.peterparker.userapi.domain.repository

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Otp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface OtpRepository : JpaRepository<Otp, UUID> {
    @Query("SELECT otp FROM Otp otp WHERE otp.value = :val")
    fun findByOtpValue(@Param("val") value: String): Otp?
}
