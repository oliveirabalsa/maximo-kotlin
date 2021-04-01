package com.br.maximo.modules.store.entities

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.store.dto.StoreDTO
import com.br.maximo.modules.user.entities.User
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "stores")
data class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    val name: String,

    val image: String,

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "user_id")
    val owner: User? = null,


    @OneToMany(mappedBy = "store", )
    @JsonIgnore
    val products: List<Product>? = listOf()

    )

