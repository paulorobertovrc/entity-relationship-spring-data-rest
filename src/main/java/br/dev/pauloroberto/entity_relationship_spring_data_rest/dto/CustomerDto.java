package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Rental;

import java.util.List;

public record CustomerDto(String name, String email, AddressDto address, List<Rental> rentals) {
}
