package com.br.maximo.shared.security.Jwt

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.repositories.UserRepository
import com.br.maximo.modules.user.services.UserService
import com.br.maximo.shared.errors.UnauthorizedException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Service
class JwtService(val response: HttpServletResponse, val userRepository: UserRepository) {

    fun generateToken(id: Long) {
        val issuer = id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        return response.addCookie(cookie)
    }

    fun checkAuthentication(token: String?): Boolean {
        try {
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).body

            val user = userRepository.findByIdOrNull(body.issuer.toLong())

            if (user != null) return true
            throw UnauthorizedException("Token Invalid")
        } catch (e: Exception) {
            throw UnauthorizedException("Please check your authorization")
        }
    }
}