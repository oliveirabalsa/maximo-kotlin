package com.br.maximo.modules.order.enum

enum class DeliveryStatusEnum(type: String?) {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    WAITING_FOR_DELIVERY("delivery_man"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED"),
    FINISHED("FINISHED"),
}
