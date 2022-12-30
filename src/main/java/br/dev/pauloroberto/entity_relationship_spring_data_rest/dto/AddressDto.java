package br.dev.pauloroberto.entity_relationship_spring_data_rest.dto;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.FetchAddressApiService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.RequestParam;

public record AddressDto(
        @RequestParam(required = false) String logradouro,
        @NotBlank(message = "The field NUMBER must not be blank") String numero,
        @RequestParam(required = false) String complemento,
        @RequestParam(required = false) String bairro,
        @Pattern(regexp = "\\d{5}-\\d{3}") // Checks if the field is a valid zip code
        String cep,
        @RequestParam(required = false) String cidade,
        @RequestParam(required = false) @Pattern(regexp = "(^$)|([A-Z]{2})") // Checks if the field is a valid state
        String uf
) {
    public AddressDto {
        if (logradouro == null || logradouro.isBlank()) {
            FetchedAddressFromApiDto addressFetchApiDto = new FetchAddressApiService().getCityByZipCode(cep);
            logradouro = addressFetchApiDto.address_type() + " " + addressFetchApiDto.address_name();
            bairro = addressFetchApiDto.district();
            cidade = addressFetchApiDto.city();
            uf = addressFetchApiDto.state();
        }
    }
}