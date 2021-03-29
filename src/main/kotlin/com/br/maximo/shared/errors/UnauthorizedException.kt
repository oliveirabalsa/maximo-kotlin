package com.br.maximo.shared.errors

class UnauthorizedException(val unauthorizedMessage: String = "Please put your validation") : Exception() {
}