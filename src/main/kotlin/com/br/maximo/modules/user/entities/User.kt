package com.br.maximo.modules.user.entities

import jdk.jfr.Name
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

//    @field:CreatedDate
//    val createdAt: Date,
//
//    @field:LastModifiedDate
//    val updatedAt: Date
) {

}