package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Brand;

public record CarDto(
        String licenseNumber,
        Brand brand,
        String model,
        String color,
        boolean available
) {
}
