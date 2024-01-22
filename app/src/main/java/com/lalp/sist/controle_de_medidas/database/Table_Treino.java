package com.lalp.sist.controle_de_medidas.database;

public class Table_Treino {

    public static String getCreate_Table_Treino() {

        StringBuilder sqlTreino = new StringBuilder();

        sqlTreino.append("CREATE TABLE TREINO (" +
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "MUSCULO     VARCHAR (50)," +
                "EXERCICIO   VARCHAR (100)," +
                "SEQUENCIA  INTEGER(4)," +
                "SERIES      INTEGER (10)," +
                "REPETICOES  INTEGER (10)," +
                "CARGA       INTEGER (20)," +
                "CHECK1       INTEGER NOT NULL," +
                "REPETICOES2  INTEGER (10)," +
                "CARGA2       INTEGER (20)," +
                "CHECK2       INTEGER NOT NULL," +
                "REPETICOES3  INTEGER (10)," +
                "CARGA3       INTEGER (20)," +
                "CHECK3       INTEGER NOT NULL," +
                "REPETICOES4  INTEGER (10)," +
                "CARGA4       INTEGER (20)," +
                "CHECK4       INTEGER NOT NULL," +
                "REPETICOES5  INTEGER (10)," +
                "CARGA5       INTEGER (20)," +
                "CHECK5       INTEGER NOT NULL," +
                "REPETICOES6  INTEGER (10)," +
                "CARGA6       INTEGER (20)," +
                "CHECK6       INTEGER NOT NULL," +
                "REPETICOES7  INTEGER (10)," +
                "CARGA7       INTEGER (20)," +
                "CHECK7       INTEGER NOT NULL," +
                "REPETICOES8  INTEGER (10)," +
                "CARGA8       INTEGER (20)," +
                "CHECK8       INTEGER NOT NULL," +
                "REPETICOES9  INTEGER (10)," +
                "CARGA9       INTEGER (20)," +
                "CHECK9       INTEGER NOT NULL," +
                "REPETICOES10  INTEGER (10)," +
                "CARGA10       INTEGER (20)," +
                "CHECK10       INTEGER NOT NULL," +
                "DIADASEMANA VARCHAR (30)," +
                "IDMUSC  INTEGER REFERENCES usuario (IDUSER) ON DELETE CASCADE MATCH [FULL]," +
                "IDUSER  INTEGER REFERENCES usuario (IDUSER) ON DELETE CASCADE MATCH [FULL]" +
                ")");


        return sqlTreino.toString();

    }

}
