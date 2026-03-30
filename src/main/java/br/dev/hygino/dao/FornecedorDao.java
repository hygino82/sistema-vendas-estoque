package br.dev.hygino.dao;

import br.dev.hygino.exceptions.ResourceNotFoundException;
import br.dev.hygino.jdbc.ConexaoBanco;
import br.dev.hygino.models.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public class FornecedorDao {

    private final Connection connection;

    public FornecedorDao() {
        this.connection = new ConexaoBanco().getConnection();
    }

    public void salvar(Fornecedor fornecedor) {

        final var sql = """
                   INSERT INTO tb_fornecedores(
                   nome,
                   cnpj,
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
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCelular());
            stmt.setString(6, fornecedor.getCep());
            stmt.setInt(7, fornecedor.getNumero());
            stmt.setString(8, fornecedor.getComplemento());
            stmt.setString(9, fornecedor.getBairro());
            stmt.setString(10, fornecedor.getCidade());
            stmt.setString(11, fornecedor.getEstado());
            stmt.setString(12, fornecedor.getEndereco());

            // 2. Executar a consulta
            stmt.execute(); // ou stmt.executeUpdate() se você quiser saber quantas linhas foram afetadas
            JOptionPane.showMessageDialog(null, "Fornecedor " + fornecedor.getNome() + " salvo com sucesso!");
            //closeConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar fornecedor: " + e.getMessage());
        }
    }

    public List<Fornecedor> listar(String nome) {

        List<Fornecedor> fornecedores = new ArrayList<>();

        var sql = """
        SELECT * FROM tb_fornecedores
        WHERE UPPER(nome) LIKE CONCAT('%', UPPER(?), '%')
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // operador de coalescência nula apenas no Java
            stmt.setString(1, nome == null ? "" : nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
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
                fornecedores.add(fornecedor);
            }

            return fornecedores;

        } catch (SQLException e) {
            throw new ResourceNotFoundException(
                    "Erro ao buscar fornecedores: " + e.getMessage()
            );
        }
    }

    public Optional<Fornecedor> buscarFornecedor(String nome) {
        final var sql = """
                  SELECT * FROM tb_fornecedores
                  WHERE UPPER(nome) = UPPER(?)
                  """;

        Optional<Fornecedor> result = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Fornecedor fornecedorEncontrado = new Fornecedor();
                    fornecedorEncontrado.setId(rs.getInt("id"));
                    fornecedorEncontrado.setNome(rs.getString("nome"));
                    fornecedorEncontrado.setCnpj(rs.getString("cnpj"));
                    fornecedorEncontrado.setEmail(rs.getString("email"));
                    fornecedorEncontrado.setTelefone(rs.getString("telefone"));
                    fornecedorEncontrado.setCelular(rs.getString("celular"));
                    fornecedorEncontrado.setCep(rs.getString("cep"));
                    fornecedorEncontrado.setNumero(rs.getInt("numero"));
                    fornecedorEncontrado.setComplemento(rs.getString("complemento"));
                    fornecedorEncontrado.setBairro(rs.getString("bairro"));
                    fornecedorEncontrado.setCidade(rs.getString("cidade"));
                    fornecedorEncontrado.setEstado(rs.getString("estado"));
                    fornecedorEncontrado.setEndereco(rs.getString("endereco"));
                    result = Optional.of(fornecedorEncontrado);
                }
                return result;
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Erro ao buscar fornecedor: " + e.getMessage() + "Erro no Banco de Dados");
        }
    }

    public void excluir(int id) {
        final var sql = "DELETE FROM tb_fornecedores WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover fornecedor: " + e.getMessage());
        }
    }

    public void atualizar(Fornecedor fornecedor) {
        final var sql = """
                   UPDATE tb_fornecedores SET
                   nome=?,
                   cnpj=?,
                   email=?,
                   telefone=?,
                   celular=?,
                   cep=?,
                   numero=?,
                   complemento=?,
                   bairro=?,
                   cidade=?,
                   estado=?,
                   endereco=?
                   WHERE id=?     
                   """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCelular());
            stmt.setString(6, fornecedor.getCep());
            stmt.setInt(7, fornecedor.getNumero());
            stmt.setString(8, fornecedor.getComplemento());
            stmt.setString(9, fornecedor.getBairro());
            stmt.setString(10, fornecedor.getCidade());
            stmt.setString(11, fornecedor.getEstado());
            stmt.setString(12, fornecedor.getEndereco());
            stmt.setInt(13, fornecedor.getId());
            // 2. Executar a consulta
            stmt.execute(); // ou stmt.executeUpdate() se você quiser saber quantas linhas foram afetadas
            JOptionPane.showMessageDialog(null, "Fornecedor " + fornecedor.getNome() + " editado com sucesso!");
            //closeConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar fornecedor: " + e.getMessage());
        }
    }
}
