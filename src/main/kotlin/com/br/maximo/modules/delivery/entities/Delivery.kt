package com.br.maximo.modules.delivery.entities

import com.br.maximo.modules.user.entities.User
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "delivery")
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @Column
    // Change to Enum in the future
    val status: String,

    @ManyToOne(cascade = arrayOf(CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null,


//    @OneToOne(cascade = arrayOf(CascadeType.ALL))
//    @JoinColumn(name = "order_id")
//    val order: Order


)
