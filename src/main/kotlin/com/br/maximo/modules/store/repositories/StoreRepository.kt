package com.br.maximo.modules.store.repositories

import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.user.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : JpaRepository<Store, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<Store?>
    fun findByOwner(owner: User): List<Store?>
    fun findByNameContainingIgnoreCaseAndOwner(name: String?, owner: User?): List<Store?>
}