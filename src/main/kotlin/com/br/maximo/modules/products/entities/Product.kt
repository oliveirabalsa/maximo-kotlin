package com.br.maximo.modules.products.entities

import com.br.maximo.modules.order.entities.Order
import com.br.maximo.modules.store.entities.Store
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "products")
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


    )
