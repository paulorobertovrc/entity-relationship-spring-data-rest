package br.dev.pauloroberto.entity_relationship_spring_data_rest.controller;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.RentalDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Rental;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Rental", description = "Endpoints for operations about rentals")
@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Create a new rental", description = "The car must be available to be rented or an exception will be thrown. The rental's start date will be the current date and the end date will be null")
    public void rent(@RequestBody @Valid RentalDto rentalDto) {
        rentalService.rent(rentalDto);
    }

    @GetMapping
    @Operation(summary = "List all rentals", description = "List all rentals with pagination and sorting support")
    public Iterable<Rental> list(@PageableDefault(size = 5) Pageable pageable) {
        return rentalService.list(pageable);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Return a car", description = "Only active rentals can be returned. Also, the car will be marked as available and the rental's end date will be set")
    public void finishRental (@RequestBody @PathVariable Long id) {
        rentalService.finishRental(id);
    }
}
