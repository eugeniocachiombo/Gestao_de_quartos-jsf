package DAO;

import Conexao.Conexao;
import Modelos.Quarto;
import Modelos.Listagem.Quartos;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Named(value = "quartoDAO")
@SessionScoped
public class QuartoDAO implements Serializable {

    private Connection con;
    private Conexao conexao;
    private String mensagemSucesso;
    private String mensagemError;
    private ArrayList<Quartos> listaQuartos;

    public String getMensagemSucesso() {
        return mensagemSucesso;
    }

    public void setMensagemSucesso(String mensagemSucesso) {
        this.mensagemSucesso = mensagemSucesso;
    }

    public String getMensagemError() {
        return mensagemError;
    }

    public void setMensagemError(String mensagemError) {
        this.mensagemError = mensagemError;
    }

    public QuartoDAO() {
        this.conexao = new Conexao();
        this.con = this.conexao.getConexao();
        BuscarQuartos();
    }

    public void InserirQuarto(Quarto quarto) {
        try {
            String sql = "INSERT INTO quarto (tipoQuarto, totalHospedes, disponibilidadeQuarto, recursosQuarto) VALUES(?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, quarto.getTipoQuarto());
            stmt.setInt(2, quarto.getTotalHospedes());
            stmt.setString(3, quarto.getDisponibilidade());
            stmt.setString(4, quarto.getRecursos());
            stmt.execute();

            if (!"Seleccione".equals(quarto.getTipoQuarto())
                    && quarto.getTotalHospedes() > 0
                    && !"Seleccione".equals(quarto.getDisponibilidade())
                    && !"Seleccione".equals(quarto.getRecursos())) {
                quarto.setTipoQuarto("");
                quarto.setTotalHospedes(0);
                quarto.setDisponibilidade("");
                quarto.setRecursos("");

                this.setMensagemError("");
                this.setMensagemSucesso("Quarto cadastrado com sucesso");
                System.out.println("Quarto cadastrado com sucesso");

            } else {
                this.setMensagemError("Existe campo vazio");
                this.setMensagemSucesso("");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void PesquisarQuarto(Quarto quarto) {
        try {
            String sql = "SELECT * FROM quarto WHERE idQuarto = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, quarto.getIdQuarto());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                quarto.setIdQuarto(rs.getInt("idQuarto"));
                quarto.setTipoQuarto(rs.getString("tipoQuarto"));
                quarto.setTotalHospedes(rs.getInt("totalHospedes"));
                quarto.setDisponibilidade(rs.getString("disponibilidadeQuarto"));
                quarto.setRecursos(rs.getString("recursosQuarto"));
            }
            if (!"Seleccione".equals(quarto.getTipoQuarto())
                    && quarto.getTotalHospedes() > 0
                    && !"Seleccione".equals(quarto.getDisponibilidade())
                    && !"Seleccione".equals(quarto.getRecursos())) {
                this.setMensagemError("");
                this.setMensagemSucesso("Dados do quarto encontrado com sucesso");
                System.out.println("Dados do quarto encontrado com sucesso");
            } else {
                this.setMensagemError("Quarto não encontrado");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void ActualizarQuarto(Quarto quarto) {
        try {
            String sql = "UPDATE quarto SET tipoQuarto = ?, totalHospedes = ?, disponibilidadeQuarto = ?, recursosQuarto = ? WHERE idQuarto = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, quarto.getTipoQuarto());
            stmt.setInt(2, quarto.getTotalHospedes());
            stmt.setString(3, quarto.getDisponibilidade());
            stmt.setString(4, quarto.getRecursos());
            stmt.setInt(5, quarto.getIdQuarto());
            stmt.execute();

            if (quarto.getIdQuarto() > 0
                    && !"Seleccione".equals(quarto.getTipoQuarto())
                    && quarto.getTotalHospedes() > 0
                    && !"Seleccione".equals(quarto.getDisponibilidade())
                    && !"Seleccione".equals(quarto.getRecursos())) {

                quarto.setTipoQuarto("");
                quarto.setTotalHospedes(0);
                quarto.setDisponibilidade("");
                quarto.setRecursos("");
                quarto.setIdQuarto(0);

                this.setMensagemError("");
                this.setMensagemSucesso("Dados do quarto Actualizado com sucesso");
                System.out.println("Dados do quarto Actualizado com sucesso");

            } else {
                this.setMensagemError("Existe campo vazio");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao actualizar: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void ExcluirQuarto(Quarto quarto) {
        try {
            String sql = "DELETE FROM quarto WHERE idQuarto = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, quarto.getIdQuarto());
            stmt.execute();

            if (quarto.getIdQuarto() > 0) {

                quarto.setTipoQuarto("");
                quarto.setTotalHospedes(0);
                quarto.setDisponibilidade("");
                quarto.setRecursos("");
                quarto.setIdQuarto(0);
                this.setMensagemError("");
                this.setMensagemSucesso("Quarto Excluido com sucesso");
                System.out.println("Quarto Excluido com sucesso");

            } else {
                this.setMensagemError("Especifique o id do quarto");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void BuscarQuartos() {
        try {

            String sql = "SELECT * FROM quarto";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            listaQuartos = new ArrayList<>();

            while (rs.next()) {
                listaQuartos.add(new Quartos(
                        rs.getInt("idQuarto"),
                        rs.getString("tipoQuarto"),
                        rs.getInt("totalHospedes"),
                        rs.getString("disponibilidadeQuarto"),
                        rs.getString("recursosQuarto")
                ));

                // System.out.println(quarto.getTipoQuarto());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public ArrayList<Quartos> getListarQuartos() {
        return listaQuartos;
    }
}
