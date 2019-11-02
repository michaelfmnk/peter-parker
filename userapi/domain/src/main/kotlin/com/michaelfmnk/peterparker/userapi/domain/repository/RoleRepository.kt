package com.michaelfmnk.peterparker.userapi.domain.repository

import com.michaelfmnk.peterparker.userapi.domain.model.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>
