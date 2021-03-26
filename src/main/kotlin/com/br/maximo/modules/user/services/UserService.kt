package com.br.maximo.modules.user.services

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.mappers.UserDTO
import com.br.maximo.modules.user.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val repository: UserRepository) {

    fun getAll(): List<UserDTO> {
        val users = repository.findAll()

        return users.map { it.toResponseObject() }
    }

    fun create(user: User): UserDTO {
        return repository.save(user).toResponseObject()
    }
}