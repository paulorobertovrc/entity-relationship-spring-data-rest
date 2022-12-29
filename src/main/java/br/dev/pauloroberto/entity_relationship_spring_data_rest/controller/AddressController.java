package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.AddressDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Address;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid AddressDto address) {
        addressService.create(address);
    }

    @GetMapping
    public Iterable<Address> list() {
        return addressService.list();
    }

    @GetMapping("/{id}")
    public Address list(@PathVariable Long id) {
        return addressService.list(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid AddressDto address) {
        addressService.update(id, address);
    }
}
