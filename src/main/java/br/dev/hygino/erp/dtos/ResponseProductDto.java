package br.dev.hygino.erp.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.dev.hygino.erp.entities.Product;

public record ResponseProductDto(
                long id,
                String name,
                String description,
                BigDecimal price,
                int stock,
                String supplierName,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

        public ResponseProductDto(Product product) {
                this(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getPrice(),
                                product.getStock(),
                                product.getSupplier().getName(),
                                product.getCreatedAt(),
                                product.getUpdatedAt());
        }
}