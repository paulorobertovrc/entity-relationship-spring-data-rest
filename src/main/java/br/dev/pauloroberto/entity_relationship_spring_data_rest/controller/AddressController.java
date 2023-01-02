package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.AddressDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Address;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Address", description = "Endpooints for operations about addresses")
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Create a new address", description = "Will be created when the customer is created")
    public void create(@RequestBody @Valid AddressDto address) {
        addressService.create(address);
    }

    @GetMapping
    @Operation(summary = "List all addresses", description = "List all addresses with pagination and sorting support")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of addresses"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Iterable<Address> list(@PageableDefault(size = 5) Pageable pageable) {
        return addressService.list(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find an address by id or throw an exception if not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Address list(@PathVariable Long id) {
        return addressService.list(id);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update an address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void update(@PathVariable Long id, @RequestBody @Valid AddressDto address) {
        addressService.update(id, address);
    }
}
