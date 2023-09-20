package DAO;

import Conexao.Conexao;
import Modelos.Hospede;
import Modelos.Listagem.Hospedes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Named(value = "hospedeDAO")
@SessionScoped
public class HospedeDAO implements Serializable {

    private Connection con;
    private Conexao conexao;
    private String mensagemSucesso;
    private String mensagemError;
    private ArrayList<Hospedes> listaHospedes;

    public HospedeDAO() {
        this.conexao = new Conexao();
        this.con = this.conexao.getConexao();
        BuscarHospedes();
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

    public void InserirHospede(Hospede hospede) {
        try {
            String sql = "INSERT INTO hospede (nomeHospede,nascimentoHospede,enderecoHospede,emailHospede,telefoneHospede) VALUES (?,?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hospede.getNomeHospede());
            stmt.setString(2, hospede.getNascimentoHospede());
            stmt.setString(3, hospede.getEnderecoHospede());
            stmt.setString(4, hospede.getEmailHospede());
            stmt.setInt(5, hospede.getTelefoneHospede());
            stmt.execute();

            if (!"".equals(hospede.getNomeHospede())
                    && hospede.getTelefoneHospede() > 0
                    && !"".equals(hospede.getNascimentoHospede())
                    && !"".equals(hospede.getEnderecoHospede())
                    && !"".equals(hospede.getEmailHospede())) {

                hospede.setNomeHospede("");
                hospede.setNascimentoHospede("");
                hospede.setEnderecoHospede("");
                hospede.setEmailHospede("");
                hospede.setTelefoneHospede(0);

                this.setMensagemError("");
                this.setMensagemSucesso("Hospede cadastrado com sucesso");
            } else {
                this.setMensagemError("Existe campo vazio");
                this.setMensagemSucesso("");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void PesquisarHospede(Hospede hospede) {
        try {
            String sql = "SELECT * FROM hospede WHERE idHospede = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, hospede.getIdHospede());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hospede.setIdHospede(rs.getInt("idHospede"));
                hospede.setNomeHospede(rs.getString("nomeHospede"));
                hospede.setNascimentoHospede(rs.getString("nascimentoHospede"));
                hospede.setEnderecoHospede(rs.getString("enderecoHospede"));
                hospede.setEmailHospede(rs.getString("emailHospede"));
                hospede.setTelefoneHospede(rs.getInt("telefoneHospede"));
            }
            if (!"".equals(hospede.getNomeHospede())
                    && hospede.getTelefoneHospede() > 0
                    && !"".equals(hospede.getNascimentoHospede())
                    && !"".equals(hospede.getEnderecoHospede())
                    && !"".equals(hospede.getEmailHospede())) {
                this.setMensagemError("");
                this.setMensagemSucesso("Dados do hospede encontrado com sucesso");
            } else {
                this.setMensagemError("Hospede não encontrado");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void ActualizarHospede(Hospede hospede) {
        try {
            String sql = "UPDATE hospede SET nomeHospede = ?, nascimentoHospede = ?, enderecoHospede = ?, emailHospede = ?, telefoneHospede = ? WHERE idHospede = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hospede.getNomeHospede());
            stmt.setString(2, hospede.getNascimentoHospede());
            stmt.setString(3, hospede.getEnderecoHospede());
            stmt.setString(4, hospede.getEmailHospede());
            stmt.setInt(5, hospede.getTelefoneHospede());
            stmt.setInt(6, hospede.getIdHospede());
            stmt.execute();

            if (hospede.getIdHospede() > 0
                    && !"".equals(hospede.getNomeHospede())
                    && hospede.getTelefoneHospede() > 0
                    && !"".equals(hospede.getNascimentoHospede())
                    && !"".equals(hospede.getEnderecoHospede())
                    && !"".equals(hospede.getEmailHospede())) {

                hospede.setNomeHospede("");
                hospede.setNascimentoHospede("");
                hospede.setEnderecoHospede("");
                hospede.setEmailHospede("");
                hospede.setTelefoneHospede(0);
                hospede.setIdHospede(0);

                this.setMensagemError("");
                this.setMensagemSucesso("Dados do hospede Actualizado com sucesso");

            } else {
                this.setMensagemError("Existe campo vazio");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao actualizar: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void ExcluirHospede(Hospede hospede) {
        try {
            String sql = "DELETE FROM hospede WHERE idHospede = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, hospede.getIdHospede());
            stmt.execute();

            if (hospede.getIdHospede() > 0) {

                hospede.setNomeHospede("");
                hospede.setNascimentoHospede("");
                hospede.setEnderecoHospede("");
                hospede.setEmailHospede("");
                hospede.setTelefoneHospede(0);
                hospede.setIdHospede(0);
                this.setMensagemError("");
                this.setMensagemSucesso("Hospede Excluido com sucesso");

            } else {
                this.setMensagemError("Especifique o id do hospede");
                this.setMensagemSucesso("");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public void BuscarHospedes() {
        try {

            String sql = "SELECT * FROM hospede";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            listaHospedes = new ArrayList<>();

            while (rs.next()) {
                listaHospedes.add(
                        new Hospedes(
                                rs.getInt("idHospede"),
                                rs.getString("nomeHospede"),
                                rs.getString("nascimentoHospede"),
                                rs.getString("enderecoHospede"),
                                rs.getString("emailHospede"),
                                rs.getInt("telefoneHospede")
                        )
                );

                // System.out.println(quarto.getTipoQuarto());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            this.setMensagemError("Erro da base de dados");
        }
    }

    public ArrayList<Hospedes> getListarHospedes() {
        return listaHospedes;
    }
}
