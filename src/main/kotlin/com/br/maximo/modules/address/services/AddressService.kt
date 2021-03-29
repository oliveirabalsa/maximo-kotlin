package com.br.maximo.modules.address.services

import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.address.repositories.AddressRepository
import com.br.maximo.shared.errors.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AddressService(val addressRepository: AddressRepository) {
    fun all(): List<Address> {
        return addressRepository.findAll()
    }

    fun create(address: Address): Address {
        return addressRepository.save(address)
    }

    fun update(addressId: Long, address: Address): Address {
        val foundAddress = addressRepository.findByIdOrNull(addressId) ?: throw NotFoundException("Address not found")
        val (id, street, neighborhood, complement, house_number, city, state, type) = address

        val newAddress = foundAddress.copy(
                street = street,
                neighborhood = neighborhood,
                complement = complement,
                house_number = house_number,
                city = city,
                state = state,
                type = type
                )
        return addressRepository.save(newAddress)
        }
    }