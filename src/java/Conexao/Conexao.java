package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "conexao")
@Dependent
public class Conexao {

    public Connection getConexao() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_de_quartos?serverTimezone=UTC", "root", "");
            System.out.println("Conectado com a base de dados!");
            return con;
        } catch (SQLException e) {
            System.out.println("Erro de conexao: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Conexao con = new Conexao();
            con.getConexao();
        } catch (Exception e) {
            System.out.println("Erro de conexao: " + e.getMessage());
        }
    }

}
