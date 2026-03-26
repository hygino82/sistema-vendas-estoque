package br.dev.hygino.dao;

import br.dev.hygino.exceptions.ClientNotFoundException;
import br.dev.hygino.jdbc.ConexaoBanco;
import br.dev.hygino.models.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public class FuncionarioDao {

    private final Connection connection;

    public FuncionarioDao() {
        this.connection = new ConexaoBanco().getConnection();
    }

    public List<Funcionario> listar(String nome) {

        List<Funcionario> funcionarios = new ArrayList<>();

        var sql = """
        SELECT * FROM tb_funcionarios
        WHERE UPPER(nome) LIKE CONCAT('%', UPPER(?), '%')
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome == null ? "" : nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario obj = new Funcionario(
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
                        rs.getString("estado"),
                        rs.getString("senha"),
                        rs.getString("cargo"),
                        rs.getString("nivel_acesso")
                );
                funcionarios.add(obj);
            }

            return funcionarios;

        } catch (SQLException e) {
            throw new ClientNotFoundException(
                    "Erro ao buscar funcionários: " + e.getMessage()
            );
        }
    }

    public Optional<Funcionario> buscarFuncionario(String nome) {
        final var sql = """
                  SELECT * FROM tb_funcionarios
                  WHERE UPPER(nome) = UPPER(?)
                  """;

        Optional<Funcionario> result = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario funcionarioEncontrado = new Funcionario();
                    funcionarioEncontrado.setId(rs.getInt("id"));
                    funcionarioEncontrado.setNome(rs.getString("nome"));
                    funcionarioEncontrado.setRg(rs.getString("rg"));
                    funcionarioEncontrado.setCpf(rs.getString("cpf"));
                    funcionarioEncontrado.setEmail(rs.getString("email"));
                    funcionarioEncontrado.setTelefone(rs.getString("telefone"));
                    funcionarioEncontrado.setCelular(rs.getString("celular"));
                    funcionarioEncontrado.setCep(rs.getString("cep"));
                    funcionarioEncontrado.setNumero(rs.getInt("numero"));
                    funcionarioEncontrado.setComplemento(rs.getString("complemento"));
                    funcionarioEncontrado.setBairro(rs.getString("bairro"));
                    funcionarioEncontrado.setCidade(rs.getString("cidade"));
                    funcionarioEncontrado.setEstado(rs.getString("estado"));
                    funcionarioEncontrado.setEndereco(rs.getString("endereco"));
                    funcionarioEncontrado.setSenha(rs.getString("senha"));
                    funcionarioEncontrado.setCargo(rs.getString("cargo"));
                    funcionarioEncontrado.setNivelAcesso(rs.getString("nivel_acesso"));
                    result = Optional.of(funcionarioEncontrado);
                }
                return result;
            }
        } catch (SQLException e) {
            throw new ClientNotFoundException("Erro ao buscar funcionario: " + e.getMessage() + "Erro no Banco de Dados");
        }
    }

    public void salvar(Funcionario funcionario) {
        final var sql = """
                   INSERT INTO tb_funcionarios(
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
                   endereco,
                   senha,
                   cargo,
                   nivel_acesso)
                   VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                   """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getCelular());
            stmt.setString(7, funcionario.getCep());
            stmt.setInt(8, funcionario.getNumero());
            stmt.setString(9, funcionario.getComplemento());
            stmt.setString(10, funcionario.getBairro());
            stmt.setString(11, funcionario.getCidade());
            stmt.setString(12, funcionario.getEstado());
            stmt.setString(13, funcionario.getEndereco());
            stmt.setString(14, funcionario.getSenha());
            stmt.setString(15, funcionario.getCargo());
            stmt.setString(16, funcionario.getNivelAcesso());

            // 2. Executar a consulta
            stmt.execute(); // ou stmt.executeUpdate() se você quiser saber quantas linhas foram afetadas
            JOptionPane.showMessageDialog(null, "Funcionário " + funcionario.getNome() + " salvo com sucesso!");
            //closeConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar funcionário: " + e.getMessage());
        }
    }
    
    //todo fix update employee
    public void atualizar(Funcionario funcionario) {
        final var sql = """
                   INSERT INTO tb_funcionarios(
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
                   endereco,
                   senha,
                   cargo,
                   nivel_acesso)
                   VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                   """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getCelular());
            stmt.setString(7, funcionario.getCep());
            stmt.setInt(8, funcionario.getNumero());
            stmt.setString(9, funcionario.getComplemento());
            stmt.setString(10, funcionario.getBairro());
            stmt.setString(11, funcionario.getCidade());
            stmt.setString(12, funcionario.getEstado());
            stmt.setString(13, funcionario.getEndereco());
            stmt.setString(14, funcionario.getSenha());
            stmt.setString(15, funcionario.getCargo());
            stmt.setString(16, funcionario.getNivelAcesso());

            // 2. Executar a consulta
            stmt.execute(); // ou stmt.executeUpdate() se você quiser saber quantas linhas foram afetadas
            JOptionPane.showMessageDialog(null, "Funcionário " + funcionario.getNome() + " salvo com sucesso!");
            //closeConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar funcionário: " + e.getMessage());
        }
    }
    
    public void excluir(int id) {
        final var sql = "DELETE FROM tb_funcionarios WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover guncionário: " + e.getMessage());
        }
    }
}
