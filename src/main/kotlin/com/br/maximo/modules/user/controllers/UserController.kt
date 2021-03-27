package com.br.maximo.modules.user.controllers

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.mappers.UserDTO
import com.br.maximo.modules.user.services.UserService
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val service: UserService) {

    @GetMapping
    fun getAll(): List<UserDTO> {
        return service.getAll()

    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody user: User): User {
        return service.create(user)
    }
}