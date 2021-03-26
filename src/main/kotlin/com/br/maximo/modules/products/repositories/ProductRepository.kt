package com.br.maximo.modules.products.repositories

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.store.entities.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findByStore(store: Store): List<Product>
}