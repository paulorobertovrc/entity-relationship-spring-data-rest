package br.dev.pauloroberto.entity_relationship_spring_data_rest.model;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CarDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String licenseNumber;
    private Brand brand;
    private String model;
    private @NotBlank Color color;
    @Column(nullable = false)
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
