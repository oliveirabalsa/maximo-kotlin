package com.br.maximo.modules.order.dto

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.order.enum.DeliveryStatusEnum
import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.user.entities.User
import javax.persistence.*

data class OrderDTO(
    val id: Long,

    val price: Double,

    val delivery_status: DeliveryStatusEnum,

    val delivery_man_id: User? = null,

    val user: User? = null,

    val address: Address? = null,

    val products: List<Product>? = null,

    )
