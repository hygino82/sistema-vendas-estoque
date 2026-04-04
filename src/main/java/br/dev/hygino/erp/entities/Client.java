package br.dev.hygino.erp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @CPF
    @Column(length = 14)
    private String cpf;

    @Email
    @Column(unique = true, length = 100)
    private String email;

    @NotBlank
    @Column(length = 20)
    private String phoneNumber;

    @NotBlank
    @Column(length = 10)
    private String cep;

    @NotBlank
    @Column(length = 100)
    private String address;

    @NotNull
    @Column(length = 10)
    @Positive
    private Integer number;

    @Size(max = 20)
    private String addressComplement;

    @NotBlank
    @Column(length = 40)
    private String neighborhood;

    @NotBlank
    @Column(length = 40)
    private String city;

    @NotBlank
    @Column(length = 40)
    private String state;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @PrePersist
    private void create() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void update() {
        updatedAt = LocalDateTime.now();
    }
}
