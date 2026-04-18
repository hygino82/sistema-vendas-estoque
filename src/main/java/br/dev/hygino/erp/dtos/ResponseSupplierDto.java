package br.dev.hygino.erp.dtos;

import br.dev.hygino.erp.entities.Supplier;

public record ResponseSupplierDto(
        long id,
        String name,
        String cnpj,
        String email,
        String phoneNumber,
        String zipCode,
        String address,
        int number,
        String complement,
        String district,
        String city,
        String state) {

    public ResponseSupplierDto(Supplier supplier) {
        this(
                supplier.getId(),
                supplier.getName(),
                supplier.getCnpj(),
                supplier.getEmail(),
                supplier.getPhoneNumber(),
                supplier.getZipCode(),
                supplier.getAddress(),
                supplier.getNumber(),
                supplier.getComplement(),
                supplier.getDistrict(),
                supplier.getCity(),
                supplier.getState().toString());
    }
}