package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarUpdateDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Car;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Car", description = "Endpoints for operations about cars")
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Create a new car", description = "Observe that Brand and Color fields are Enum")
    public void create(@RequestBody @Valid CarDto carDto) {
        carService.create(carDto);
    }

    @GetMapping
    @Operation(summary = "List all cars", description = "List all cars with pagination and sorting support")
    public Iterable<Car> list(@PageableDefault(size = 5) Pageable pageable) {
        return carService.list(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a car by id or throw an exception if not found")
    public Car list(@PathVariable Long id) {
        return carService.list(id);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update a car", description = "Only licenseNumber and model fields can be updated")
    public void update(@PathVariable Long id, @RequestBody @Valid CarUpdateDto carUpdateDto) {
        carService.update(id, carUpdateDto);
    }

    @PutMapping("/{id}/activate")
    @Transactional
    @Operation(summary = "Activate a car", description = "Only cars with status INACTIVE (NOT AVAILABLE) can be activated")
    public void activate(@PathVariable Long id) {
        carService.activate(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete a car", description = "This operation is a soft delete, so the car will be marked as NOT AVAILABLE")
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}
