package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerUpdateDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Address;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Customer;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.AddressRepository;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerController(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CustomerDto customerDto) {
        customerRepository.save(new Customer(customerDto));
    }

    @GetMapping
    public Iterable<Customer> list() {
        return customerRepository.findAllByActiveTrue();
    }

    @GetMapping("/{id}")
    public Customer list(@PathVariable Long id) {
        if (customerRepository.findById(id).isPresent()) {
            return customerRepository.findById(id).get();
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid CustomerUpdateDto customerUpdateDto) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        if (customerUpdateDto.name() != null) {
            customer.setName(customerUpdateDto.name());
        }
        if (customerUpdateDto.email() != null) {
            customer.setEmail(customerUpdateDto.email());
        }

        Address address = addressRepository.findById(customerUpdateDto.addressId()).orElseThrow();
        customer.setAddress(address);

        customerRepository.save(customer);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
//        customerRepository.deleteById(id); // Physical delete

        // Logical delete
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setInactive();
        customerRepository.save(customer);
    }
}
