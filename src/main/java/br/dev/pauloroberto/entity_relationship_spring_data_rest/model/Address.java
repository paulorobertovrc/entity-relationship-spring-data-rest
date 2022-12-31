package br.dev.pauloroberto.entity_relationship_spring_data_rest.model;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @JsonIgnore // Ignora o campo no retorno do JSON
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
