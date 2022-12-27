package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import jakarta.validation.constraints.Email;
import org.springframework.web.bind.annotation.RequestParam;

public record CustomerUpdateDto(
        @RequestParam(required = false)
        String name,
        @Email @RequestParam(required = false)
        String email,
        @RequestParam(required = false)
        Long addressId
) {
}
