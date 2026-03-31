package br.dev.hygino.erp.dtos;

import br.dev.hygino.erp.entities.AccessLevel;
import br.dev.hygino.erp.entities.Employee;

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

    public Employee toEmployeeEntity() {
        return new Employee(
                name,
                cpf,
                email,
                phoneNumber,
                cep,
                address,
                number,
                addressComplement,
                neighborhood,
                city,
                jobTitle,
                state,
                password,
                accessLevel
        );
    }
}
