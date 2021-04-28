package com.br.maximo.modules.store.services

import com.br.maximo.modules.store.dto.StoreDTO
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.store.repositories.StoreRepository
import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.enum.UserTypeEnum
import com.br.maximo.modules.user.repositories.UserRepository
import com.br.maximo.shared.errors.BadRequestException
import com.br.maximo.shared.errors.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StoreService(
    val storeRepository: StoreRepository,
    val userRepository: UserRepository,
) {
    fun all(name: String?, owner_id: String?): List<Store?> {
        val storeName = name ?: ""
        if (name == null && owner_id == null) return this.storeRepository.findAll()
        val user = this.userRepository.findByIdOrNull(owner_id?.toLong() ?: 0)
        if(name != null && owner_id.isNullOrEmpty()) return this.storeRepository.findByNameContainingIgnoreCase(storeName)
        return this.storeRepository.findByNameContainingIgnoreCaseAndOwner(storeName, user)
    }

    fun one(id: Long): Store? {
        return storeRepository.findByIdOrNull(id)
    }

    fun create(store: StoreDTO): Store {
        val user = userRepository.findByIdOrNull(store.owner_id)
            ?: throw NotFoundException("Owner with id ${store.owner_id} doesn't exists")

        if (user.type != UserTypeEnum.SELLER) throw BadRequestException("The owner must be a type seller")

        val storeToCreate = Store(
            id = store.id,
            name = store.name,
            image = store.image,
            owner = user,
            products = store.products
        )

        return storeRepository.save(storeToCreate)
    }

    fun update(store: StoreDTO, id: Long): Store {
        val storeFound = storeRepository.findByIdOrNull(id) ?: throw NotFoundException("Cannot find store with Id: $id")
        val (_, name, image, owner, products) = store

        val storeToUpdate =
            storeFound.copy(id = id, name = name, image = image, owner = storeFound.owner, products = products)

        return storeRepository.save(storeToUpdate)

    }

    fun delete(id: Long) {
        val store = storeRepository.findByIdOrNull(id) ?: throw NotFoundException("Cannot find store with Id: $id")
        return storeRepository.delete(store)
    }
}