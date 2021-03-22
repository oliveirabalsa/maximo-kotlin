package com.br.maximo.modules.products.controllers

import com.br.maximo.modules.products.entities.Product
import com.br.maximo.modules.products.services.ProductService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/product")
class ProductController (val productService: ProductService) {
    @GetMapping
    fun getAll(): List<Product>{
        return productService.all()

    }

    @PostMapping
    @ResponseBody
    fun create(@Validated @RequestBody product: Product): Product {
        return productService.create(product)
    }
}