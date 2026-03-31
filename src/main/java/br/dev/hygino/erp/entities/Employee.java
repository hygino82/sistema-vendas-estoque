package br.dev.hygino.erp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.*;

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
    private String name;

    @NotBlank
    private String cpf;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String cep;

    @NotBlank
    private String address;

    @NotNull
    private Integer number;

    private String addressComplement;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String city;

    @NotBlank
    private String jobTitle;

    @NotBlank
    private String state;

    @NotBlank
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
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
