package com.br.maximo.modules.delivery.controllers

import com.br.maximo.modules.delivery.entities.Delivery
import com.br.maximo.modules.delivery.services.DeliveryService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("delivery")
class DeliveryController (val deliveryService: DeliveryService) {
    @GetMapping
    fun getAll(): List<Delivery>{
        return deliveryService.all()

    }

    @PostMapping
    @ResponseBody
    fun create(@Validated @RequestBody delivery: Delivery): Delivery {
        return deliveryService.create(delivery)
    }
}