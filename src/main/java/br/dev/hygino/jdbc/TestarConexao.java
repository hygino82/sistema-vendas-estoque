package br.dev.hygino.jdbc;

import javax.swing.JOptionPane;

public class TestarConexao {

    public static void main(String[] args) {
        try {
            new ConexaoBanco().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso ao banco de dados!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com o banco de dados!\n" + e.getMessage());
        }
    }
}
