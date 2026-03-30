package br.dev.hygino.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {

    private Integer id;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private String celular;
    private String cep;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
