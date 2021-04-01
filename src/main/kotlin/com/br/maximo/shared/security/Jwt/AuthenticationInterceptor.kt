package com.br.maximo.shared.security.Jwt

import com.br.maximo.shared.errors.UnauthorizedException
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import org.springframework.web.servlet.HandlerMapping
import java.lang.Exception


@Component
class AuthenticationInterceptor(val jwtService: JwtService) : WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, 0).toString()
        if (path.contains("login") || path.contains("signup")) return

        val authorization = request.getHeader("Authorization") ?: throw UnauthorizedException("Token invalid")
        val (type, token) = authorization.split(" ")

        if (type.toLowerCase() != "bearer") throw UnauthorizedException("Type unknown")

        jwtService.checkAuthentication(token)
    }

    override fun postHandle(p0: WebRequest, p1: ModelMap?) {

    }

    override fun afterCompletion(p0: WebRequest, p1: Exception?) {

    }

}