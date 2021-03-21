package com.br.maximo.modules.user.mappers

import java.util.*

data class UserResponse(
    val id: Long,

    val name: String,

    val email: String,

    val createdAt: Date,

    val updatedAt: Date,
)
