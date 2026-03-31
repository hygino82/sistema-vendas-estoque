package br.dev.hygino.erp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @CPF
    @Size(max = 14)
    private String cpf;

    @Email
    @Column(unique = true)
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(max = 10)
    private String cep;

    @NotBlank
    @Size(max = 100)
    private String address;

    @NotNull
    @Size(max = 10)
    private Integer number;

    @Size(max = 20)
    private String addressComplement;

    @NotBlank
    @Size(max = 40)
    private String neighborhood;

    @NotBlank
    @Size(max = 40)
    private String city;

    @NotBlank
    @Size(max = 40)
    private String jobTitle;

    @NotBlank
    @Size(max = 40)
    private String state;

    @NotBlank
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;

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
