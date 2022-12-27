package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.RentalDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Car;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Customer;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Rental;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CarRepository;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CustomerRepository;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.RentalRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public RentalController(RentalRepository rentalRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody RentalDto rentalDto) {
        Car car = carRepository.findByLicenseNumber(rentalDto.licenseNumber());
        Customer customer = customerRepository.findById(rentalDto.customerId()).orElseThrow();

        if (car.isAvailable()) {
            rentalRepository.save(new Rental(rentalDto, customer, carRepository));
        } else {
            throw new RuntimeException("Car is not available");
        }
    }
}
