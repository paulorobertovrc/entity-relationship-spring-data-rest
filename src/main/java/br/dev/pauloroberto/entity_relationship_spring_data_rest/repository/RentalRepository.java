package br.dev.pauloroberto.entity_relationship_spring_data_rest.repository;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Long> {
}
