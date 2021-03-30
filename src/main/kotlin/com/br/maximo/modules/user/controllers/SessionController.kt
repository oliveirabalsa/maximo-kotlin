package com.br.maximo.modules.user.controllers

import com.br.maximo.modules.user.mappers.LoginDTO
import com.br.maximo.modules.user.services.UserService
import com.br.maximo.shared.models.WebResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class SessionController(val userService: UserService) {

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDTO): WebResponse<String> {
        return userService.login(body)
    }
}
