package br.dev.pauloroberto.entity_relationship_spring_data_rest.model;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.AddressDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String logradouro;

    @Column
    private String numero;

    @Column
    private String complemento;

    @Column
    private String bairro;

    @Column
    private String cep;

    @Column
    private String cidade;

    @Column
    private String uf;

    @OneToOne(mappedBy = "address")
    private Customer customer;

    public Address(AddressDto address) {
            this.logradouro = address.logradouro();
            this.numero = address.numero();
            this.complemento = address.complemento();
            this.bairro = address.bairro();
            this.cep = address.cep();
            this.cidade = address.cidade();
            this.uf = address.uf();
    }
}
