package Modelos;

import DAO.QuartoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

@Named(value = "quarto")
@SessionScoped
public class Quarto implements Serializable {

    private int idQuarto;
    private String tipoQuarto;
    private int totalHospedes;
    private String disponibilidade;
    private String recursos;
    private final ArrayList<String> listaTiposQuartos;
    private final ArrayList<String> listaDisponibilidade;
    private final ArrayList<String> listaRecursos;

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

    public Quarto() {
        listaTiposQuartos = new ArrayList<>();
        listaTiposQuartos.add("Seleccione");
        listaTiposQuartos.add("Individual");
        listaTiposQuartos.add("Colectivo");
        listaTiposQuartos.add("Suite");

        listaDisponibilidade = new ArrayList<>();
        listaDisponibilidade.add("Seleccione");
        listaDisponibilidade.add("Ocupado");
        listaDisponibilidade.add("Disponível");
        listaDisponibilidade.add("Em Manutenção");

        listaRecursos = new ArrayList<>();
        listaRecursos.add("Seleccione");
        listaRecursos.add("Tv");
        listaRecursos.add("Ac");
        listaRecursos.add("Banheiro");
        listaRecursos.add("Tv, Ac, Banheiro");
        listaRecursos.add("Sem nenhum recurso");
    }

    public ArrayList<String> getListarTiposQuatos() {
        return listaTiposQuartos;
    }

    public ArrayList<String> getListarDisponibilidade() {
        return listaDisponibilidade;
    }

    public ArrayList<String> getListarRecursos() {
        return listaRecursos;
    }

}
