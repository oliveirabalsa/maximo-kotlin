package com.br.maximo.modules.products.services

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.products.repositories.ProductRepository
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.store.repositories.StoreRepository
import com.br.maximo.shared.errors.BadRequestException
import com.br.maximo.shared.errors.ErrorHandler
import com.br.maximo.shared.errors.NotFoundException
import com.br.maximo.shared.models.WebResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*


@Service
class ProductService(
    val productRepository: ProductRepository,
    val storeRepository: StoreRepository,
    val errorHandler: ErrorHandler,
) {

    fun all(): List<Product> {
        return productRepository.findAll()
    }

    fun getProductsByStoreId(storeId: Long): WebResponse<List<Product>> {
        val store: Store = storeRepository.findByIdOrNull(storeId)
            ?: throw BadRequestException("Loja com o id $storeId não encontrada")
        
        val products = productRepository.findByStore(store)

        if (products.isEmpty()) throw NotFoundException("Não foram encontrados produtos para o estabelecimento: ${store.name}")
        return WebResponse(
            code = 200,
            status = "OK",
            data = products
        )
    }

    fun create(product: Product): Product {
        return productRepository.save(product)
    }
}