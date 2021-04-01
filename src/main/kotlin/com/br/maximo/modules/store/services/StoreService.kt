package com.br.maximo.modules.store.services

import com.br.maximo.modules.store.dto.StoreDTO
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.store.repositories.StoreRepository
import com.br.maximo.modules.user.repositories.UserRepository
import com.br.maximo.shared.errors.NotFoundException
import com.br.maximo.shared.security.Jwt.JwtService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StoreService(
    val storeRepository: StoreRepository,
    val userRepository: UserRepository,
) {
    fun all(): List<Store> {
        return storeRepository.findAll()
    }

    fun one(id: Long): Store? {
        return storeRepository.findByIdOrNull(id)
    }

    fun create(store: StoreDTO): Store {
        val userExists = userRepository.findByIdOrNull(store.owner_id) ?: throw NotFoundException("Owner with id ${store.id} doesn't exists")

        val storeToCreate = Store(
            id = store.id,
            name = store.name,
            image = store.image,
            owner = userExists,
            products = store.products
        )

        return storeRepository.save(storeToCreate)
    }
}