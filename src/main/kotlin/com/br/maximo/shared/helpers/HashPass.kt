package com.br.maximo.shared.helpers

import java.util.*

fun hashPassword(password: String): String {
    val response: String = Base64.getEncoder().encodeToString(password.toByteArray())
    println(response)
    return response
}