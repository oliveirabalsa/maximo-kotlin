package com.br.maximo.shared.models

data class WebResponse<T>(

    val code: Int,

    val status: String,

    val data: T
)