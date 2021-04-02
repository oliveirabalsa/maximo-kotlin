package com.br.maximo.modules.order.entities

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.order.enum.DeliveryStatusEnum
import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.user.entities.User
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @Column
    val price: Double,

    @Column
    val delivery_status: DeliveryStatusEnum,

    @Column
    val delivery_man_id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val buyer: User? = null,

    @OneToOne(cascade = arrayOf(CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    val address: Address? = null,


    @OneToMany(cascade = arrayOf(CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val products: List<Product>? = null,

)
