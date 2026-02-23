package br.dev.hygino.jdbc;

import br.dev.hygino.dao.ClienteDao;
import br.dev.hygino.models.Cliente;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TestarConexao {

    public static void main(String[] args) throws SQLException {
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
}
