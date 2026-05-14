package br.dev.hygino.erp.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.dev.hygino.erp.dtos.RequestSupplierDto;
import br.dev.hygino.erp.dtos.ResponseSupplierDto;
import br.dev.hygino.erp.entities.State;
import br.dev.hygino.erp.entities.Supplier;
import br.dev.hygino.erp.repository.SupplierRepository;
import br.dev.hygino.erp.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
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

    @Transactional(readOnly = true)
    public Page<ResponseSupplierDto> getSuppliers(Pageable pageable, String name) {
        Page<Supplier> suppliers = supplierRepository.findByNameContainingIgnoreCase(pageable, name);
        return suppliers.map(ResponseSupplierDto::new);
    }

    @Transactional(readOnly = true)
    public ResponseSupplierDto getSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found!"));
        return new ResponseSupplierDto(supplier);
    }

    public void deleteSupplier(Long id) {
        try {
            supplierRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Supplier not found!");
        }
    }

    @Transactional
    public ResponseSupplierDto updateSupplier(Long id, RequestSupplierDto dto) {
        try {
            Supplier supplier = supplierRepository.getReferenceById(id);
            dtoToEntity(dto, supplier);
            supplier = supplierRepository.save(supplier);
            return new ResponseSupplierDto(supplier);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Supplier not found!");
        }
    }
}
