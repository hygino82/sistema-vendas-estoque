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
    private String jobTitle;

    @NotBlank
    @Column(length = 40)
    private String state;

    @NotBlank
    @Column(length = 40)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Employee(String name, String cpf, String email, String phoneNumber, String cep, String address, Integer number, String addressComplement, String neighborhood, String city, String jobTitle, String state, String password, br.dev.hygino.erp.entities.AccessLevel accessLevel) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cep = cep;
        this.address = address;
        this.number = number;
        this.addressComplement = addressComplement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.jobTitle = jobTitle;
        this.state = state;
        this.password = password;
        this.accessLevel = accessLevel;
    }

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
