package br.dev.hygino.erp.dtos;

import jakarta.validation.constraints.*;

public record RequestSupplierDto(
        @NotBlank @Size String name,
        
        @NotBlank @Size(max = 18) String cnpj,

        @Email @NotBlank String email,

        @NotBlank @Size(max = 20) String phoneNumber,

        @NotBlank @Size(max = 10) String zipCode,

        @NotBlank @Size(max = 100) String address,

        @NotNull Integer number,

        @Size(max = 100) String complement,

        @NotBlank @Size(max = 40) String district,

        @NotBlank @Size(max = 40) String city,

        @NotBlank String state) {

}
