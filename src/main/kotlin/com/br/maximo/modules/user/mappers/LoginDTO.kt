package com.br.maximo.modules.user.mappers
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginDTO(

    val name: String? = "",

    val email: String = "",

    val password: String = ""
)