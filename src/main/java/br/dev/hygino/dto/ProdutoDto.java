package br.dev.hygino.dto;

public record ProdutoDto(
        int id,
        String descricao,
        double preco,
        int estoque,
        String fornecedor) {

}
