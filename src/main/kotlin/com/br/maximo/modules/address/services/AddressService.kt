package com.br.maximo.modules.address.services

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.address.repositories.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService (val addressRepository: AddressRepository) {
    fun all(): List<Address> {
        return addressRepository.findAll()
    }

    fun create(address: Address): Address {
        return addressRepository.save(address)
    }
}