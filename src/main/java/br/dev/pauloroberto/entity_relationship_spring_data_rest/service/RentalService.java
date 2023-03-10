package br.dev.pauloroberto.entity_relationship_spring_data_rest.service;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.RentalDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Car;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Customer;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Rental;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CarRepository;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CustomerRepository;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.RentalRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public RentalService(RentalRepository rentalRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public void rent(RentalDto rentalDto) {
        Car car = carRepository.findByLicenseNumber(rentalDto.licenseNumber());
        Customer customer = customerRepository.findById(rentalDto.customerId()).orElseThrow();

        if (car.isAvailable()) {
            rentalRepository.save(new Rental(rentalDto, customer, carRepository));
        } else {
            throw new RuntimeException("Car is not available");
        }
    }

    public void finishRental(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setEndDate(new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(new Date()));
        Car car = carRepository.findById(rental.getCar().getId()).orElseThrow();
        car.setAvailable(true);
        carRepository.save(car);
    }

    public Iterable<Rental> list(Pageable pageable) {
        return rentalRepository.findAll(pageable);
    }
}
