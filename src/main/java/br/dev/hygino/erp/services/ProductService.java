package br.dev.hygino.erp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.dev.hygino.erp.dtos.RequestProductDto;
import br.dev.hygino.erp.dtos.ResponseProductDto;
import br.dev.hygino.erp.entities.Product;
import br.dev.hygino.erp.entities.Supplier;
import br.dev.hygino.erp.repository.ProductRepository;
import br.dev.hygino.erp.repository.SupplierRepository;
import br.dev.hygino.erp.services.exceptions.DatabaseException;
import br.dev.hygino.erp.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class ProductService implements IService<RequestProductDto, ResponseProductDto> {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Transactional
    @Override
    public ResponseProductDto insert(@Valid RequestProductDto dto) {
        final var supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found!"));
        var product = new Product();
        dtoToEntity(dto, product, supplier);
        productRepository.save(product);
        return new ResponseProductDto(product);
    }

    private void dtoToEntity(RequestProductDto dto, Product product, Supplier supplier) {
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setSupplier(supplier);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ResponseProductDto> getAll(Pageable pageable, String name) {
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, pageable);
        return products.map(ResponseProductDto::new);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseProductDto getById(long id) {
        final Product result = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        return new ResponseProductDto(result);
    }

    @Override
    @Transactional
    public ResponseProductDto update(long id, @Valid RequestProductDto dto) {
        final var supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found!"));
        try {
            var product = productRepository.getReferenceById(id);
            dtoToEntity(dto, product, supplier);
            product = productRepository.save(product);
            return new ResponseProductDto(product);
        } catch (EntityNotFoundException e) {
            throw new DatabaseException("Product not found!");
        }
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
