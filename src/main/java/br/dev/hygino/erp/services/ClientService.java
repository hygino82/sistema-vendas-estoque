package br.dev.hygino.erp.services;

import br.dev.hygino.erp.dtos.RequestClientDto;
import br.dev.hygino.erp.dtos.ResponseClientDto;
import br.dev.hygino.erp.entities.Client;
import br.dev.hygino.erp.repository.ClientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public ResponseClientDto insert(@Valid RequestClientDto dto) {
        Client client = new Client();
        dtoToEntity(dto, client);
        client = clientRepository.save(client);
        return new ResponseClientDto(client);
    }

    private void dtoToEntity(RequestClientDto dto, Client entity) {
        // Dados Pessoais e Contato
        entity.setName(dto.name());
        entity.setCpf(dto.cpf());
        entity.setEmail(dto.email());
        entity.setPhoneNumber(dto.phoneNumber());

        // Endereço
        entity.setCep(dto.cep());
        entity.setAddress(dto.address());
        entity.setNumber(dto.number());
        entity.setAddressComplement(dto.addressComplement());
        entity.setNeighborhood(dto.neighborhood());
        entity.setCity(dto.city());
        entity.setState(dto.state());
    }
}
