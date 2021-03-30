package com.br.maximo.modules.user.mappers

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.order.entities.Order
import com.br.maximo.modules.store.entities.Store
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserDTO(
    val id: Long? = null,

    val name: String? = null,

    val email: String? = null,

    val createdAt: Date? = null,

    val updatedAt: Date? = null,

    val address: Address? = null,

    val store: Store? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val token: String? = null,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val orders: List<Order>? = listOf()
)
