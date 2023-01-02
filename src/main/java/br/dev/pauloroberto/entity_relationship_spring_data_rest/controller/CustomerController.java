package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerUpdateDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Customer;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer", description = "Endpoints for operations about customers")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Create a new customer", description = "If the address is not informed, it will be set to null. You may update the address later, informing an existing address id")
    public void create(@RequestBody @Valid CustomerDto customerDto) {
        customerService.create(customerDto);
    }

    @GetMapping
    @Operation(summary = "List all customers", description = "List all customers with pagination and sorting support")
    public Iterable<Customer> list(@PageableDefault(size = 5, sort = {"name"}) Pageable pageable) {
        // The Pageable object is automatically created by Spring Data Rest based on the request parameters (page, size, sort) sent by the client (browser)
        return customerService.list(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a customer by id or throw an exception if not found")
    public Customer list(@PathVariable Long id) {
        return customerService.list(id);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update a customer", description = "Unfilled fields will be ignored")
    public void update(@PathVariable Long id, @RequestBody @Valid CustomerUpdateDto customerUpdateDto) {
       customerService.update(id, customerUpdateDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete a customer by id", description = "This operation is a soft delete, so the customer will be marked as inactive. The related address will not be deleted")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
