package com.br.maximo.modules.user.services

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.mappers.LoginDTO
import com.br.maximo.modules.user.mappers.UserDTO
import com.br.maximo.modules.user.repositories.UserRepository
import com.br.maximo.shared.errors.BadRequestException
import com.br.maximo.shared.errors.NotFoundException
import com.br.maximo.shared.models.WebResponse
import com.br.maximo.shared.security.Jwt.JwtService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import net.bytebuddy.implementation.bytecode.Throw
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CookieValue
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse


@Service
class UserService(
    val repository: UserRepository,
    val jwtService: JwtService,
) {

    fun getAll(): List<UserDTO> {

        val users = repository.findAll()

        return users.map { it.toResponseObject() }
    }

    fun create(user: User): WebResponse<UserDTO> {
        val userCreated = repository.save(user)

        return WebResponse(
            code = 200,
            status = "success",
            data = userCreated.toResponseObject()
        )
    }

    fun update(id: Long?, user: User): WebResponse<UserDTO> {
        if(id == null) throw BadRequestException("Please enter the user id")

        val userFinded = repository.findByIdOrNull(id) ?: throw NotFoundException("User not found")

        userFinded.name = user.name
        userFinded.email = user.email
        userFinded.type = user.type

        val userUpdated = repository.save(userFinded)

        return WebResponse(
            code = 200,
            status = "success",
            data = userUpdated.toResponseObject()
        )
    }

    fun delete(id: Long?): WebResponse<Unit> {
        if(id == null) throw BadRequestException("Please enter the user id")

        val userFinded = repository.findByIdOrNull(id) ?: throw NotFoundException("User not found")

        val userDeleted = repository.delete(userFinded)

        return WebResponse(
            code = 200,
            status = "success",
            data = userDeleted
        )
    }

    fun login(body: LoginDTO): WebResponse<String> {
        val user = repository.findByEmail(body.email) ?: throw NotFoundException("User not found")

        if (!user.comparePassword(body.password)) {
            throw BadRequestException("Email/Password invalid")
        }
        jwtService.generateToken(user.id)
        return WebResponse(
            code = 200,
            status = "success",
            data = "User login success"
        )
    }
}