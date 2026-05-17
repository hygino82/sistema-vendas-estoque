package br.dev.hygino.erp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

public interface IService<I, O> {
    O insert(@Valid I dto);

    O getById(long id);

    Page<O> getAll(Pageable pageable, String name);

    O update(long id, @Valid I dto);

    void delete(long id);
}
