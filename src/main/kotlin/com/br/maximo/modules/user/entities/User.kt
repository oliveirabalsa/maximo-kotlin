package com.br.maximo.modules.user.entities

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.delivery.entities.Delivery
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.user.mappers.UserDTO
import com.fasterxml.jackson.annotation.JsonIgnore
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
    val password: String? = null,

    @field:CreatedDate
    val createdAt: Date = Date(),

    @field:LastModifiedDate
    val updatedAt: Date = Date(),

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    val address: Address? = null,

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    val store: Store? = null,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    val deliveries: List<Delivery>? = listOf(),

//    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
//    @JsonIgnore
//    val orders: List<Order>? = null


) {
    fun toResponseObject(): UserDTO {
        return UserDTO(
            id = this.id,
            name = this.name,
            email = this.email,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            address = this.address,
            store = this.store,
            deliveries = this.deliveries
        )
    }
}