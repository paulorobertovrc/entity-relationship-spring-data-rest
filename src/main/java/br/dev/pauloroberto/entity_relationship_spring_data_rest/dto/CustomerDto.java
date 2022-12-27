package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Rental;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CustomerDto(
        @NotBlank // Checks if the field (string) is not null and not empty
        String name,
        @NotBlank
        @Email // Checks if the field is a valid email
        String email,
        @Valid
        AddressDto address,
        List<Rental> rentals
) {
}
