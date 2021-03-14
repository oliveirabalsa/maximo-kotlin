package com.br.maximo.modules.user.services

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val repository: UserRepository) {
    fun getAll(): List<User> {
        return repository.findAll()
    }

    fun create(user: User): User {
        val userKeys = user.toString()
        println(userKeys)
        return repository.save(user)
    }
}