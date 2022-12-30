package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerUpdateDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Customer;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CustomerDto customerDto) {
        customerService.create(customerDto);
    }

    @GetMapping
    public Iterable<Customer> list(@PageableDefault(size = 5, sort = {"name"}) Pageable pageable) {
        // The Pageable object is automatically created by Spring Data Rest based on the request parameters (page, size, sort) sent by the client (browser)
        return customerService.list(pageable);
    }

    @GetMapping("/{id}")
    public Customer list(@PathVariable Long id) {
        return customerService.list(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid CustomerUpdateDto customerUpdateDto) {
       customerService.update(id, customerUpdateDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
