package br.dev.pauloroberto.entity_relationship_spring_data_rest.repository;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByLicenseNumber(String licenseNumber);
}
