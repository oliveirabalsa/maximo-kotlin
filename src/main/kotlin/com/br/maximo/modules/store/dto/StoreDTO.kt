package com.br.maximo.modules.store.dto

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.user.entities.User
import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*

data class StoreDTO(
    val id: Long,

    val name: String,

    val image: String,

    val owner_id: Long,

    val products: List<Product>? = listOf(),
)
