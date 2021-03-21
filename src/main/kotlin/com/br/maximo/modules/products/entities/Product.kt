package com.br.maximo.modules.products.entities

import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    val name: String,

    val quantity: Long,

    val price: Double,

    val image: String,

//    @ManyToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
//    @JoinColumn(name = "store_id")
//    @JsonIgnore
//    val store: Store? = null,

//    @ManyToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    @JsonIgnore
//    val order: Order? = null,


    )
