package com.br.maximo.modules.store.entities

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.user.entities.User
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "store")
data class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    val name: String,

    val image: String,

    @OneToOne(mappedBy = "store", fetch = FetchType.LAZY)
    @JsonIgnore
    val user: User? = null,


    @OneToMany(mappedBy = "store")
    val products: List<Product>? = null

    )