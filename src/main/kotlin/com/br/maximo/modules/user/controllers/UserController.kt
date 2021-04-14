package com.br.maximo.modules.user.controllers

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.mappers.UserDTO
import com.br.maximo.modules.user.services.UserService
import com.br.maximo.shared.models.WebResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class UserController(val service: UserService) {

    // TODO update UserDTO to return with totalpages, page, size and totalelements
    @GetMapping("/user")
    fun getAll(
        @RequestParam(
            defaultValue = "0",
            value = "page"
        ) page: Int,
        @RequestParam(
            defaultValue = "10",
            value = "size"
        ) size: Int,
    ): List<UserDTO> {
        return service.getAll(page, size)
    }

    @PostMapping("/signup")
    fun create(@RequestBody user: User): WebResponse<UserDTO> {
        return service.create(user)
    }

    @PutMapping("/user/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: User): WebResponse<UserDTO> {
        return service.update(id, user)
    }

    @DeleteMapping("/user/{id}")
    fun delete(@PathVariable id: Long): WebResponse<Unit> {
        return service.delete(id)
    }
}