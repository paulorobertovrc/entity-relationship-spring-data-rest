package br.dev.pauloroberto.entity_relationship_spring_data_rest.repository;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Page<Customer> findAllByActiveTrue(Pageable pageable);
}
