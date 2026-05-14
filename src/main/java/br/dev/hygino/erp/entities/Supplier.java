package br.dev.hygino.erp.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 18, nullable = false, unique = true)
    private String cnpj;

    @Column(length = 100, unique = true)
    @Email
    private String email;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Column(length = 10, nullable = false)
    private String zipCode;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer number;

    @Column(length = 100)

    private String complement;

    @Column(length = 40, nullable = false)
    private String district;

    @Column(length = 40, nullable = false)
    private String city;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

   // @NotNull
    private LocalDateTime createdAt;

   // @NotNull
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
