package com.lalp.sist.controle_de_medidas.database;

public class Table_Medidas {

    public static String getCreate_Table_Medidas() {

        StringBuilder sqlMedidas = new StringBuilder();

        sqlMedidas.append("CREATE TABLE MEDIDAS (" +
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "NOMEUSER VARCHAR (200) NOT NULL," +
                "DATARG TEXT," +
                "PESO INTEGER (50)      ," +
                "ALTURA INTEGER (50)    ," +
                "PEITO INTEGER (50)     ," +
                "OMBROS INTEGER (50)     ," +
                "BICEPSDIR INTEGER (50) ," +
                "BICEPSESQ INTEGER (50) ," +
                "BICEPSDIRCONTR INTEGER (50) ," +
                "BICEPSESQCONTR INTEGER (50) ," +
                "ANTBRACDIR INTEGER (50)," +
                "ANTBRACESQ INTEGER (50)," +
                "CINTURA INTEGER (50)   ," +
                "BUMBUM INTEGER (50)    ," +
                "COXADIR INTEGER (50)   ," +
                "COXAESQ INTEGER (50)   ," +
                "PANTURDIR INTEGER (50) ," +
                "PANTURESQ INTEGER (50), " +
                "IDUSER  INTEGER REFERENCES usuario (IDUSER) ON DELETE CASCADE MATCH [FULL]" +
                ")");

        return sqlMedidas.toString();

    }
}

