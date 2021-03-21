package com.br.maximo.modules.store.repositories

import com.br.maximo.modules.store.entities.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : JpaRepository<Store, Long> {
}