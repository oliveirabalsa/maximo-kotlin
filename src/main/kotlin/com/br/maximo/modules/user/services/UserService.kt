package com.br.maximo.modules.user.services

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.mappers.LoginDTO
import com.br.maximo.modules.user.mappers.UserDTO
import com.br.maximo.modules.user.repositories.UserRepository
import com.br.maximo.shared.errors.BadRequestException
import com.br.maximo.shared.errors.NotFoundException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(
    val repository: UserRepository
) {

    fun getAll(): List<UserDTO> {
        val users = repository.findAll()

        return users.map { it.toResponseObject() }
    }

    fun create(user: User): User {
        return repository.save(user)
    }

    fun login(body: LoginDTO): Any {
        val user = findByEmail(body.email!!)

        if(!user.comparePassword(body.password!!)){
            throw BadRequestException("Email/Password invalid")
        }
        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

            return jwt
    }

    private fun findByEmail(email: String): User {
        return this.repository.findByEmail(email) ?: throw NotFoundException()
    }

}