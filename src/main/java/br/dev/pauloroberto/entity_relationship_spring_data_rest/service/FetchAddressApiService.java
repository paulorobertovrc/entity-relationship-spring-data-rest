package br.dev.pauloroberto.entity_relationship_spring_data_rest.service;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.FetchedAddressFromApiDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FetchAddressApiService {
    private static final String API_URL = "https://cep.awesomeapi.com.br/";

    // This class is responsible for fetching the address from the API and returning it as a DTO
    public FetchedAddressFromApiDto getCityByZipCode(String zipCode) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FetchedAddressFromApiDto> response = restTemplate.exchange(
                API_URL + zipCode,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
}
