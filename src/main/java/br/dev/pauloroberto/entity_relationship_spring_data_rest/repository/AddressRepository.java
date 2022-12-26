package br.dev.pauloroberto.entity_relationship_spring_data_rest.repository;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
