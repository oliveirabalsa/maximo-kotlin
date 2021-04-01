package com.br.maximo.modules.order.controllers

import com.br.maximo.modules.order.dto.OrderDTO
import com.br.maximo.modules.order.entities.Order
import com.br.maximo.modules.order.services.OrderService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/order")
class OrderController (val orderService: OrderService) {
    @GetMapping
    fun getAll(): List<Order>{
        return orderService.all()

    }

    @PostMapping
    @ResponseBody
    fun create(@Validated @RequestBody order: Order): OrderDTO {
        return orderService.create(order)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody order: Order): Order {
        return orderService.update(order = order, orderId = id)
    }
}