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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
