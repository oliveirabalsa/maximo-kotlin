package com.br.maximo.modules.order.services


import com.br.maximo.modules.order.dto.OrderDTO
import com.br.maximo.modules.order.entities.Order
import com.br.maximo.modules.order.repositories.OrderRepository
import com.br.maximo.modules.user.enum.UserTypeEnum
import com.br.maximo.modules.user.repositories.UserRepository
import com.br.maximo.shared.errors.BadRequestException
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
        val buyer = userRepository.findByIdOrNull(order.buyer?.id) ?: throw NotFoundException("Buyer not found")

        if(buyer.type != UserTypeEnum.BUYER) throw BadRequestException("Buyer must be a type ${UserTypeEnum.BUYER}")

        val orderCreated = orderRepository.save(order)

        return OrderDTO(
            orderCreated.id,
            orderCreated.price,
            orderCreated.delivery_status,
            null,
            orderCreated.buyer,
            orderCreated.address,
            orderCreated.products,
        )
    }

    fun update(orderId: Long, order: Order): Order {
        val foundOrder = orderRepository.findByIdOrNull(orderId) ?: throw NotFoundException("Order not found")

        val deliveryMan = userRepository.findByIdOrNull(order.delivery_man_id) ?: throw NotFoundException("Delivery man not found")

        if(deliveryMan.type != UserTypeEnum.DELIVERY_MAN) throw BadRequestException("Delivery man must be a type ${UserTypeEnum.DELIVERY_MAN}")

        //check if address exists

        val orderToUpdate = foundOrder.copy(
             price = order.price,
             delivery_status = order.delivery_status,
             delivery_man_id = 14,
             buyer = order.buyer,
             address = order.address,
             products = order.products,
        )
        return orderRepository.saveAndFlush(orderToUpdate)
    }
}