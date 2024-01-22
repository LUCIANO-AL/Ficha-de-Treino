package com.lalp.sist.controle_de_medidas.dominios.entidades;

import java.io.Serializable;
import java.util.List;

public class Musculo implements Serializable {

    public int ID;
    public String MUSCULO;
    public int QUANTMUSCULO;
    public int POSICAO;
    public String DIADASEMANA;
    public int IDUSER;

    public Musculo() {
        ID = 0;
    }

    public int getQUANTMUSCULO() {
        return QUANTMUSCULO;
    }

    public void setQUANTMUSCULO(int QUANTMUSCULO) {
        this.QUANTMUSCULO = QUANTMUSCULO;
    }

    public int getPOSICAO() {
        return POSICAO;
    }

    public void setPOSICAO(int POSICAO) {
        this.POSICAO = POSICAO;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMUSCULO() {
        return MUSCULO;
    }

    public void setMUSCULO(String MUSCULO) {
        this.MUSCULO = MUSCULO;
    }

    public String getDIADASEMANA() {
        return DIADASEMANA;
    }

    public void setDIADASEMANA(String DIADASEMANA) {
        this.DIADASEMANA = DIADASEMANA;
    }

    public int getIDUSER() {
        return IDUSER;
    }

    public void setIDUSER(int IDUSER) {
        this.IDUSER = IDUSER;
    }
}
