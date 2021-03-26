package com.br.maximo.modules.products.entities

import com.br.maximo.modules.store.entities.Store
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
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

    @ManyToOne(cascade = arrayOf(CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    val store: Store,

//    @ManyToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    @JsonIgnore
//    val order: Order? = null,


    )
