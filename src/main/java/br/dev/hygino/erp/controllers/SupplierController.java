package br.dev.hygino.erp.controllers;

import java.net.URI;

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

import br.dev.hygino.erp.dtos.RequestSupplierDto;
import br.dev.hygino.erp.dtos.ResponseSupplierDto;
import br.dev.hygino.erp.services.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ResponseSupplierDto> createSupplier(@RequestBody @Valid RequestSupplierDto dto) {
        ResponseSupplierDto response = supplierService.insert(dto);
        URI uri = URI.create("/api/v1/supplier/" + response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseSupplierDto>> getSuppliers(
            Pageable pageable,
            @RequestParam(defaultValue = "") String name) {
        Page<ResponseSupplierDto> response = supplierService.getSuppliers(pageable, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSupplierDto> getSupplier(@PathVariable Long id) {
        ResponseSupplierDto response = supplierService.getSupplier(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSupplierDto> updateSupplier(
            @PathVariable Long id,
            @RequestBody @Valid RequestSupplierDto dto) {
        ResponseSupplierDto response = supplierService.updateSupplier(id, dto);
        return ResponseEntity.ok(response);
    }
}
