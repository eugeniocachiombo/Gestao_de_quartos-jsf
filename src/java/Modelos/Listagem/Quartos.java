package Modelos.Listagem;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "quartos")
@SessionScoped
public class Quartos implements Serializable {

    private int idQuarto;
    private String tipoQuarto;
    private int totalHospedes;
    private String disponibilidade;
    private String recursos;

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getTotalHospedes() {
        return totalHospedes;
    }

    public void setTotalHospedes(int totalHospedes) {
        this.totalHospedes = totalHospedes;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public Quartos(int idQuarto, String tipoQuarto,
            int totalHospedes, String disponibilidade, String recursos
    ) {
        this.idQuarto = idQuarto;
        this.tipoQuarto = tipoQuarto;
        this.totalHospedes = totalHospedes;
        this.disponibilidade = disponibilidade;
        this.recursos = recursos;
    }

}
