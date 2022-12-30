package br.dev.pauloroberto.entity_relationship_spring_data_rest.service;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.AddressDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.model.Address;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void create(AddressDto address) {
        addressRepository.save(new Address(address));
    }

    public Page<Address> list(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public Address list(Long id) {
        if (addressRepository.findById(id).isPresent()) {
            return addressRepository.findById(id).get();
        } else {
            throw new RuntimeException("Address not found");
        }
    }

    public void update(Long id, AddressDto addressDto) {
        if (addressRepository.findById(id).isPresent()) {
            Address address = addressRepository.findById(id).orElseThrow();

            if (!addressDto.logradouro().isEmpty()) {
                address.setLogradouro(addressDto.logradouro());
            }

            if (!addressDto.numero().isEmpty()) {
                address.setNumero(addressDto.numero());
            }

            if (!addressDto.complemento().isEmpty()) {
                address.setComplemento(addressDto.complemento());
            }

            if (!addressDto.bairro().isEmpty()) {
                address.setBairro(addressDto.bairro());
            }

            if (!addressDto.cep().isEmpty()) {
                address.setCep(addressDto.cep());
            }

            if (!addressDto.cidade().isEmpty()) {
                address.setCidade(addressDto.cidade());
            }

            if (!addressDto.uf().isEmpty()) {
                address.setUf(addressDto.uf());
            }
        }
    }
}
