package br.dev.hygino.erp.controllers;

import org.springframework.http.ResponseEntity;

import br.dev.hygino.erp.dtos.RequestEmployeeDto;
import br.dev.hygino.erp.dtos.ResponseEmployeeDto;
import br.dev.hygino.erp.services.EmployeeService;
import br.dev.hygino.erp.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseEmployeeDto> updateEmployee(@RequestBody @Valid RequestEmployeeDto dto,
            @PathVariable long id) {
        var result = service.update(dto, id);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findEmployeeByEmail(@PathVariable @NotBlank @Email String email) {
        try {
            var result = service.getEmployeeByEmail(email);
            return ResponseEntity.status(200).body(result);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<ResponseEmployeeDto>> getEmployees(
            Pageable pageable,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String city,
            @RequestParam(defaultValue = "") String state) {
        var result = service.getEmployees(pageable, name, state, city);
        return ResponseEntity.status(200).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(204).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
