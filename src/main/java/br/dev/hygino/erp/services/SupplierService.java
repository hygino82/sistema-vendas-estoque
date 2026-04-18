package br.dev.hygino.erp.services;

import org.springframework.stereotype.Service;

import br.dev.hygino.erp.dtos.RequestSupplierDto;
import br.dev.hygino.erp.dtos.ResponseSupplierDto;
import br.dev.hygino.erp.entities.State;
import br.dev.hygino.erp.entities.Supplier;
import br.dev.hygino.erp.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Transactional
    public ResponseSupplierDto insert(@Valid RequestSupplierDto dto) {
        Supplier supplier = new Supplier();
        dtoToEntity(dto, supplier);
        supplier = supplierRepository.save(supplier);
        return new ResponseSupplierDto(supplier);
    }

    private void dtoToEntity(RequestSupplierDto dto, Supplier entity) {
        entity.setName(dto.name());
        entity.setCnpj(dto.cnpj());
        entity.setEmail(dto.email());
        entity.setPhoneNumber(dto.phoneNumber());
        entity.setAddress(dto.address());
        entity.setCity(dto.city());
        entity.setState(State.valueOf(dto.state()));
        entity.setZipCode(dto.zipCode());
        entity.setDistrict(dto.district());
        entity.setNumber(dto.number());
        entity.setComplement(dto.complement());
    }
}
