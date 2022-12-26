package br.dev.pauloroberto.entity_relationship_spring_data_rest.model;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    @RestResource(path = "customerAddress", rel = "address")
    private Address address;

    @ManyToMany
    @RestResource(path = "customerRentals", rel = "rentals")
    private List<Rental> rentals;

    public Customer(CustomerDto customerDto) {
        this.name = customerDto.name();
        this.email = customerDto.email();
        this.address = new Address(customerDto.address());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
