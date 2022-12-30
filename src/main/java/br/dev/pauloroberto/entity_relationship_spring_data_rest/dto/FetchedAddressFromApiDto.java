package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FetchedAddressFromApiDto(
        @NotBlank String address_type,
        @NotBlank String address_name,
        @Pattern(regexp = "[A-Z]{2}") String state,
        @NotBlank String city,
        @NotBlank String district
) {
}
