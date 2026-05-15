package br.dev.hygino.erp.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();

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
