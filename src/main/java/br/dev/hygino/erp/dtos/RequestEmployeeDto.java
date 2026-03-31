package br.dev.hygino.erp.dtos;

import br.dev.hygino.erp.entities.AccessLevel;

public record RequestEmployeeDto(
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
        String jobTitle,
        String state,
        String password,
        AccessLevel accessLevel
        ) {
}
