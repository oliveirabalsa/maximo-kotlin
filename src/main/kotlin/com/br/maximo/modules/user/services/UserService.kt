package com.br.maximo.modules.user.services

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.mappers.UserResponse
import com.br.maximo.modules.user.repositories.UserRepository
import com.br.maximo.shared.helpers.hashPassword
import org.springframework.stereotype.Service

@Service
class UserService(val repository: UserRepository) {
    fun getAll(): List<User> {
        return repository.findAll()
    }

    fun create(user: User): User {
        return repository.save(user)
    }
}