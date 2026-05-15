package br.dev.hygino.erp.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record RequestProductDto(

        @NotBlank @Size(min = 3, max = 100) String name,

        @NotBlank @Size(min = 3, max = 100) String description,

        @NotNull @Positive BigDecimal price,

        @PositiveOrZero int stock,

        @Positive long supplierId) {

}
