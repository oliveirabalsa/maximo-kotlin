package com.br.maximo.modules.order.services

import com.br.maximo.modules.order.dto.OrderDTO
import com.br.maximo.modules.order.entities.Order
import com.br.maximo.modules.order.repositories.OrderRepository
import com.br.maximo.modules.user.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import com.br.maximo.shared.errors.NotFoundException

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val userRepository: UserRepository,
) {

    fun all(): List<Order> {
        return orderRepository.findAll()
    }

    fun create(order: Order): OrderDTO {
        val deliveryMan = userRepository.findByIdOrNull(order.delivery_man_id) ?: throw NotFoundException()

        val orderCreated = orderRepository.save(order)

        return OrderDTO(
            orderCreated.id,
            orderCreated.price,
            orderCreated.delivery_status,
            deliveryMan,
            orderCreated.user,
            orderCreated.address,
            orderCreated.products,
        )
    }
}