package Modelos.Listagem;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "hospedes")
@SessionScoped
public class Hospedes implements Serializable {

    private int idHospede;
    private String nomeHospede;
    private String nascimentoHospede;
    private String enderecoHospede;
    private String emailHospede;
    private int telefoneHospede;

    public int getIdHospede() {
        return idHospede;
    }

    public void setIdHospede(int idHospede) {
        this.idHospede = idHospede;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public String getNascimentoHospede() {
        return nascimentoHospede;
    }

    public void setNascimentoHospede(String nascimentoHospede) {
        this.nascimentoHospede = nascimentoHospede;
    }

    public String getEnderecoHospede() {
        return enderecoHospede;
    }

    public void setEnderecoHospede(String enderecoHospede) {
        this.enderecoHospede = enderecoHospede;
    }

    public String getEmailHospede() {
        return emailHospede;
    }

    public void setEmailHospede(String emailHospede) {
        this.emailHospede = emailHospede;
    }

    public int getTelefoneHospede() {
        return telefoneHospede;
    }

    public void setTelefoneHospede(int telefoneHospede) {
        this.telefoneHospede = telefoneHospede;
    }

    public Hospedes(int idHospede, String nomeHospede, String nascimentoHospede, String enderecoHospede, String emailHospede, int telefoneHospede) {
        this.idHospede = idHospede;
        this.nomeHospede = nomeHospede;
        this.nascimentoHospede = nascimentoHospede;
        this.enderecoHospede = enderecoHospede;
        this.emailHospede = emailHospede;
        this.telefoneHospede = telefoneHospede;
    }

}
