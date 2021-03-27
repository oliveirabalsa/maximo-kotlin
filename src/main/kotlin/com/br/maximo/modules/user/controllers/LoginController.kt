package com.br.maximo.modules.user.controllers

import com.br.maximo.modules.user.mappers.LoginDTO
import com.br.maximo.modules.user.services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController(val userService: UserService) {

    @PostMapping
    fun login(@RequestBody body: LoginDTO): Any {
        return userService.login(body)
    }
}