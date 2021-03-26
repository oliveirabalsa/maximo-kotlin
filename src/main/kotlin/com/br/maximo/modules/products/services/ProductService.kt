package com.br.maximo.modules.products.services

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.products.repositories.ProductRepository
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.store.repositories.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(val productRepository: ProductRepository, val storeRepository: StoreRepository) {

    fun all(): List<Product> {
        return productRepository.findAll()
    }

    fun getProductsByStoreId(storeId: Long): List<Product> {
        val store: Store = storeRepository.findByIdOrNull(storeId) ?: throw Exception("error")
        return productRepository.findByStore(store)
    }

    fun create(product: Product): Product {
        return productRepository.save(product)
    }
}