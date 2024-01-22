package com.lalp.sist.controle_de_medidas.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.lalp.sist.controle_de_medidas.dominios.entidades.Exercicio;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Musculo;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private String result, resultListItens, ultimoiduser;
    private String nomemusculo, nomemusculo1, nomemusculo2, nomemusculo3;
    private String nomemusculo4, nomemusculo5, nomemusculo6, qtdMusculo;
    private String resultQuantidadeMusc;
    private String maiorposicao, copia;
    private String idmusculo, idmusculo2, idmusculo3, idmusculo4, idmusculo5, idmusculo6;
    private String posicao, posicao2, posicao3, posicao4, posicao5, posicao6;
    private Exercicio exercicio;

    private SQLiteDatabase db;


    public DataBase(Context context) {

        super(context, "FICHADETREINOS", null, 4);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_Medidas.getCreate_Table_Medidas());
        db.execSQL(Table_Treino.getCreate_Table_Treino());
        db.execSQL(Table_Musculo.getCreate_Table_Musculos());
        db.execSQL(Table_Usuario.getCreateUsuario());

        db.execSQL("insert into usuario values (NULL, 'TreinoExemplo TR1', 'Normal', '31/03/2023', '31/03/2030')");

        db.execSQL("insert into MUSCULOS values (NULL, 'Peito', '0', '0', 'Segunda', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Peito', 'Supino Inclinado com Barra', 1, 3, 10, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Peito', 'Crucifixo Inclinado com Halteres', 2, 3, 10, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Peito', 'Supino Reto com Barra', 3, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Peito', 'Supino Declinado com Barra', 4, 3, 10, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");

        db.execSQL("insert into MUSCULOS values (NULL, 'Tríceps', '0', '0', 'Segunda', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Triceps', 'Tríceps Testa', 1, 3, 10, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Triceps', 'Tríceps Corda', 2, 3, 10, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Triceps', 'Tríceps Francês', 3, 3, 10, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Abdômen', '0', '0', 'Segunda', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Abdômen', 'Abdominal Reto', 1, 3, 15, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Abdômen', 'Abdominal Infra', 2, 3, 15,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Quadríceps', '0', '0', 'Terça', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Quadríceps', 'Agachamento', 1, 3, 10, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Quadríceps', 'Agachamento Búlgaro', 2, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Quadríceps', 'Leg Press 45°', 3, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Quadríceps', 'Cadeira extensora', 4, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Panturrilha', '0', '0', 'Terça', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Panturrilha', 'Panturrilha em Pé', 1, 3, 20,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Panturrilha', 'Panturrilha Sentado', 2, 3, 20,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Costas', '0', '0', 'Quarta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Puxada alta frontal com pegada aberta', 1, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Puxada alta com triângulo ou corda', 2, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Pull Over na polia alta com corda', 3, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Remada curvada com pegada supinada', 4, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Remada unilateral (serrote)', 5, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Remada baixa pegada aberta', 6, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Crucifixo Inverso', 7, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Biceps', '0', '0', 'Quinta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Biceps', 'Rosca direta no cabo', 1, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Biceps', 'Rosca com halteres no banco inclinado', 2, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Biceps', 'Rosca Scott', 3, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Ombros', '0', '0', 'Quinta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Ombros', 'Elevação Lateral com Halteres', 1, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Ombros', 'Desenvolvimento de Ombros com Barra', 2, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Ombros', 'Elevação Frontal com Halteres', 3, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Abdômen', '0', '0', 'Quinta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Abdômen', 'Prancha Frontal', 1, 3,'0','0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Abdômen', 'Abdominal supra com corda polia alta', 2, 3, 15,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Posterior', '0', '0', 'Sexta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Levantamento Terra Sumô', 1, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Stiff', 2, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Levantamento Terra', 3, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Mesa Flexora', 4, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Cadeira Flexora', 5, 3, 10,'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");

        db.execSQL("insert into MUSCULOS values (NULL, 'Panturrilha', '0', '0', 'Sexta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Panturrilha', 'Panturrilha em Pé', 1, 3, 20, '0', '0', '0', '0', '0', '0', '0', '0', '0', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Panturrilha', 'Panturrilha Sentado', 2, 3, 20, '0', '0', '', '0', '0', '0', '0', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("ALTER TABLE TREINO ADD SEQUENCIA  INTEGER(4);");
        //db.execSQL("UPDATE TREINO SET SEQUENCIA = 1;");
        //db.execSQL("UPDATE MUSCULOS SET POSICAO = 2 WHERE MUSCULO = 'Tes1' AND DIADASEMANA = 'Domingo';");

        /*db.execSQL(Table_Usuario.getCreateUsuario());
        db.execSQL("insert into usuario values (NULL, 'Usuario1 Tr.1', 'Normal', '01/07/2023', '31/03/2030')");
        db.execSQL("ALTER TABLE MEDIDAS ADD IDUSER INTEGER REFERENCES usuario (IDUSER) ON DELETE CASCADE MATCH [FULL];");
        db.execSQL("ALTER TABLE MUSCULOS ADD IDUSER INTEGER REFERENCES usuario (IDUSER) ON DELETE CASCADE MATCH [FULL];");
        db.execSQL("ALTER TABLE TREINO ADD IDMUSC INTEGER REFERENCES MUSCULOS (ID) ON DELETE CASCADE MATCH [FULL];");
        db.execSQL("ALTER TABLE TREINO ADD IDUSER  INTEGER REFERENCES usuario (IDUSER) ON DELETE CASCADE MATCH [FULL];");
        db.execSQL("UPDATE MEDIDAS SET IDUSER = 1 ;");
        db.execSQL("UPDATE MUSCULOS SET IDUSER = 1 ;");
        db.execSQL("UPDATE TREINO SET IDUSER = 1 ;");
        db.execSQL("update TREINO set IDMUSC = ( select ID from MUSCULOS where MUSCULOS.MUSCULO = TREINO.MUSCULO )");*/

       /* db.execSQL("insert into usuario values (NULL, 'Treino Exemplo Tr.1', 'Normal', '31/03/2023', '31/03/2030')");

        db.execSQL("insert into MUSCULOS values (NULL, 'Peito', '0', '0', 'Segunda', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Peito', 'Supino Inclinado com Barra', 1, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Peito', 'Crucifixo Inclinado com Halteres', 2, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Peito', 'Supino Reto com Barra', 3, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Peito', 'Supino Declinado com Barra', 4, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");

        db.execSQL("insert into MUSCULOS values (NULL, 'Tríceps', '0', '0', 'Segunda', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Triceps', 'Tríceps Testa', 1, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Triceps', 'Tríceps Corda', 2, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Triceps', 'Tríceps Francês', 3, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Abdômen', '0', '0', 'Segunda', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Abdômen', 'Abdominal Reto', 1, 3, 15, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Abdômen', 'Abdominal Infra', 2, 3, 15, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Segunda', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Quadríceps', '0', '0', 'Terça', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Quadríceps', 'Agachamento', 1, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Quadríceps', 'Agachamento Búlgaro', 2, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Quadríceps', 'Leg Press 45°', 3, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Quadríceps', 'Cadeira extensora', 4, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Panturrilha', '0', '0', 'Terça', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Panturrilha', 'Panturrilha em Pé', 1, 3, 20, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Panturrilha', 'Panturrilha Sentado', 2, 3, 20, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Terça', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Costas', '0', '0', 'Quarta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Puxada alta frontal com pegada aberta', 1, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Puxada alta com triângulo ou corda', 2, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Pull Over na polia alta com corda', 3, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Remada curvada com pegada supinada', 4, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Remada unilateral (serrote)', 5, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Remada baixa pegada aberta', 6, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Costas', 'Crucifixo Inverso', 7, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quarta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Biceps', '0', '0', 'Quinta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Biceps', 'Rosca direta no cabo', 1, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Biceps', 'Rosca com halteres no banco inclinado', 2, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Biceps', 'Rosca Scott', 3, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Ombros', '0', '0', 'Quinta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Ombros', 'Elevação Lateral com Halteres', 1, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Ombros', 'Desenvolvimento de Ombros com Barra', 2, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Ombros', 'Elevação Frontal com Halteres', 3, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Abdômen', '0', '0', 'Quinta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Abdômen', 'Prancha Frontal', 1, 3, '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Abdômen', 'Abdominal supra com corda polia alta', 2, 3, 15, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Quinta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");


        db.execSQL("insert into MUSCULOS values (NULL, 'Posterior', '0', '0', 'Sexta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Levantamento Terra Sumô', 1, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Stiff', 2, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Levantamento Terra', 3, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Mesa Flexora', 4, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Posterior', 'Cadeira Flexora', 5, 3, 10, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");

        db.execSQL("insert into MUSCULOS values (NULL, 'Panturrilha', '0', '0', 'Sexta', (select IDUSER from usuario order by IDUSER desc limit 1))");
        db.execSQL("insert into TREINO values (NULL, 'Panturrilha', 'Panturrilha em Pé', 1, 3, 20, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");
        db.execSQL("insert into TREINO values (NULL, 'Panturrilha', 'Panturrilha Sentado', 2, 3, 20, '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', '', '', '0', 'Sexta', " +
                "(select ID from MUSCULOS order by ID desc limit 1),\n" +
                "(select IDUSER from usuario order by IDUSER desc limit 1));");

        */

    }

    public int insertUser(String table, ContentValues values) {
        try {
            db = this.getWritableDatabase();
            int y = (int) db.insert(table, null, values);
            db.close();
            // Log.e("Data Inserted", "Data Inserted");
            //Log.e("y", y + "");
            return y;
        } catch (Exception ex) {
            Log.e("Error Insert", ex.getMessage().toString());
            return 0;
        }
    }
    public int insertMusculos(String table, ContentValues values) {
        try {
            db = this.getWritableDatabase();
            int y = (int) db.insert(table, null, values);

            db.close();
            // Log.e("Data Inserted", "Data Inserted");
            //Log.e("y", y + "");
            return y;
        } catch (Exception ex) {
            Log.e("Error Insert", ex.getMessage().toString());
            return 0;
        }
    }

    public int insertExercicio(String table, ContentValues values) {
        try {
            db = this.getWritableDatabase();
            int y = (int) db.insert(table, null, values);

            db.close();
            // Log.e("Data Inserted", "Data Inserted");
            //Log.e("y", y + "");
            return y;
        } catch (Exception ex) {
            Log.e("Error Insert", ex.getMessage().toString());
            return 0;
        }
    }

    public String selectUltimoIDUser() {

        String selectQuery = "select IDUSER from usuario order by IDUSER desc limit 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {

            ultimoiduser = cursor.getString(0);
        }

        cursor.close();
        return ultimoiduser;

    }

    public String updateExercicio(String MUSCULO, String EXERCICIO, String SEQUENCIA, String SERIES, String REPETICOES, String CARGA, String REPETICOES2, String CARGA2,
                                  String REPETICOES3, String CARGA3, String REPETICOES4, String CARGA4, String REPETICOES5, String CARGA5, String REPETICOES6, String CARGA6
            , String REPETICOES7, String CARGA7, String REPETICOES8, String CARGA8, String REPETICOES9, String CARGA9, String REPETICOES10, String CARGA10, String IDMUSC, String ID) {

        String updateQuery = "UPDATE TREINO SET MUSCULO=?, EXERCICIO=?, SEQUENCIA=?, SERIES=?, REPETICOES=?, CARGA=?, REPETICOES2=?, CARGA2=?, REPETICOES3=?, CARGA3=?" +
                ", REPETICOES4=?, CARGA4=?, REPETICOES5=?, CARGA5=?, REPETICOES6=?, CARGA6=?, REPETICOES7=?, CARGA7=?, REPETICOES8=?, CARGA8=?, REPETICOES9=?, CARGA9=? " +
                ", REPETICOES10=?, CARGA10=?, IDMUSC=? WHERE ID=?";

        String[] parametros = {MUSCULO, EXERCICIO, SEQUENCIA, SERIES, REPETICOES, CARGA, REPETICOES2, CARGA2, REPETICOES3, CARGA3, REPETICOES4, CARGA4, REPETICOES5, CARGA5, REPETICOES6, CARGA6
                , REPETICOES7, CARGA7, REPETICOES8, CARGA8, REPETICOES9, CARGA9, REPETICOES10, CARGA10, IDMUSC, ID};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }

    public String updateExercicioDiaSemana(String DIADASEMANA, String IDMUSC, String IDUSER) {

        String updateQuery = "UPDATE TREINO SET DIADASEMANA = ? WHERE IDMUSC = ? AND IDUSER = ?";

        String[] parametros = {DIADASEMANA, IDMUSC, IDUSER};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }

    public String updateUser(String NOMEUSER, String TIPODETREINO, String DATAINICIO, String DATAFIM, String IDUSER) {

        String updateQuery = "UPDATE usuario SET NOMEUSER=?, TIPODETREINO=?, DATAINICIO=?, DATAFIM=? WHERE IDUSER=?";

        String[] parametros = {NOMEUSER, TIPODETREINO, DATAINICIO, DATAFIM, IDUSER};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }

    public List<Exercicio> buscarExercicio(String diadasemana, String IDMUSC, String IDUSER) {

        List<Exercicio> exercicios = new ArrayList<Exercicio>();

        StringBuilder sql = new StringBuilder();
        //sql.append("SELECT * FROM TREINO WHERE DIADASEMANA= ? AND IDMUSC= ? AND IDUSER = ?");
        sql.append("SELECT TREINO.ID, TREINO.MUSCULO, TREINO.EXERCICIO, TREINO.SEQUENCIA, TREINO.SERIES, TREINO.REPETICOES, TREINO.CARGA, TREINO.CHECK1, " +
                "TREINO.REPETICOES2,TREINO.CARGA2, TREINO.CHECK2, TREINO.REPETICOES3,TREINO.CARGA3, TREINO.CHECK3" +
                ", TREINO.REPETICOES4,TREINO.CARGA4, TREINO.CHECK4, TREINO.REPETICOES5,TREINO.CARGA5, TREINO.CHECK5, " +
                "TREINO.REPETICOES6,TREINO.CARGA6, TREINO.CHECK6, TREINO.REPETICOES7,TREINO.CARGA7, TREINO.CHECK7, TREINO.REPETICOES8,TREINO.CARGA8, TREINO.CHECK8" +
                ", TREINO.REPETICOES9,TREINO.CARGA9, TREINO.CHECK9, TREINO.REPETICOES10,TREINO.CARGA10, TREINO.CHECK10, " +
                "TREINO.DIADASEMANA, TREINO.IDMUSC, TREINO.IDUSER" +
                " From TREINO INNER JOIN MUSCULOS on MUSCULOS.ID = TREINO.IDMUSC Where MUSCULOS.DIADASEMANA = ? AND  TREINO.IDMUSC = ? AND TREINO.IDUSER = ? ORDER BY SEQUENCIA");

        String[] parametros = {diadasemana, IDMUSC, IDUSER};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor curso = db.rawQuery(sql.toString(), parametros);

        if (curso.getCount() > 0) {

            curso.moveToFirst();

            do {

                Exercicio rgcomp = new Exercicio();

                rgcomp.ID = curso.getInt(curso.getColumnIndexOrThrow("ID"));
                rgcomp.MUSCULO = curso.getString(curso.getColumnIndexOrThrow("MUSCULO"));
                rgcomp.EXERCICIO = curso.getString(curso.getColumnIndexOrThrow("EXERCICIO"));
                rgcomp.SEQUENCIA = curso.getString(curso.getColumnIndexOrThrow("SEQUENCIA"));
                rgcomp.SERIES = curso.getString(curso.getColumnIndexOrThrow("SERIES"));
                rgcomp.REPETICOES = curso.getString(curso.getColumnIndexOrThrow("REPETICOES"));
                rgcomp.CARGA = curso.getString(curso.getColumnIndexOrThrow("CARGA"));
                rgcomp.CHECK1 = curso.getInt(curso.getColumnIndexOrThrow("CHECK1")) >= 1;
                rgcomp.REPETICOES2 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES2"));
                rgcomp.CARGA2 = curso.getString(curso.getColumnIndexOrThrow("CARGA2"));
                rgcomp.CHECK2 = curso.getInt(curso.getColumnIndexOrThrow("CHECK2")) >= 1;
                rgcomp.REPETICOES3 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES3"));
                rgcomp.CARGA3 = curso.getString(curso.getColumnIndexOrThrow("CARGA3"));
                rgcomp.CHECK3 = curso.getInt(curso.getColumnIndexOrThrow("CHECK3")) >= 1;
                rgcomp.REPETICOES4 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES4"));
                rgcomp.CARGA4 = curso.getString(curso.getColumnIndexOrThrow("CARGA4"));
                rgcomp.CHECK4 = curso.getInt(curso.getColumnIndexOrThrow("CHECK4")) >= 1;
                rgcomp.REPETICOES5 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES5"));
                rgcomp.CARGA5 = curso.getString(curso.getColumnIndexOrThrow("CARGA5"));
                rgcomp.CHECK5 = curso.getInt(curso.getColumnIndexOrThrow("CHECK5")) >= 1;
                rgcomp.REPETICOES6 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES6"));
                rgcomp.CARGA6 = curso.getString(curso.getColumnIndexOrThrow("CARGA6"));
                rgcomp.CHECK6 = curso.getInt(curso.getColumnIndexOrThrow("CHECK6")) >= 1;
                rgcomp.REPETICOES7 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES7"));
                rgcomp.CARGA7 = curso.getString(curso.getColumnIndexOrThrow("CARGA7"));
                rgcomp.CHECK7 = curso.getInt(curso.getColumnIndexOrThrow("CHECK7")) >= 1;
                rgcomp.REPETICOES8 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES8"));
                rgcomp.CARGA8 = curso.getString(curso.getColumnIndexOrThrow("CARGA8"));
                rgcomp.CHECK8 = curso.getInt(curso.getColumnIndexOrThrow("CHECK8")) >= 1;
                rgcomp.REPETICOES9 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES9"));
                rgcomp.CARGA9 = curso.getString(curso.getColumnIndexOrThrow("CARGA9"));
                rgcomp.CHECK9 = curso.getInt(curso.getColumnIndexOrThrow("CHECK9")) >= 1;
                rgcomp.REPETICOES10 = curso.getString(curso.getColumnIndexOrThrow("REPETICOES10"));
                rgcomp.CARGA10 = curso.getString(curso.getColumnIndexOrThrow("CARGA10"));
                rgcomp.CHECK10 = curso.getInt(curso.getColumnIndexOrThrow("CHECK10")) >= 1;
                rgcomp.DIADASEMANA = curso.getString(curso.getColumnIndexOrThrow("DIADASEMANA"));
                rgcomp.IDMUSC = curso.getString(curso.getColumnIndexOrThrow("IDMUSC"));
                rgcomp.IDUSER = curso.getString(curso.getColumnIndexOrThrow("IDUSER"));

                exercicios.add(rgcomp);

            } while (curso.moveToNext());

        }

        curso.close();
        return exercicios;
    }

    public List<Musculo> buscar_Musculo(String diadasemana, String IDUSER) {

        List<Musculo> musculos = new ArrayList<Musculo>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM MUSCULOS WHERE DIADASEMANA = ? AND IDUSER = ? order by POSICAO");

        String[] parametros = {diadasemana, IDUSER};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor curso = db.rawQuery(sql.toString(), parametros);

        if (curso.getCount() > 0) {

            curso.moveToFirst();

            do {

                Musculo musc = new Musculo();

                musc.ID = curso.getInt(curso.getColumnIndexOrThrow("ID"));
                musc.MUSCULO = curso.getString(curso.getColumnIndexOrThrow("MUSCULO"));
                musc.POSICAO = curso.getInt(curso.getColumnIndexOrThrow("POSICAO"));
                musc.DIADASEMANA = curso.getString(curso.getColumnIndexOrThrow("DIADASEMANA"));
                musc.IDUSER = curso.getInt(curso.getColumnIndexOrThrow("IDUSER"));

                musculos.add(musc);

            } while (curso.moveToNext());

        }

        return musculos;
    }

    public List<Usuario> buscarUsuario() {

        List<Usuario> usuarios = new ArrayList<Usuario>();

        StringBuilder sql = new StringBuilder();
        //sql.append("SELECT * FROM TREINO WHERE DIADASEMANA = ? AND IDMUSC = ? AND IDUSER = ?");
        sql.append("SELECT * FROM usuario order by IDUSER");

        //String[] parametros = {diadasemana, IDMUSC, IDUSER};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor curso = db.rawQuery(sql.toString(), null);

        if (curso.getCount() > 0) {

            curso.moveToFirst();

            do {

                Usuario rg = new Usuario();

                rg.IDUSER = curso.getInt(curso.getColumnIndexOrThrow("IDUSER"));
                rg.NOMEUSER = curso.getString(curso.getColumnIndexOrThrow("NOMEUSER"));
                rg.TIPODETREINO = curso.getString(curso.getColumnIndexOrThrow("TIPODETREINO"));
                rg.DATAINICIO = curso.getString(curso.getColumnIndexOrThrow("DATAINICIO"));
                rg.DATAFIM = curso.getString(curso.getColumnIndexOrThrow("DATAFIM"));

                usuarios.add(rg);

            } while (curso.moveToNext());

        }

        return usuarios;
    }

    public String copiarMedidas(int ID) {

        String InsertQuery = "insert into MEDIDAS (NOMEUSER, DATARG, PESO, ALTURA, PEITO, OMBROS, BICEPSDIR, BICEPSESQ, " +
                "BICEPSDIRCONTR, BICEPSESQCONTR, ANTBRACESQ, CINTURA, BUMBUM, COXADIR, COXAESQ, PANTURDIR, PANTURESQ)" +
                " select NOMEUSER, DATARG, PESO, ALTURA, PEITO, OMBROS, BICEPSDIR, BICEPSESQ, BICEPSDIRCONTR, BICEPSESQCONTR," +
                " ANTBRACESQ, CINTURA, BUMBUM, COXADIR, COXAESQ, PANTURDIR, PANTURESQ " +
                "from  MEDIDAS where ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(InsertQuery, parametros);


        if (cursor.moveToNext()) {

            copia = cursor.getString(0);
        }

        cursor.close();
        return copia;

    }

    public void noCheck1(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK1 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check1(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK1 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck2(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK2 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check2(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK2 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck3(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK3 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check3(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK3 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck4(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK4 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check4(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK4 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck5(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK5 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check5(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK5 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck6(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK6 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check6(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK6 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck7(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK7 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check7(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK7 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck8(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK8 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check8(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK8 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck9(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK9 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check9(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK9 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public void noCheck10(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK10 = 0 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();

    }

    public void Check10(int ID) {

        String UpdateQuery = "UPDATE TREINO SET CHECK10 = 1 WHERE  ID = ?";

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(UpdateQuery, parametros);

        cursor.moveToFirst();

        cursor.close();
    }

    public String selectNumLinhasUser() {

        String selectQuery = "SELECT COUNT(*) FROM  usuario";

        // String[] parametros = {DIADASEMANA};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {

            maiorposicao = cursor.getString(0);

        }
        cursor.close();
        return maiorposicao;

    }

    public String selectNumLinhasMusc(String DIADASEMANA, String IDUSER) {

        String selectQuery = "SELECT COUNT(*) FROM  MUSCULOS WHERE DIADASEMANA = ? AND IDUSER = ?";

        String[] parametros = {DIADASEMANA, IDUSER};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, parametros);

        if (cursor.moveToNext()) {

            maiorposicao = cursor.getString(0);

        }
        cursor.close();
        return maiorposicao;

    }

    public String selectNumLinhasExerc(String DIADASEMANA, String IDUSER, String IDMUS) {

        String selectQuery = "SELECT COUNT(*) FROM  TREINO WHERE DIADASEMANA = ? AND IDUSER = ? AND IDMUSC = ?";

        String[] parametros = {DIADASEMANA, IDUSER, IDMUS};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, parametros);

        if (cursor.moveToNext()) {

            maiorposicao = cursor.getString(0);

        }
        cursor.close();
        return maiorposicao;

    }

    @SuppressLint("Range")
    public ArrayList<String> buscarNomeMusculoSippnerRecycle(String DIADASEMANA, String IDUSER) {

        SQLiteDatabase db = getReadableDatabase();

        String sql_busca_dados_spinner = "SELECT DISTINCT MUSCULO FROM  MUSCULOS where DIADASEMANA = ? AND IDUSER = ? ORDER BY POSICAO";

        ArrayList<String> dados_spinner = new ArrayList<>();

        String[] parametros = {DIADASEMANA, IDUSER};

        Cursor cursor2 = db.rawQuery(sql_busca_dados_spinner, parametros);

        while (cursor2.moveToNext()) {

            dados_spinner.add(cursor2.getString(cursor2.getColumnIndex("MUSCULO")));

        }
        cursor2.close();

        return (dados_spinner);
    }

    @SuppressLint("Range")
    public ArrayList<String> buscarNomeMusculoSippner(String DIADASEMANA, String IDUSER) {

        SQLiteDatabase db = getReadableDatabase();

        String sql_busca_dados_spinner = "SELECT DISTINCT MUSCULO FROM  MUSCULOS where DIADASEMANA = ? AND IDUSER = ? ORDER BY ID DESC";

        ArrayList<String> dados_spinner = new ArrayList<>();

        String[] parametros = {DIADASEMANA, IDUSER};
        parametros[0] = DIADASEMANA;

        Cursor cursor = db.rawQuery(sql_busca_dados_spinner, parametros);

        while (cursor.moveToNext()) {

            dados_spinner.add(cursor.getString(cursor.getColumnIndex("MUSCULO")));

        }
        cursor.close();
        return (dados_spinner);
    }

    @SuppressLint("Range")
    public ArrayList<String> buscarPosicaoMusculoSippner(String DIADASEMANA, String IDUSER) {

        SQLiteDatabase db = getReadableDatabase();

        String sql_busca_dados_spinner = "SELECT POSICAO FROM  MUSCULOS where DIADASEMANA = ? AND IDUSER = ?";

        ArrayList<String> dados_spinner = new ArrayList<>();

        String[] parametros = {DIADASEMANA, IDUSER};
        parametros[0] = DIADASEMANA;

        Cursor cursor = db.rawQuery(sql_busca_dados_spinner, parametros);

        while (cursor.moveToNext()) {

            dados_spinner.add(cursor.getString(cursor.getColumnIndex("POSICAO")));

        }
        cursor.close();
        return (dados_spinner);
    }

    @SuppressLint("Range")
    public ArrayList<String> buscarNomeUserSippnerTreino() {

        SQLiteDatabase db = getReadableDatabase();

        String sql_busca_dados_spinner = "SELECT DISTINCT NOMEUSER FROM  usuario ORDER BY IDUSER desc;";

        ArrayList<String> dados_spinner = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql_busca_dados_spinner, null);

        while (cursor.moveToNext()) {
            dados_spinner.add(cursor.getString(cursor.getColumnIndex("NOMEUSER")));
        }
        cursor.close();
        return (dados_spinner);
    }


    public String buscarIDMusc(String DIADASEMANA, String MUSCULO, String IDUSER) {

        String selectQuery = "SELECT ID FROM  MUSCULOS where DIADASEMANA = ? and MUSCULO = ? AND IDUSER = ?";

        String[] parametros = {DIADASEMANA, MUSCULO, IDUSER};
        //parametros[0] = {DIADASEMANA, ID};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, parametros);

        if (cursor.moveToNext()) {
            idmusculo6 = cursor.getString(0);
        } else {
            idmusculo6 = "0";
        }
        cursor.close();

        return idmusculo6;

    }

    public String buscarIDUser(String NOMEUSER) {

        String selectQuery = "SELECT IDUSER FROM  usuario where NOMEUSER = ?";

        String[] parametros = {NOMEUSER};
        //parametros[0] = {DIADASEMANA, ID};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, parametros);

        if (cursor.moveToNext()) {
            idmusculo5 = cursor.getString(0);
        } else {
            idmusculo5 = "0";
        }
        cursor.close();

        return idmusculo5;

    }

    @SuppressLint("Range")
    public ArrayList<String> buscarSemanaUpdateSippner(int ID) {

        SQLiteDatabase db = getReadableDatabase();

        String sql_busca_dados_spinner = "SELECT DIADASEMANA FROM  TREINO where ID = ?";

        ArrayList<String> dados_spinner = new ArrayList<>();

        String[] parametros = {String.valueOf(ID)};
        ;
        //parametros[0] = {DIADASEMANA, ID};

        Cursor cursor = db.rawQuery(sql_busca_dados_spinner, parametros);

        while (cursor.moveToNext()) {

            dados_spinner.add(cursor.getString(cursor.getColumnIndex("DIADASEMANA")));

        }
        cursor.close();
        return (dados_spinner);
    }

    public String select_Nome() {

        String selectQuery = "select NOMEUSER from MEDIDAS order by ID desc limit 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            resultListItens = cursor.getString(0);
        }
        cursor.close();
        return resultListItens;

    }

    @SuppressLint("Range")
    public ArrayList<String> buscarNomeUserSippner() {

        SQLiteDatabase db = getReadableDatabase();

        String sql_busca_dados_spinner = "SELECT DISTINCT NOMEUSER FROM  MEDIDAS ORDER BY NOMEUSER DESC;";

        ArrayList<String> dados_spinner = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql_busca_dados_spinner, null);

        while (cursor.moveToNext()) {
            dados_spinner.add(cursor.getString(cursor.getColumnIndex("NOMEUSER")));
        }
        cursor.close();
        return (dados_spinner);
    }

    public void excluirtodosrgmed() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "RG_MEDIDAS");
    }

    public void excluirtudasmeds() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + "MEDIDAS");
    }

    public boolean excluirUser(int IDUSER) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("usuario", "IDUSER=?", new String[]{IDUSER + ""}) > 0;
    }

    public boolean excluir_RG_Med(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("MEDIDAS", "ID=?", new String[]{ID + ""}) > 0;
    }

    public boolean excluirExercicio(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("TREINO", "ID=?", new String[]{ID + ""}) > 0;
    }

    public boolean excluirMusculo(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("MUSCULOS", "ID=?", new String[]{ID + ""}) > 0;
    }

    public boolean excluirExercicioComMusc(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("TREINO", "IDMUSC=?", new String[]{ID + ""}) > 0;
    }

    public String desmarcarTodosPorSemana(String DIADASEMANA) {

        String InsertQuery = "UPDATE TREINO SET CHECK1 = 0, CHECK2 = 0, CHECK3 = 0, CHECK4 = 0, CHECK5 = 0, " +
                "CHECK6 = 0, CHECK7 = 0, CHECK8 = 0, CHECK9 = 0, CHECK10 = 0  where DIADASEMANA = ?";

        String[] parametros = new String[1];
        parametros[0] = DIADASEMANA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(InsertQuery, parametros);

        if (cursor.moveToNext()) {

            result = cursor.getString(0);
        }

        cursor.close();
        return result;

    }

    //Alterar posicao dos musculos que sera subistituido antes da posicao do musculo selecionado para alteraçao da posicao
    public String updatePosicaoMusculoSubstituir(String PosicaoNova, String PosicaoAnt, String DiadaSemana) {

        String updateQuery = "UPDATE MUSCULOS SET POSICAO = ? WHERE POSICAO = ? AND DIADASEMANA = ?";

        String[] parametros = {PosicaoNova, PosicaoAnt, DiadaSemana};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }

    public String updatePosicaoMusculoSubstituirPorId(String PosicaoNova, String ID) {

        String updateQuery = "UPDATE MUSCULOS SET POSICAO = ? WHERE ID = ?";

        String[] parametros = {PosicaoNova, ID};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }

    //Alterar posicao do musculo selecionado para alteraçao da posicao
    public String updatePosicaoMusculo(String PosicaoAnt, String PosicaoNova, String DiadaSemana, String IdMusc) {

        String updateQuery = "UPDATE MUSCULOS SET POSICAO = ? WHERE POSICAO = ? AND DIADASEMANA = ? AND ID = ?";

        String[] parametros = {PosicaoAnt, PosicaoNova, DiadaSemana, IdMusc};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }

    public String updatePosicaoMusculoPorID(String PosicaoAnt, String IdMusc) {

        String updateQuery = "UPDATE MUSCULOS SET POSICAO = ? WHERE ID = ?";

        String[] parametros = {PosicaoAnt, IdMusc};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }

    public String updatePosicaoMusculoDialog(String NovaPosicao, String MusculoAlvo, String DiadaSemana) {

        String updateQuery = "UPDATE MUSCULOS SET POSICAO = ? WHERE MUSCULO = ? AND DIADASEMANA = ?";

        String[] parametros = {NovaPosicao, MusculoAlvo, DiadaSemana};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }


    //Lista de posicoes para o spinerPosicao
    @SuppressLint("Range")
    public ArrayList<String> buscarPosicaoSippner(String Semana) {

        SQLiteDatabase db = getReadableDatabase();

        String sql_busca_dados_spinner = "SELECT DISTINCT POSICAO FROM  MUSCULOS  WHERE DIADASEMANA = ? ORDER BY POSICAO;";

        String[] parametros = {Semana};
        //parametros[0] = {DIADASEMANA, ID};

        ArrayList<String> dados_spinner = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql_busca_dados_spinner, parametros);

        while (cursor.moveToNext()) {
            dados_spinner.add(cursor.getString(cursor.getColumnIndex("POSICAO")));
        }
        cursor.close();
        return (dados_spinner);
    }

    //Alterar dia da semana do musculo
    public String updateDiaDaSemanaMusculo(String DiaDaSemana, String UltimaPosicao, String IdMusc) {

        String updateQuery = "UPDATE MUSCULOS SET DIADASEMANA = ?, POSICAO = ? WHERE ID = ? ";

        String[] parametros = {DiaDaSemana, UltimaPosicao, IdMusc};

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(updateQuery, parametros);

        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;

    }


}

