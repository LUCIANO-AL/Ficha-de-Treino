package com.lalp.sist.controle_de_medidas.database;

public class Table_Usuario {

    public static String getCreateUsuario()
    {
        StringBuilder sqlUsuario = new StringBuilder();

        sqlUsuario.append("CREATE TABLE usuario(");
        sqlUsuario.append("IDUSER INTEGER NOT NULL   PRIMARY KEY AUTOINCREMENT,");
        sqlUsuario.append("NOMEUSER VARCHAR (50) NOT NULL,");
        sqlUsuario.append("TIPODETREINO VARCHAR (50) NOT NULL,");
        sqlUsuario.append("DATAINICIO TEXT,");
        sqlUsuario.append("DATAFIM TEXT");
        sqlUsuario.append(" );");

        return sqlUsuario.toString();

    }
}
