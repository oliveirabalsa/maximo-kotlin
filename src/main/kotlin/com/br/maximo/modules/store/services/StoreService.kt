package com.br.maximo.modules.store.services

import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.store.repositories.StoreRepository
import com.br.maximo.shared.security.Jwt.JwtService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StoreService (val storeRepository: StoreRepository, val jwtService: JwtService) {
    fun all(token: String?): List<Store> {
        jwtService.checkAuthentication(token)
        return storeRepository.findAll()
    }

    fun one(id: Long, token: String?): Store? {
        jwtService.checkAuthentication(token)
        return storeRepository.findByIdOrNull(id)
    }

    fun create(product: Store, token: String?): Store {
        jwtService.checkAuthentication(token)
        return storeRepository.save(product)
    }
}