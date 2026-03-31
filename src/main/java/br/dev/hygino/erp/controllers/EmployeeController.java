package br.dev.hygino.erp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.erp.dtos.RequestEmployeeDto;
import br.dev.hygino.erp.dtos.ResponseEmployeeDto;
import br.dev.hygino.erp.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<ResponseEmployeeDto> createEmployee(@RequestBody @Valid RequestEmployeeDto dto) {
        var result = service.insert(dto);
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEmployeeDto> updateEmployee(@RequestBody @Valid RequestEmployeeDto dto, @PathVariable long id) {
        var result = service.update(dto, id);
        return ResponseEntity.status(200).body(result);
    }
}
