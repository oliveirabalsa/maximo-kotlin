package com.br.maximo.modules.user.controllers

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.services.UserService
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val service: UserService) {

    @GetMapping
    fun getAll(): List<User> {
        return service.getAll()
    }

    @PostMapping
    @ResponseBody
    fun create(@Validated @RequestBody user: User): User {
        return service.create(user)
    }
}