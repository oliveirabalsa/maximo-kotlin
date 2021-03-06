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
    fun getAll(
        @RequestParam(
            value = "name",
            required = false
        ) name: String?,
        @RequestParam(
            required = false,
            value = "owner_id"
        ) owner_id: String?
    ): List<Store?> {
        return storeService.all(name, owner_id)
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

    @PutMapping("/{id}")
    @ResponseBody
    fun update(@RequestBody store: StoreDTO, @PathVariable("id") id: Long): Store {
        return storeService.update(store, id)
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun delete(@PathVariable("id") id: Long): Unit {
        return storeService.delete(id)
    }
}