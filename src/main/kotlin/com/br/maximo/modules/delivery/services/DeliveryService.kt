package com.br.maximo.modules.delivery.services

import com.br.maximo.modules.delivery.entities.Delivery
import com.br.maximo.modules.delivery.repositories.DeliveryRepository
import org.springframework.stereotype.Service

@Service
class DeliveryService(val deliveryRepository: DeliveryRepository) {

    fun all(): List<Delivery> {
        return deliveryRepository.findAll()
    }

    fun create(delivery: Delivery): Delivery {
        return deliveryRepository.save(delivery)
    }
}