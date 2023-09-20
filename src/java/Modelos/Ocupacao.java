package Modelos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "ocupacao")
@SessionScoped
public class Ocupacao implements Serializable {

    private int idOcupacao;
    private int idQuarto;
    private int idHospede;
    private String dataCheckIN;
    private String dataCheckOUT;

    public Ocupacao() {
    }

    public int getIdOcupacao() {
        return idOcupacao;
    }

    public void setIdOcupacao(int idOcupacao) {
        this.idOcupacao = idOcupacao;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public int getIdHospede() {
        return idHospede;
    }

    public void setIdHospede(int idHospede) {
        this.idHospede = idHospede;
    }

    public String getDataCheckIN() {
        return dataCheckIN;
    }

    public void setDataCheckIN(String dataCheckIN) {
        this.dataCheckIN = dataCheckIN;
    }

    public String getDataCheckOUT() {
        return dataCheckOUT;
    }

    public void setDataCheckOUT(String dataCheckOUT) {
        this.dataCheckOUT = dataCheckOUT;
    }

}
