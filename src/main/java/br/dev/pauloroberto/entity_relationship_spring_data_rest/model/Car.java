package br.dev.pauloroberto.entity_relationship_spring_data_rest.model;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licenseNumber;
    private Brand brand;
    private String model;
    private String color;
    private boolean available;

    public Car(CarDto carDto) {
        this.licenseNumber = carDto.licenseNumber();
        this.brand = carDto.brand();
        this.model = carDto.model();
        this.color = carDto.color();
        this.available = carDto.available();
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
