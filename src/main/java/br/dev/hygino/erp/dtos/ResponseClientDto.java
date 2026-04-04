package br.dev.hygino.erp.dtos;

import java.time.LocalDateTime;

import br.dev.hygino.erp.entities.Client;

public record ResponseClientDto(
        Long id,
        String name,
        String cpf,
        String email,
        String phoneNumber,
        String cep,
        String address,
        Integer number,
        String addressComplement,
        String neighborhood,
        String city,
        String state,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ResponseClientDto(Client client) {
        this(client.getId(),
                client.getName(),
                client.getCpf(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getCep(),
                client.getAddress(),
                client.getNumber(),
                client.getAddressComplement(),
                client.getNeighborhood(),
                client.getCity(),
                client.getState(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }
}
