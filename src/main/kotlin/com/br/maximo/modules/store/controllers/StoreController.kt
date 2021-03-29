package com.br.maximo.modules.store.controllers

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
    fun getAll(@CookieValue("jwt") token: String?): List<Store>{
        return storeService.all(token)
    }

    @GetMapping("/{id}")
    fun one(@PathVariable id: Long, @CookieValue("jwt") token: String?): Store?{
        return storeService.one(id, token)
    }

    @PostMapping
    @ResponseBody
    fun create(@Validated @RequestBody store: Store, @CookieValue("jwt") token: String?): Store {
        return storeService.create(store, token)
    }
}