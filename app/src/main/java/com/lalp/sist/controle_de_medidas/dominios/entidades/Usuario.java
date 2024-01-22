package com.lalp.sist.controle_de_medidas.dominios.entidades;

import java.io.Serializable;

public class Usuario implements Serializable {

    public int IDUSER;
    public String NOMEUSER;
    public String TIPODETREINO;
    public String DATAINICIO;
    public String DATAFIM;

    public int getIDUSER() {
        return IDUSER;
    }

    public void setIDUSER(int IDUSER) {
        this.IDUSER = IDUSER;
    }

    public String getNOMEUSER() {
        return NOMEUSER;
    }

    public void setNOMEUSER(String NOMEUSER) {
        this.NOMEUSER = NOMEUSER;
    }

    public String getTIPODETREINO() {
        return TIPODETREINO;
    }

    public void setTIPODETREINO(String TIPODETREINO) {
        this.TIPODETREINO = TIPODETREINO;
    }

    public String getDATAINICIO() {
        return DATAINICIO;
    }

    public void setDATAINICIO(String DATAINICIO) {
        this.DATAINICIO = DATAINICIO;
    }

    public String getDATAFIM() {
        return DATAFIM;
    }

    public void setDATAFIM(String DATAFIM) {
        this.DATAFIM = DATAFIM;
    }
}
