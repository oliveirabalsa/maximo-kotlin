package com.br.maximo.modules.products.services

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.products.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun all(): List<Product> {
        return productRepository.findAll()
    }

    fun create(product: Product): Product {
        return productRepository.save(product)
    }
}