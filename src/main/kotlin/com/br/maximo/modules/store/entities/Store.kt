package com.br.maximo.modules.store.entities

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.user.entities.User
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "stores")
data class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    val name: String,

    val image: String,

    @OneToOne(cascade = arrayOf(CascadeType.MERGE), fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    val user: User,


    @OneToMany(mappedBy = "store", )
    @JsonIgnore
    val products: List<Product>? = listOf()

    )
