package DAO;

import Conexao.Conexao;
import Modelos.Ocupacao;
import Modelos.Quarto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Named(value = "ocupacaoDAO")
@SessionScoped
public class OcupacaoDAO implements Serializable {

    private Connection con;
    private Conexao conexao;
    private String mensagemSucesso;
    private String mensagemError;

    public OcupacaoDAO() {
        this.conexao = new Conexao();
        this.con = this.conexao.getConexao();
    }

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

    public void InserirOcupacao(Ocupacao ocupacao) {
        try {
            String sql = "INSERT INTO ocupacao (idQuarto,idHospede,dataCheckIN,dataCheckOUT) VALUES (?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ocupacao.getIdQuarto());
            stmt.setInt(2, ocupacao.getIdHospede());
            stmt.setString(3, ocupacao.getDataCheckIN());
            stmt.setString(4, ocupacao.getDataCheckOUT());
            stmt.execute();

            if (!"Seleccione".equals(ocupacao.getIdQuarto())
                    && !"Seleccione".equals(ocupacao.getIdHospede())
                    && !"".equals(ocupacao.getDataCheckIN())
                    && !"".equals(ocupacao.getDataCheckOUT())) {
                ocupacao.setIdQuarto(0);
                ocupacao.setIdHospede(0);
                ocupacao.setDataCheckIN("");
                ocupacao.setDataCheckOUT("");

                this.setMensagemError("");
                this.setMensagemSucesso("Ocupação feita com sucesso");

            } else {
                this.setMensagemError("Existe campo vazio");
                this.setMensagemSucesso("");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void PesquisarOcupacao(Ocupacao ocupacao) {
        try {
            String sql = "SELECT * FROM ocupacao WHERE idOcupacao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ocupacao.getIdOcupacao());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ocupacao.setIdOcupacao(rs.getInt("idOcupacao"));
                ocupacao.setIdQuarto(rs.getInt("idQuarto"));
                ocupacao.setIdHospede(rs.getInt("idHospede"));
                ocupacao.setDataCheckIN(rs.getString("dataCheckIN"));
                ocupacao.setDataCheckOUT(rs.getString("dataCheckOUT"));
            }
            if (!"Seleccione".equals(ocupacao.getIdQuarto())
                    && !"Seleccione".equals(ocupacao.getIdHospede())
                    && !"".equals(ocupacao.getDataCheckIN())
                    && !"".equals(ocupacao.getDataCheckOUT())) {
                this.setMensagemError("");
                this.setMensagemSucesso("Ocupação de quarto encontrado com sucesso");

            } else {
                this.setMensagemError("Ocupação não encontrada");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void ActualizarOcupacao(Ocupacao ocupacao) {
        try {
            String sql = "UPDATE ocupacao SET idQuarto = ?, idHospede = ?, dataCheckIN = ?, dataCheckOUT = ? WHERE idOcupacao = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ocupacao.getIdQuarto());
            stmt.setInt(2, ocupacao.getIdHospede());
            stmt.setString(3, ocupacao.getDataCheckIN());
            stmt.setString(4, ocupacao.getDataCheckOUT());
            stmt.setInt(5, ocupacao.getIdOcupacao());
            stmt.execute();

            if (ocupacao.getIdOcupacao() > 0
                    && !"Seleccione".equals(ocupacao.getIdQuarto())
                    && !"Seleccione".equals(ocupacao.getIdHospede())
                    && !"".equals(ocupacao.getDataCheckIN())
                    && !"".equals(ocupacao.getDataCheckOUT())) {

                ocupacao.setIdQuarto(0);
                ocupacao.setIdHospede(0);
                ocupacao.setDataCheckIN("");
                ocupacao.setDataCheckOUT("");
                ocupacao.setIdOcupacao(0);

                this.setMensagemError("");
                this.setMensagemSucesso("Ocupação de quarto Actualizado com sucesso");

            } else {
                this.setMensagemError("Existe campo vazio");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao actualizar: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void ExcluirOcupacao(Ocupacao ocupacao) {
        try {
            String sql = "DELETE FROM ocupacao WHERE idOcupacao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ocupacao.getIdOcupacao());
            stmt.execute();

            if (ocupacao.getIdOcupacao() > 0) {

                ocupacao.setIdQuarto(0);
                ocupacao.setIdHospede(0);
                ocupacao.setDataCheckIN("");
                ocupacao.setDataCheckOUT("");
                ocupacao.setIdOcupacao(0);

                this.setMensagemError("");
                this.setMensagemSucesso("Ocupação Excluida com sucesso");

            } else {
                this.setMensagemError("Especifique o id da ocupação");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

}
