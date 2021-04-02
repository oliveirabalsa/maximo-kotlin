package com.br.maximo.modules.address.entities

import com.br.maximo.modules.address.enum.AddressEnum
import com.br.maximo.modules.order.entities.Order
import com.br.maximo.modules.user.entities.User
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @Column
    val street: String,

    @Column
    val neighborhood: String,

    @Column(nullable = true)
    val complement: String?,

    @Column
    val house_number: Int,

    @Column
    val city: String,

    @Column
    val state: String,

    @Column
    val type: AddressEnum,

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    @JsonIgnore
    val order: Order? = null,

    @OneToOne(cascade = arrayOf(CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null,


)
