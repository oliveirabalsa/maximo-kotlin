package com.br.maximo.modules.delivery.repositories

import com.br.maximo.modules.delivery.entities.Delivery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeliveryRepository : JpaRepository<Delivery, Long> {
}