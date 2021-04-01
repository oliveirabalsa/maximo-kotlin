package com.br.maximo.modules.store.controllers

import com.br.maximo.modules.store.dto.StoreDTO
import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.store.services.StoreService
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/store")
@JsonIgnoreProperties(ignoreUnknown=true)
class StoreController (val storeService: StoreService) {
    @GetMapping
    fun getAll(): List<Store>{
        return storeService.all()
    }

    @GetMapping("/{id}")
    fun one(@PathVariable id: Long): Store?{
        return storeService.one(id)
    }

    @PostMapping
    @ResponseBody
    fun create(@Validated @RequestBody store: StoreDTO): Store {
        return storeService.create(store)
    }
}