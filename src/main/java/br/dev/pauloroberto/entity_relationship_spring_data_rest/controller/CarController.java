package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Car;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CarRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody CarDto carDto) {
        carRepository.save(new Car(carDto));
    }
}
