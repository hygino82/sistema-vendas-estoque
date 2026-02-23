package br.dev.hygino.dao;

import br.dev.hygino.jdbc.ConexaoBanco;
import br.dev.hygino.models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
// Supondo que a classe Cliente já exista com getters para os atributos

public class ClienteDao {

    private final Connection connection;

    public ClienteDao() {
        this.connection = new ConexaoBanco().getConnection();
    }

    public void salvar(Cliente cliente) {
        final var sql = """
                   INSERT INTO tb_clientes(
                   nome,
                   rg,
                   cpf,
                   email,
                   telefone,
                   celular,
                   cep,
                   numero,
                   complemento,
                   bairro,
                   cidade,
                   estado,
                   endereco)
                   VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)
                   """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setString(7, cliente.getCep());
            stmt.setInt(8, cliente.getNumero());
            stmt.setString(9, cliente.getComplemento());
            stmt.setString(10, cliente.getBairro());
            stmt.setString(11, cliente.getCidade());
            stmt.setString(12, cliente.getEstado());
            stmt.setString(13, cliente.getEndereco());

            // 2. Executar a consulta
            stmt.execute(); // ou stmt.executeUpdate() se você quiser saber quantas linhas foram afetadas
            JOptionPane.showMessageDialog(null, "Cliente " + cliente.getNome() + " salvo com sucesso!");
            closeConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente: " + e.getMessage());
        }
    }

    // Método para fechar a conexão (geralmente gerenciada externamente ou por um pool)
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
