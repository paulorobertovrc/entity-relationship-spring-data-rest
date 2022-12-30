package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarUpdateDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Car;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.CarService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CarDto carDto) {
        carService.create(carDto);
    }

    @GetMapping
    public Iterable<Car> list(@PageableDefault(size = 5) Pageable pageable) {
        return carService.list(pageable);
    }

    @GetMapping("/{id}")
    public Car list(@PathVariable Long id) {
        return carService.list(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid CarUpdateDto carUpdateDto) {
        carService.update(id, carUpdateDto);
    }

    @PutMapping("/{id}/activate")
    @Transactional
    public void activate(@PathVariable Long id) {
        carService.activate(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}
