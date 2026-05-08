package br.dev.hygino.dao;

import br.dev.hygino.exceptions.ResourceNotFoundException;
import br.dev.hygino.jdbc.ConexaoBanco;
import br.dev.hygino.models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author hygino
 */
public final class ProdutoDao {

    private final Connection connection;

    public ProdutoDao() {
        this.connection = new ConexaoBanco().getConnection();
    }

    public void salvar(Produto produto) {
        final var sql = """
                      INSERT INTO tb_produtos(descricao, preco, qtd_estoque, for_id)
                      VALUES(?, ?, ?, ?)
                      """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setInt(4, produto.getFornecedorId());

            // 2. Executar a consulta
            stmt.execute(); // ou stmt.executeUpdate() se você quiser saber quantas linhas foram afetadas
            JOptionPane.showMessageDialog(null, "Produto " + produto.getDescricao() + " salvo com sucesso!");
            //closeConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + e.getMessage());
        }
    }

    public void atualizar(Produto produto) {
        final var sql = """
                   UPDATE tb_produtos SET
                   descricao=?,
                   preco=?,
                   qtd_etoque=?,
                   for_id=?,
                   WHERE id=?     
                   """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // 1. Atribuir os valores do objeto Cliente aos parâmetros da SQL
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setInt(4, produto.getFornecedorId());
            stmt.setInt(5, produto.getId());
            // 2. Executar a consulta
            stmt.execute(); // ou stmt.executeUpdate() se você quiser saber quantas linhas foram afetadas
            JOptionPane.showMessageDialog(null, "Produto " + produto.getDescricao() + " editado com sucesso!");
            //closeConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar cliente: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        final var sql = "DELETE FROM tb_produtos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover produto: " + e.getMessage());
        }
    }

    public List<Produto> listar(String descricao) {

        List<Produto> produtos = new ArrayList<>();

        var sql = """
        SELECT * FROM tb_produtos
        WHERE UPPER(descricao) LIKE CONCAT('%', UPPER(?), '%')
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // operador de coalescência nula apenas no Java
            stmt.setString(1, descricao == null ? "" : descricao);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getInt("qtd_estoque"),
                        rs.getInt("for_id")
                );

                produtos.add(produto);
            }

            return produtos;

        } catch (SQLException e) {
            throw new ResourceNotFoundException(
                    "Erro ao buscar produtos: " + e.getMessage()
            );
        }
    }

    public Optional<Produto> buscarProduto(String descricao) {
        final var sql = """
                  SELECT * FROM tb_produtos
                  WHERE UPPER(descricao) = UPPER(?)
                  """;

        Optional<Produto> result = Optional.empty();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, descricao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto(
                            rs.getInt("id"),
                            rs.getString("descricao"),
                            rs.getDouble("preco"),
                            rs.getInt("qtd_estoque"),
                            rs.getInt("for_id")
                    );
                    result = Optional.of(produto);
                }
                return result;
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Erro ao buscar produto: " + e.getMessage() + "Erro no Banco de Dados");
        }
    }
}
