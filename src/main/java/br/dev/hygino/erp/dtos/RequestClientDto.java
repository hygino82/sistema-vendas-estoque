package br.dev.hygino.erp.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record RequestClientDto(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String name,
        
        @NotBlank(message = "CPF é obrigatório")
        @CPF(message = "CPF inválido")
        @Size(max = 14, message = "CPF deve ter no máximo 14 caracteres")
        String cpf,
        
        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
        String email,
        
        @NotBlank(message = "Telefone é obrigatório")
        @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
        String phoneNumber,
        
        @NotBlank(message = "CEP é obrigatório")
        @Size(max = 10, message = "CEP deve ter no máximo 10 caracteres")
        String cep,
        
        @NotBlank(message = "Endereço é obrigatório")
        @Size(max = 100, message = "Endereço deve ter no máximo 100 caracteres")
        String address,
        
        @NotNull(message = "Número é obrigatório")
        @Positive
        Integer number,
        
        @Size(max = 20, message = "Complemento deve ter no máximo 20 caracteres")
        String addressComplement,
        
        @NotBlank(message = "Bairro é obrigatório")
        @Size(max = 40, message = "Bairro deve ter no máximo 40 caracteres")
        String neighborhood,
        
        @NotBlank(message = "Cidade é obrigatória")
        @Size(max = 40, message = "Cidade deve ter no máximo 40 caracteres")
        String city,
        
        @NotBlank(message = "Estado é obrigatório")
        @Size(max = 40, message = "Estado deve ter no máximo 40 caracteres")
        String state
        ) {
}
