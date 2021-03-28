package com.br.maximo.modules.user.controllers

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.mappers.UserDTO
import com.br.maximo.modules.user.services.UserService
import com.br.maximo.shared.models.WebResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val service: UserService) {

    @GetMapping
    fun getAll(@CookieValue("jwt") jwt: String?): List<UserDTO> {
        return service.getAll(jwt)

    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody user: User): WebResponse<UserDTO> {
        return service.create(user)
    }
}