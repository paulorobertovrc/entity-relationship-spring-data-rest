package br.dev.pauloroberto.entity_relationship_spring_data_rest.service;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerUpdateDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Address;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Customer;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.AddressRepository;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public void create(CustomerDto customerDto) {
        customerRepository.save(new Customer(customerDto));
    }

    public Iterable<Customer> list() {
        return customerRepository.findAllByActiveTrue();
    }

    public Customer list(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            return customerRepository.findById(id).get();
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public void update(Long id, CustomerUpdateDto customerUpdateDto) {
        Customer customer = customerRepository.findById(id).orElseThrow();

            if (!customerUpdateDto.name().isEmpty()) {
                customer.setName(customerUpdateDto.name());
            }

            if (!customerUpdateDto.email().isEmpty()) {
                customer.setEmail(customerUpdateDto.email());
            }

        Address address = addressRepository.findById(customerUpdateDto.addressId()).orElseThrow();
        customer.setAddress(address);

        customerRepository.save(customer);
    }

    public void delete(Long id) {
        // This method implements a logical deletion
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setInactive();
        customerRepository.save(customer);
    }
}
