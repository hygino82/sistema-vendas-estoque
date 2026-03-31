package br.dev.hygino.erp.dtos;

import java.time.LocalDateTime;

import br.dev.hygino.erp.entities.AccessLevel;
import br.dev.hygino.erp.entities.Employee;

public record ResponseEmployeeDto(
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
        String jobTitle,
        String state,
        AccessLevel accessLevel,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ResponseEmployeeDto(Employee employee) {
        this(
                employee.getId(),
                employee.getName(),
                employee.getCpf(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getCep(),
                employee.getAddress(),
                employee.getNumber(),
                employee.getAddressComplement(),
                employee.getNeighborhood(),
                employee.getCity(),
                employee.getJobTitle(),
                employee.getState(),
                employee.getAccessLevel(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }
}
