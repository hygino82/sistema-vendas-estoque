package br.dev.hygino.dao;

import br.dev.hygino.exceptions.ClientNotFoundException;
import br.dev.hygino.jdbc.ConexaoBanco;
import br.dev.hygino.models.Cliente;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
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

    public Optional<Cliente> buscarCliente(String nome) throws SQLException { // Mantenha throws SQLException para propagar erros do BD
        final var sql = """
                  SELECT * FROM tb_clientes
                  WHERE UPPER(nome) = UPPER(?)
                  """;

        Optional<Cliente> result = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente clienteEncontrado = new Cliente();
                    clienteEncontrado.setId(rs.getInt("id"));
                    clienteEncontrado.setNome(rs.getString("nome"));
                    clienteEncontrado.setRg(rs.getString("rg"));
                    clienteEncontrado.setCpf(rs.getString("cpf"));
                    clienteEncontrado.setEmail(rs.getString("email"));
                    clienteEncontrado.setTelefone(rs.getString("telefone"));
                    clienteEncontrado.setCelular(rs.getString("celular"));
                    clienteEncontrado.setCep(rs.getString("cep"));
                    clienteEncontrado.setNumero(rs.getInt("numero"));
                    clienteEncontrado.setComplemento(rs.getString("complemento"));
                    clienteEncontrado.setBairro(rs.getString("bairro"));
                    clienteEncontrado.setCidade(rs.getString("cidade"));
                    clienteEncontrado.setEstado(rs.getString("estado"));
                    clienteEncontrado.setEndereco(rs.getString("endereco"));
                    result = Optional.of(clienteEncontrado);
                }
                return result;
            }
        } catch (SQLException e) {
            throw new ClientNotFoundException("Erro ao buscar cliente: " + e.getMessage() + "Erro no Banco de Dados");
        }
    }

    public List<Cliente> listar(String nome) {

        List<Cliente> clientes = new ArrayList<>();

        var sql = """
        SELECT * FROM tb_clientes
        WHERE UPPER(nome) LIKE CONCAT('%', UPPER(?), '%')
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // operador de coalescência nula apenas no Java
            stmt.setString(1, nome == null ? "" : nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("rg"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("cep"),
                        rs.getString("endereco"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
                clientes.add(cliente);
            }

            return clientes;

        } catch (SQLException e) {
            throw new ClientNotFoundException(
                    "Erro ao buscar clientes: " + e.getMessage()
            );
        }
    }
}
