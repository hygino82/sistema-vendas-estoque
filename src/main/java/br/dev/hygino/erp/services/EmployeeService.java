package br.dev.hygino.erp.services;

import br.dev.hygino.erp.dtos.RequestEmployeeDto;
import br.dev.hygino.erp.dtos.ResponseEmployeeDto;
import br.dev.hygino.erp.entities.Employee;
import org.springframework.stereotype.Repository;

import br.dev.hygino.erp.repository.EmployeeRepository;
import br.dev.hygino.erp.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Repository
@Validated
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public ResponseEmployeeDto insert(@Valid RequestEmployeeDto dto) {
        Employee employee = new Employee();
        dtoToEntity(dto, employee);
        // TODO adicionar criptografia
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
        // TODO adicionar criptografia
        entity.setPassword(dto.password()); // Lembre-se de tratar a senha (criptografar) antes de salvar!
        entity.setAccessLevel(dto.accessLevel());
    }

    @Transactional(readOnly = true)
    public Page<ResponseEmployeeDto> getEmployees(Pageable pageable, String name, String state, String city) {
        final var page = employeeRepository.getEmployees(pageable, name, state, city);
        return page.map(ResponseEmployeeDto::new);
    }

    @Transactional(readOnly = true)
    public ResponseEmployeeDto getEmployeeByEmail(@Email @NotBlank String email) {
        final var result = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));

        return new ResponseEmployeeDto(result);
    }

    public void delete(long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found!");
        }
        employeeRepository.deleteById(id);
    }
}
