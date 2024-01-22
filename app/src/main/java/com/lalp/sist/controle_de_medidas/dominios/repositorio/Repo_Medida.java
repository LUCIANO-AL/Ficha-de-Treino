package com.lalp.sist.controle_de_medidas.dominios.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;


import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;

import java.util.ArrayList;
import java.util.List;


public class Repo_Medida {

    private Context context;

    private static DataBase dataBaseLista;
    private List<Medidas> dados;

    public Medidas medidas;
    public Medidas medidasComp;

    private String result, resultZero;

    private SQLiteDatabase conexaorg_med;

    public Repo_Medida(SQLiteDatabase conexaorg_med){this.conexaorg_med = conexaorg_med;}

    public void inserirMedidas(Medidas medidas){

        ContentValues contentValues = new ContentValues();

        contentValues.put("NOMEUSER", medidas.NomeUser);
        contentValues.put("DATARG", medidas.Datarg);
        contentValues.put("PESO", medidas.Peso);
        contentValues.put("ALTURA", medidas.Altura);
        contentValues.put("PEITO", medidas.Peito);
        contentValues.put("OMBROS", medidas.Ombros);
        contentValues.put("BICEPSDIR", medidas.Bicepsdir);
        contentValues.put("BICEPSESQ", medidas.Bicepsesq);
        contentValues.put("BICEPSDIRCONTR", medidas.BicepsdirContr);
        contentValues.put("BICEPSESQCONTR", medidas.BicepsesqContr);
        contentValues.put("ANTBRACDIR", medidas.Antbracdir);
        contentValues.put("ANTBRACESQ", medidas.Antbracesq);
        contentValues.put("CINTURA", medidas.Cintura);
        contentValues.put("BUMBUM", medidas.Bumbum);
        contentValues.put("COXADIR", medidas.Coxadir);
        contentValues.put("COXAESQ", medidas.Coxaesq);
        contentValues.put("PANTURDIR", medidas.Panturdir);
        contentValues.put("PANTURESQ", medidas.Panturesq);

        conexaorg_med.insertOrThrow("MEDIDAS", null, contentValues );
    }

    public List<Medidas> buscarTodos_Medidas() {

        List<Medidas> medidas = new ArrayList<Medidas>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM MEDIDAS order by ID desc");

        Cursor resultado = conexaorg_med.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0) {

            resultado.moveToFirst();

            do {

                Medidas rg = new Medidas();

                rg.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                rg.NomeUser = resultado.getString(resultado.getColumnIndexOrThrow("NOMEUSER"));
                rg.Datarg = resultado.getString(resultado.getColumnIndexOrThrow("DATARG"));
                rg.Peso = resultado.getString(resultado.getColumnIndexOrThrow("PESO"));
                rg.Altura = resultado.getString(resultado.getColumnIndexOrThrow("ALTURA"));
                rg.Peito = resultado.getString(resultado.getColumnIndexOrThrow("PEITO"));
                rg.Ombros = resultado.getString(resultado.getColumnIndexOrThrow("OMBROS"));
                rg.Bicepsdir = resultado.getString(resultado.getColumnIndexOrThrow("BICEPSDIR"));
                rg.Bicepsesq= resultado.getString(resultado.getColumnIndexOrThrow("BICEPSESQ"));
                rg.BicepsdirContr = resultado.getString(resultado.getColumnIndexOrThrow("BICEPSDIRCONTR"));
                rg.BicepsesqContr= resultado.getString(resultado.getColumnIndexOrThrow("BICEPSESQCONTR"));
                rg.Antbracdir = resultado.getString(resultado.getColumnIndexOrThrow("ANTBRACDIR"));
                rg.Antbracesq = resultado.getString(resultado.getColumnIndexOrThrow("ANTBRACESQ"));
                rg.Cintura = resultado.getString(resultado.getColumnIndexOrThrow("CINTURA"));
                rg.Bumbum = resultado.getString(resultado.getColumnIndexOrThrow("BUMBUM"));
                rg.Coxadir = resultado.getString(resultado.getColumnIndexOrThrow("COXADIR"));
                rg.Coxaesq = resultado.getString(resultado.getColumnIndexOrThrow("COXAESQ"));
                rg.Panturdir = resultado.getString(resultado.getColumnIndexOrThrow("PANTURDIR"));
                rg.Panturesq = resultado.getString(resultado.getColumnIndexOrThrow("PANTURESQ"));

                medidas.add(rg);

            } while (resultado.moveToNext());


        }

        return medidas;
    }

    public void alterarMedida(Medidas medidas) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("NOMEUSER", medidas.NomeUser);
        contentValues.put("DATARG", medidas.Datarg);
        contentValues.put("PESO", medidas.Peso);
        contentValues.put("ALTURA", medidas.Altura);
        contentValues.put("PEITO", medidas.Peito);
        contentValues.put("OMBROS", medidas.Ombros);
        contentValues.put("BICEPSDIR", medidas.Bicepsdir);
        contentValues.put("BICEPSESQ", medidas.Bicepsesq);
        contentValues.put("BICEPSDIRCONTR", medidas.BicepsdirContr);
        contentValues.put("BICEPSESQCONTR", medidas.BicepsesqContr);
        contentValues.put("ANTBRACDIR", medidas.Antbracdir);
        contentValues.put("ANTBRACESQ", medidas.Antbracesq);
        contentValues.put("CINTURA", medidas.Cintura);
        contentValues.put("BUMBUM", medidas.Bumbum);
        contentValues.put("COXADIR", medidas.Coxadir);
        contentValues.put("COXAESQ", medidas.Coxaesq);
        contentValues.put("PANTURDIR", medidas.Panturdir);
        contentValues.put("PANTURESQ", medidas.Panturesq);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(medidas.ID);

        conexaorg_med.update("MEDIDAS", contentValues,"ID = ? ", parametros);
    }

    public List<Medidas> buscarTodos_Comparativos(String Nome) {

        List<Medidas> medidasComp = new ArrayList<Medidas>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM MEDIDAS WHERE NOMEUSER = ? ORDER BY ID DESC");
        //sql.append("ORDER BY julianday((substr(DATARG, 7, 4) || '-' || substr(DATARG, 4, 2) || '-' || substr(DATARG, 1, 2)));");

        String[] parametros = {Nome};

        Cursor curso = conexaorg_med.rawQuery(sql.toString(), parametros);

        if(curso.getCount() > 0) {

            curso.moveToFirst();

            do {

                Medidas rgcomp = new Medidas();

                rgcomp.ID = curso.getInt(curso.getColumnIndexOrThrow("ID"));
                rgcomp.NomeUser = curso.getString(curso.getColumnIndexOrThrow("NOMEUSER"));
                rgcomp.Datarg = curso.getString(curso.getColumnIndexOrThrow("DATARG"));
                rgcomp.Peso = curso.getString(curso.getColumnIndexOrThrow("PESO"));
                rgcomp.Altura = curso.getString(curso.getColumnIndexOrThrow("ALTURA"));
                rgcomp.Peito = curso.getString(curso.getColumnIndexOrThrow("PEITO"));
                rgcomp.Ombros = curso.getString(curso.getColumnIndexOrThrow("OMBROS"));
                rgcomp.Bicepsdir = curso.getString(curso.getColumnIndexOrThrow("BICEPSDIR"));
                rgcomp.Bicepsesq= curso.getString(curso.getColumnIndexOrThrow("BICEPSESQ"));
                rgcomp.BicepsdirContr = curso.getString(curso.getColumnIndexOrThrow("BICEPSDIRCONTR"));
                rgcomp.BicepsesqContr= curso.getString(curso.getColumnIndexOrThrow("BICEPSESQCONTR"));
                rgcomp.Antbracdir = curso.getString(curso.getColumnIndexOrThrow("ANTBRACDIR"));
                rgcomp.Antbracesq = curso.getString(curso.getColumnIndexOrThrow("ANTBRACESQ"));
                rgcomp.Cintura = curso.getString(curso.getColumnIndexOrThrow("CINTURA"));
                rgcomp.Bumbum = curso.getString(curso.getColumnIndexOrThrow("BUMBUM"));
                rgcomp.Coxadir = curso.getString(curso.getColumnIndexOrThrow("COXADIR"));
                rgcomp.Coxaesq = curso.getString(curso.getColumnIndexOrThrow("COXAESQ"));
                rgcomp.Panturdir = curso.getString(curso.getColumnIndexOrThrow("PANTURDIR"));
                rgcomp.Panturesq = curso.getString(curso.getColumnIndexOrThrow("PANTURESQ"));

                medidasComp.add(rgcomp);

            } while (curso.moveToNext());

        }

        return medidasComp;
    }



}

