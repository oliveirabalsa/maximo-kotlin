package com.br.maximo.modules.user.entities

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.delivery.entities.Delivery
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.user.mappers.UserResponse
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "user")
data class User(
    @field:NotNull
    val name: String,

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @field:NotNull
    @field:Email
    val email: String,

    @field:NotNull
    @field:Size(min = 5, message = "Senha deve conter mais de 5 digitos")
    val password: String,

    @field:CreatedDate
    val createdAt: Date = Date(),

    @field:LastModifiedDate
    val updatedAt: Date = Date(),

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "address_id")
    val address: Address,

    @OneToOne(cascade = arrayOf(CascadeType.ALL), optional = true)
    @JoinColumn(name = "store_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val store: Store? = null,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonBackReference
    val deliveries: List<Delivery>? = null,

//    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
//    @JsonIgnore
//    val orders: List<Order>? = null


) {
    fun toResponseObject(): UserResponse {
        return UserResponse(
            id = this.id,
            name = this.name,
            email = this.email,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}