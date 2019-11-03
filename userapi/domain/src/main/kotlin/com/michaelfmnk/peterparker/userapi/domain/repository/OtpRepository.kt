package com.michaelfmnk.peterparker.userapi.domain.repository

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Otp
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OtpRepository : JpaRepository<Otp, UUID>
