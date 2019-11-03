package com.michaelfmnk.peterparker.userapi.domain.repository

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Otp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OtpRepository : JpaRepository<Otp, UUID> {
    @Query("SELECT otp FROM Otp otp WHERE otp.value = :value")
    fun findByOtpValue(@Param("value") value: String): Otp?
}
