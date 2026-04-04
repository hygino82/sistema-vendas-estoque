package br.dev.hygino.erp.controllers;

import br.dev.hygino.erp.dtos.*;
import br.dev.hygino.erp.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}
