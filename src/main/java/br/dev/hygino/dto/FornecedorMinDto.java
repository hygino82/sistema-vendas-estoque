package br.dev.hygino.dto;

public record FornecedorMinDto(Integer id, String name) {

    @Override
    public String toString() {
        return name;
    }
}
