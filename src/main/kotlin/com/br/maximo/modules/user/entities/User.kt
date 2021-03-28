package com.br.maximo.modules.user.entities

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.delivery.entities.Delivery
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.user.mappers.UserDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class User {
    @Column
    val name: String = ""

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0

    @NotNull
    @Column(unique = true)
    val email: String = ""

    @CreatedDate
    val createdAt: Date = Date()

    @LastModifiedDate
    val updatedAt: Date = Date()

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    val address: Address? = null

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    val store: Store? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    val deliveries: List<Delivery>? = listOf()


    //    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
//    @JsonIgnore
//    val orders: List<Order>? = null
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
            address = this.address,
            store = this.store,
            deliveries = this.deliveries,
            token = token
        )
    }
}