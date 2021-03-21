package com.br.maximo.modules.store.controllers

import com.br.maximo.modules.store.entities.Store
import com.br.maximo.modules.store.services.StoreService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/store")
class StoreController (val storeService: StoreService) {
    @GetMapping
    fun getAll(): List<Store>{
        return storeService.all()

    }

    @PostMapping
    @ResponseBody
    fun create(@Validated @RequestBody store: Store): Store {
        return storeService.create(store)
    }
}