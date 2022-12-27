package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDto(
        @NotBlank
        String logradouro,
        String numero,
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}") // Checks if the field is a valid zip code
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        @Pattern(regexp = "[A-Z]{2}") // Checks if the field is a valid state
        String uf
) {
}