package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import jakarta.validation.constraints.Pattern;

public record AddressDto(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        @Pattern(regexp = "\\d{5}-\\d{3}") // Checks if the field is a valid zip code
        String cep,
        String cidade,
        @Pattern(regexp = "[A-Z]{2}") // Checks if the field is a valid state
        String uf
) {
}