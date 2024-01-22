package com.lalp.sist.controle_de_medidas.database;

public class Table_Musculo {

    public static String getCreate_Table_Musculos() {

        StringBuilder sqlMusculos = new StringBuilder();

        sqlMusculos.append("CREATE TABLE MUSCULOS (" +
                        "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "MUSCULO      VARCHAR (50)," +
                        "QUANTMUSCULO INTEGER (50)," +
                        "POSICAO INTEGER (50)," +
                        "DIADASEMANA  VARCHAR (30)," +
                        "IDUSER  INTEGER REFERENCES usuario (IDUSER) ON DELETE CASCADE MATCH [FULL]" +
                ")");

        return sqlMusculos.toString();

    }

}
