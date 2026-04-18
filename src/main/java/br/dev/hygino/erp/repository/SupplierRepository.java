package br.dev.hygino.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.hygino.erp.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
