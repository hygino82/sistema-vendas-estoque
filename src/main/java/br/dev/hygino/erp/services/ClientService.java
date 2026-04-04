package br.dev.hygino.erp.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.dev.hygino.erp.dtos.RequestClientDto;
import br.dev.hygino.erp.dtos.ResponseClientDto;
import br.dev.hygino.erp.entities.Client;
import br.dev.hygino.erp.repository.ClientRepository;
import br.dev.hygino.erp.services.exceptions.DatabaseException;
import br.dev.hygino.erp.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
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

    @Transactional
    public ResponseClientDto update(@Valid RequestClientDto dto, long id) {
        try {
            Client client = clientRepository.getReferenceById(id);
            dtoToEntity(dto, client);
            client = clientRepository.save(client);
            return new ResponseClientDto(client);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Dados do cliente inválidos");
        }
    }

    @Transactional(readOnly = true)
    public ResponseClientDto findById(long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return new ResponseClientDto(client);
    }

    @Transactional(readOnly = true)
    public Page<ResponseClientDto> findClients(Pageable pageable, String name, String state, String city) {
        Page<Client> clients = clientRepository.getEmployees(pageable, name, state, city);
        return clients.map(ResponseClientDto::new);
    }

    public void delete(long id) {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
    }
}
