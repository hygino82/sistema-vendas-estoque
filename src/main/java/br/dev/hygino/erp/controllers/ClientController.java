package br.dev.hygino.erp.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.erp.dtos.RequestClientDto;
import br.dev.hygino.erp.dtos.ResponseClientDto;
import br.dev.hygino.erp.services.ClientService;
import br.dev.hygino.erp.services.exceptions.DatabaseException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @PostMapping
    public ResponseEntity<ResponseClientDto> createClient(@RequestBody @Valid RequestClientDto dto) {
        var result = service.insert(dto);
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@RequestBody @Valid RequestClientDto dto,
            @PathVariable long id) {
        try {
            var result = service.update(dto, id);
            return ResponseEntity.ok(result);
        } catch (DatabaseException | DataIntegrityViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientDto> getClientById(@PathVariable long id) {
        var result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseClientDto>> getAllClients(
            Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String state,
            @RequestParam(required = false, defaultValue = "") String city) {
        var result = service.findClients(pageable, name, state, city);
        return ResponseEntity.ok(result);
    }
}
