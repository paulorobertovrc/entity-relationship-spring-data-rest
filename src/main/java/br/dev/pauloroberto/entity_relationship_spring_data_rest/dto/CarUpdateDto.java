package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.RequestParam;

public record CarUpdateDto(
        @RequestParam(required = false)
        @Pattern(regexp = "(^$)|([A-Z]{3}-\\d{4})") // Checks if the field is a valid license plate
        String licenseNumber,
        @NotNull @RequestParam(required = false)
        String model,
        @NotNull @RequestParam(required = false)
        String color
) {
}
