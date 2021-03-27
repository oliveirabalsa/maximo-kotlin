package com.br.maximo.shared.errors

class BadRequestException(
    val errorMessage: String = "Não foi possível concluir está ação",
) : Exception() {
}