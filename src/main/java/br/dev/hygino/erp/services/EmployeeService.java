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
        Employee employee = dto.toEmployeeEntity();
        employee = employeeRepository.save(employee);
        return new ResponseEmployeeDto(employee);
    }
}
