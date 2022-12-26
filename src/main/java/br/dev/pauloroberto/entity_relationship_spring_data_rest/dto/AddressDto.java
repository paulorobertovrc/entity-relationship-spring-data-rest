package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

public record AddressDto(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        String cidade,
        String uf
) {
}