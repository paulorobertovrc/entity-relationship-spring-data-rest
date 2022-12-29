package br.dev.pauloroberto.entity_relationship_spring_data_rest.service;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarUpdateDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Car;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CarRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void create(CarDto carDto) {
        carRepository.save(new Car(carDto));
    }

    public Iterable<Car> list() {
        return carRepository.findAll();
    }

    public Car list(Long id) {
        if (carRepository.findById(id).isPresent()) {
            return carRepository.findById(id).get();
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public void update(Long id, @Valid CarUpdateDto carUpdateDto) {
        if (carRepository.findById(id).isPresent()) {
            Car car = carRepository.findById(id).orElseThrow();

            if (!carUpdateDto.model().isEmpty()) {
                car.setModel(carUpdateDto.model());
            }

            if (!carUpdateDto.color().isEmpty()) {
                car.setColor(carUpdateDto.color());
            }

            if (!carUpdateDto.licenseNumber().isEmpty()) {
                car.setLicenseNumber(carUpdateDto.licenseNumber());
            }

            carRepository.save(car);
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public void activate(Long id) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setAvailable(true);
        carRepository.save(car);
    }

    public void delete(Long id) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setAvailable(false);
        carRepository.save(car);
    }
}
