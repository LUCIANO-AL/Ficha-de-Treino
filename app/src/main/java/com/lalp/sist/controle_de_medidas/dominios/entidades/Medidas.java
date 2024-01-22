package com.lalp.sist.controle_de_medidas.dominios.entidades;

import java.io.Serializable;

public class Medidas implements Serializable {

    public int ID;
    public String NomeUser;
    public String Datarg;
    public String Peso;
    public String Altura;
    public String Peito;
    public String Ombros;
    public String Bicepsdir;
    public String Bicepsesq;
    public String BicepsdirContr;
    public String BicepsesqContr;
    public String Antbracdir;
    public String Antbracesq;
    public String Cintura;
    public String Bumbum;
    public String Coxadir;
    public String Coxaesq;
    public String Panturdir;
    public String Panturesq;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNomeUser() {
        return NomeUser;
    }

    public void setNomeUser(String nomeUser) {
        NomeUser = nomeUser;
    }

    public String getDatarg() {
        return Datarg;
    }

    public void setDatarg(String datarg) {
        Datarg = datarg;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getAltura() {
        return Altura;
    }

    public void setAltura(String altura) {
        Altura = altura;
    }

    public String getPeito() {
        return Peito;
    }

    public void setPeito(String peito) {
        Peito = peito;
    }

    public String getOmbros() {
        return Ombros;
    }

    public void setOmbros(String ombros) {
        Ombros = ombros;
    }

    public String getBicepsdir() {
        return Bicepsdir;
    }

    public void setBicepsdir(String bicepsdir) {
        Bicepsdir = bicepsdir;
    }

    public String getBicepsesq() {
        return Bicepsesq;
    }

    public void setBicepsesq(String bicepsesq) {
        Bicepsesq = bicepsesq;
    }

    public String getBicepsdirContr() {
        return BicepsdirContr;
    }

    public void setBicepsdirContr(String bicepsdirContr) {
        BicepsdirContr = bicepsdirContr;
    }

    public String getBicepsesqContr() {
        return BicepsesqContr;
    }

    public void setBicepsesqContr(String bicepsesqContr) {
        BicepsesqContr = bicepsesqContr;
    }

    public String getAntbracdir() {
        return Antbracdir;
    }

    public void setAntbracdir(String antbracdir) {
        Antbracdir = antbracdir;
    }

    public String getAntbracesq() {
        return Antbracesq;
    }

    public void setAntbracesq(String antbracesq) {
        Antbracesq = antbracesq;
    }

    public String getCintura() {
        return Cintura;
    }

    public void setCintura(String cintura) {
        Cintura = cintura;
    }

    public String getBumbum() {
        return Bumbum;
    }

    public void setBumbum(String bumbum) {
        Bumbum = bumbum;
    }

    public String getCoxadir() {
        return Coxadir;
    }

    public void setCoxadir(String coxadir) {
        Coxadir = coxadir;
    }

    public String getCoxaesq() {
        return Coxaesq;
    }

    public void setCoxaesq(String coxaesq) {
        Coxaesq = coxaesq;
    }

    public String getPanturdir() {
        return Panturdir;
    }

    public void setPanturdir(String panturdir) {
        Panturdir = panturdir;
    }

    public String getPanturesq() {
        return Panturesq;
    }

    public void setPanturesq(String panturesq) {
        Panturesq = panturesq;
    }

    public Medidas(){ ID = 0; }
}

