package com.br.maximo.modules.store.services

import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.store.repositories.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService (val storeRepository: StoreRepository) {
    fun all(): List<Store> {
        return storeRepository.findAll()
    }

    fun create(product: Store): Store {
        return storeRepository.save(product)
    }
}