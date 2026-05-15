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
import br.dev.hygino.erp.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Transactional
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
    public Page<ResponseProductDto> getProducts(Pageable pageable, String name) {
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, pageable);
        return products.map(ResponseProductDto::new);
    }
}
