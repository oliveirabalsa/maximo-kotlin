package com.br.maximo.modules.address.controllers
import com.br.maximo.modules.address.entities.Address
import com.br.maximo.modules.address.services.AddressService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/address")
class AddressController (val addressService: AddressService) {
    @GetMapping
    fun getAll(): List<Address>{
        return addressService.all()

    }

    @PostMapping
    @ResponseBody
    fun create(@Validated @RequestBody address: Address): Address {
        return addressService.create(address)
    }
}




