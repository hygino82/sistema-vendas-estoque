package br.dev.hygino.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private Integer id;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;
    private Fornecedor fornecedor;
}
