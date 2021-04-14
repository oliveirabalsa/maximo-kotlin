package com.br.maximo.modules.user.entities

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.order.entities.Order
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.user.enum.UserTypeEnum
import com.br.maximo.modules.user.mappers.UserDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User {
    @Column
    var name: String = ""

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0

    @Column(unique = true)
    var email: String = ""

    var type: UserTypeEnum? = null

    val createdAt: Date = Date()

    val updatedAt: Date = Date()

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    val addresses: List<Address> = listOf()

    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonIgnore
    val store: Store? = null

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    @JsonIgnore
    val orders: List<Order>? = null


    @Column
    var password = ""
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun comparePassword(password: String): Boolean {
        val passwordEncoder = BCryptPasswordEncoder()
        return passwordEncoder.matches(password, this.password)
    }


    fun toResponseObject(token: String? = null): UserDTO {
        return UserDTO(
            id = this.id,
            name = this.name,
            email = this.email,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            addresses = this.addresses,
            store = this.store,
            orders = this.orders,
            type = this.type,
            token = token
        )
    }
}

