package br.dev.hygino.erp.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.erp.dtos.RequestProductDto;
import br.dev.hygino.erp.dtos.ResponseProductDto;
import br.dev.hygino.erp.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public final class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseProductDto> createProduct(@RequestBody @Valid RequestProductDto dto) {
        ResponseProductDto response = productService.insert(dto);
        URI uri = URI.create("/api/v1/product/" + response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseProductDto>> getProducts(Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String name) {
        Page<ResponseProductDto> response = productService.getProducts(pageable, name);
        return ResponseEntity.ok(response);
    }
}
