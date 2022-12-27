package br.dev.pauloroberto.entity_relationship_spring_data_rest.model;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) // Relacionamento com a tabela Address (Um para Um) // CascadeType.PERSIST: quando um cliente for salvo, o endereço também será salvo
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @RestResource(path = "customerAddress", rel = "address") // Altera o nome do endpoint para /customerAddress
    private Address address;

    @ManyToMany
    @RestResource(path = "customerRentals", rel = "rentals") // Altera o nome do endpoint para /customerRentals
    private List<Rental> rentals;

    public Customer(CustomerDto customerDto) {
        this.name = customerDto.name();
        this.email = customerDto.email();

        if (customerDto.address() != null) {
            this.address = new Address(customerDto.address());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}
