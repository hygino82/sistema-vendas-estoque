package br.dev.hygino.jdbc;

import br.dev.hygino.dao.ClienteDao;
import br.dev.hygino.dao.ProdutoDao;
import br.dev.hygino.models.Cliente;
import br.dev.hygino.models.Produto;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TestarConexao {
    
    public static void main(String[] args) throws SQLException {
        
       // buscarFornecedores();
        //excluirProduto();
        //buscarProdutos();
        //adicionarProduto();
        //adicionarCliente();
    }
    
    private static void adicionarProduto() {
        
        try {
            new ConexaoBanco().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso ao banco de dados!");
            
            Produto p1 = new Produto(null, "Salame Colonial", 34.99, 7, 3);
            
            final ProdutoDao dao = new ProdutoDao();
            
            dao.salvar(p1);
            
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com o banco de dados!\n" + e.getMessage());
        }
    }
    
    private static void adicionarCliente() {
        try {
            new ConexaoBanco().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso ao banco de dados!");
            
            Cliente c1 = new Cliente(
                    null,
                    "Juvenal Silva",
                    "78785455-8",
                    "222.222.222-22",
                    "juvenal@email.com",
                    "4632321010",
                    "4699550022",
                    "85550-000",
                    "Rua das Antas",
                    23,
                    "casa",
                    "Primavera",
                    "Coronel Vivida",
                    "PR");
            
            final ClienteDao dao = new ClienteDao();
            
            dao.salvar(c1);
            
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com o banco de dados!\n" + e.getMessage());
        }
    }
    
    private static void buscarProdutos() {
        try {
            new ConexaoBanco().getConnection();
            
            final ProdutoDao dao = new ProdutoDao();
            final var res = dao.listar("");
            
            res.forEach(System.out::println);
            
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com o banco de dados!\n" + e.getMessage());
        }
    }
    
    private static void excluirProduto() {
        final ProdutoDao dao = new ProdutoDao();
        dao.excluir(11);
    }
    
   /* private static void buscarFornecedores() {
        final var dao = new FornecedorDao();
        
        final Map<Integer, String> result = dao.listarFornecedoresCadastrados();
        
        result.forEach((cod, nome) -> System.out.println(cod + " : " + nome));
    }  */
}
