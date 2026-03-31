package br.dev.hygino.erp.services;

import br.dev.hygino.erp.dtos.RequestEmployeeDto;
import br.dev.hygino.erp.dtos.ResponseEmployeeDto;
import br.dev.hygino.erp.entities.Employee;
import org.springframework.stereotype.Repository;

import br.dev.hygino.erp.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public ResponseEmployeeDto insert(@Valid RequestEmployeeDto dto) {
        Employee employee = new Employee();
        dtoToEntity(dto, employee);
        //TODO adicionar criptografia
        employee = employeeRepository.save(employee);
        return new ResponseEmployeeDto(employee);
    }

    @Transactional
    public ResponseEmployeeDto update(@Valid RequestEmployeeDto dto, long id) {
        Employee employee = employeeRepository.getReferenceById(id);

        dtoToEntity(dto, employee);
        employee = employeeRepository.save(employee);
        return new ResponseEmployeeDto(employee);
    }

    private void dtoToEntity(RequestEmployeeDto dto, Employee entity) {
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

        // Dados Profissionais e de Acesso
        entity.setJobTitle(dto.jobTitle());
        //TODO adicionar criptografia
        entity.setPassword(dto.password()); // Lembre-se de tratar a senha (criptografar) antes de salvar!
        entity.setAccessLevel(dto.accessLevel());
    }
}
