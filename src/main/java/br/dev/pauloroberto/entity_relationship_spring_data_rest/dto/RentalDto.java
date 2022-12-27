package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RentalDto(
        @NotBlank
        @Pattern(regexp = "[A-Z]{3}-\\d{4}") // Checks if the field is a valid license plate
        String licenseNumber,
        @NotNull
        Long customerId
) {
}
