package com.br.maximo.modules.order.repositories

import com.br.maximo.modules.order.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
}