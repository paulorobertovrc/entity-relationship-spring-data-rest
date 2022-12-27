package br.dev.pauloroberto.entity_relationship_spring_data_rest.model;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relacionamento com a tabela Customer (Muitos para Um)
    // CascadeType.ALL: quando um endereço for salvo, o cliente também será salvo
    // FetchType.LAZY: quando um cliente for buscado, não será buscado os endereços dele // FetchType.EAGER: quando um cliente for buscado, será buscado os endereços dele
    @JoinColumn(name = "customer_id", referencedColumnName = "id") // nome da coluna na tabela de endereço que referencia a tabela de cliente
    @JsonIgnore // Ignora o campo no retorno do JSON
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
