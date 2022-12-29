package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.RentalDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Rental;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    @Transactional
    public void rent(@RequestBody @Valid RentalDto rentalDto) {
        rentalService.rent(rentalDto);
    }

    @GetMapping
    public Iterable<Rental> list() {
        return rentalService.list();
    }

    @PutMapping("/{id}")
    @Transactional
    public void finishRental (@RequestBody @PathVariable Long id) {
        rentalService.finishRental(id);
    }
}
