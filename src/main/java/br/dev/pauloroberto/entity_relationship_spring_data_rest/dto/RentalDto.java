package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Customer;

public record RentalDto(String licenseNumber, Long customerId) {
}
