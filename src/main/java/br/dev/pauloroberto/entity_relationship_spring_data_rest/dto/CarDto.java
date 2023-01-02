package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Brand;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Color;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CarDto(
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}-\\d{4}") // Checks if the field is a valid license plate
        String licenseNumber,
        @NotNull
        Brand brand,
        @NotBlank
        String model,
        @NotNull
        Color color,
        boolean available
) {
}
