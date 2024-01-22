package com.lalp.sist.controle_de_medidas.dominios.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Usuario;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Repo_User {

    static {
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
        );
    }

    public Usuario usuario;
    private SQLiteDatabase conexaorg_user;
    public static SQLiteDatabase conexaouserstatic;
    public static String nomeusertreino;

    private Context context;

    private static DataBase dataBaseLista;
    private List<Medidas> dados;


    public Medidas medidasComp;

    private String result, resultZero;

    public FileOutputStream streamDeSaida;


    public Repo_User(SQLiteDatabase conexaorg_user) {
        this.conexaorg_user = conexaorg_user;
    }

    public void inserirUsuario(Usuario usuario) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("NOMEUSER", usuario.NOMEUSER);
        contentValues.put("TIPODETREINO", usuario.TIPODETREINO);
        contentValues.put("DATAINICIO", usuario.DATAINICIO);
        contentValues.put("DATAFIM", usuario.DATAFIM);

        conexaorg_user.insertOrThrow("usuario", null, contentValues);
    }

    public List<Usuario> buscarTodosUser() {

        List<Usuario> usuarios = new ArrayList<Usuario>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM usuario order by IDUSER");

        Cursor resultado = conexaorg_user.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            do {

                Usuario rg = new Usuario();

                rg.IDUSER = resultado.getInt(resultado.getColumnIndexOrThrow("IDUSER"));
                rg.NOMEUSER = resultado.getString(resultado.getColumnIndexOrThrow("NOMEUSER"));
                rg.TIPODETREINO = resultado.getString(resultado.getColumnIndexOrThrow("TIPODETREINO"));
                rg.DATAINICIO = resultado.getString(resultado.getColumnIndexOrThrow("DATAINICIO"));
                rg.DATAFIM = resultado.getString(resultado.getColumnIndexOrThrow("DATAFIM"));

                usuarios.add(rg);

            } while (resultado.moveToNext());

        }

        return usuarios;
    }

    public void alterarUser(Usuario usuario) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("NOMEUSER", usuario.NOMEUSER);
        contentValues.put("TIPODETREINO", usuario.TIPODETREINO);
        contentValues.put("DATAINICIO", usuario.DATAINICIO);
        contentValues.put("DATAFIM", usuario.DATAFIM);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(usuario.IDUSER);

        conexaorg_user.update("usuario", contentValues, "IDUSER = ? ", parametros);
    }

    //Gerar planilha
    public List<Usuario> geraPlanilhaNomeUser(int IDUSER) {

        List<Usuario> usuarios = new ArrayList<Usuario>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM usuario WHERE IDUSER = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(IDUSER);

        Cursor resultado = conexaorg_user.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            do {

                Usuario list = new Usuario();

                list.IDUSER = resultado.getInt(resultado.getColumnIndexOrThrow("IDUSER"));
                list.NOMEUSER = resultado.getString(resultado.getColumnIndexOrThrow("NOMEUSER"));
                list.TIPODETREINO = resultado.getString(resultado.getColumnIndexOrThrow("TIPODETREINO"));
                list.DATAINICIO = resultado.getString(resultado.getColumnIndexOrThrow("DATAINICIO"));
                list.DATAFIM = resultado.getString(resultado.getColumnIndexOrThrow("DATAFIM"));


                usuarios.add(list);

            } while (resultado.moveToNext());

            sqliteArqExcelNomeUser(usuarios);
        }

        return usuarios;
    }

    public static String nomeUserTreino(int IDUSER) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT NOMEUSER FROM usuario WHERE IDUSER = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(IDUSER);

        Cursor resultadoNomeUser = conexaouserstatic.rawQuery(sql.toString(), parametros);

        if (resultadoNomeUser.moveToNext()) {

            nomeusertreino = resultadoNomeUser.getString(0);
        }

        return nomeusertreino;

    }

    public static void sqliteArqExcelNomeUser(List<Usuario> usuarios) {

        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        File pasta = new File(root.getAbsolutePath());
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        File arquivoXLSNomeLista = new File(pasta, "NomeUserTreinoCSV.csv");

        FileOutputStream streamDeSaida = null;

        try {
            streamDeSaida = new FileOutputStream(arquivoXLSNomeLista);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Usuario usuario : usuarios) {

            String nomeUser = usuario.getNOMEUSER() + "," + usuario.getTIPODETREINO() + "," + usuario.getDATAINICIO() + "," + usuario.getDATAFIM();

            try {
                streamDeSaida.write(nomeUser.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            streamDeSaida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        converterCsvParaXLSX();

    }

    public static void converterCsvParaXLSX() {
        try {

            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            File pasta = new File(root.getAbsolutePath());
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            File csvFileAddress = new File(pasta, "NomeUserTreinoCSV.csv");

            File xlsxFileAddressNomeListConvert = new File(pasta, "NomeUserTreino.xlsx");

            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("UsuarioDoTreino");
            String currentLine = null;
            int RowNum = 0;
            BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(",");
                RowNum++;
                XSSFRow currentRow = sheet.createRow(RowNum);
                for (int i = 0; i < str.length; i++) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(xlsxFileAddressNomeListConvert);
            workBook.write(fileOutputStream);
            fileOutputStream.close();

            csvFileAddress.delete();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

