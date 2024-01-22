package com.lalp.sist.controle_de_medidas.dominios.repositorio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Musculo;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Usuario;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

public class RepoMusculo {

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

    public Musculo musculo;
    private SQLiteDatabase conexa_musc;
    public static SQLiteDatabase conexaomuscstatic;
    public static String nomemusculos;

    public Context context;

    private static DataBase dataBaseLista;
    private List<Musculo> dados;
    public static String nomemusculostreino;


    public SQLiteDatabase conexaotreino;

    public RepoMusculo(SQLiteDatabase conexaotreino) {
        this.context = context;
        this.conexaotreino = conexaotreino;
    }

    public List<Musculo> buscar_Musculo(String diadasemana) {

        List<Musculo> musculos = new ArrayList<Musculo>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM MUSCULOS WHERE DIADASEMANA = ?");

        String[] parametros = {diadasemana};

        Cursor curso = conexaotreino.rawQuery(sql.toString(), parametros);

        if (curso.getCount() > 0) {

            curso.moveToFirst();

            do {

                Musculo musc = new Musculo();

                musc.ID = curso.getInt(curso.getColumnIndexOrThrow("ID"));
                musc.MUSCULO = curso.getString(curso.getColumnIndexOrThrow("MUSCULO"));
                musc.DIADASEMANA = curso.getString(curso.getColumnIndexOrThrow("DIADASEMANA"));

                musculos.add(musc);

            } while (curso.moveToNext());

        }

        return musculos;
    }

    //Gerar planilha
    public static String nomeUserTreino(int IDUSER) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT NOMEUSER FROM usuario WHERE IDUSER = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(IDUSER);

        Cursor resultadoNomeUser = conexaomuscstatic.rawQuery(sql.toString(), parametros);

        if (resultadoNomeUser.moveToNext()) {

            nomemusculostreino = resultadoNomeUser.getString(0);
        }

        return nomemusculostreino;

    }

    public List<Musculo> geraPlanilaMusculos(int IDUSER) {

        musculo = new Musculo();

        List<Musculo> musculos = new ArrayList<Musculo>();

        StringBuilder sql = new StringBuilder();

        sql.append(" select * from MUSCULOS where IDUSER = ? order by ID");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(IDUSER);

        Cursor resultado = conexaotreino.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();
            do {
                Musculo itemlista = new Musculo();

                itemlista.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                itemlista.MUSCULO = resultado.getString(resultado.getColumnIndexOrThrow("MUSCULO"));
                itemlista.QUANTMUSCULO = resultado.getInt(resultado.getColumnIndexOrThrow("QUANTMUSCULO"));
                itemlista.POSICAO = resultado.getInt(resultado.getColumnIndexOrThrow("POSICAO"));
                itemlista.DIADASEMANA = resultado.getString(resultado.getColumnIndexOrThrow("DIADASEMANA"));
                itemlista.IDUSER = resultado.getInt(resultado.getColumnIndexOrThrow("IDUSER"));


                musculos.add(itemlista);


            } while (resultado.moveToNext());

            sqliteExcelMusculos(musculos);
        }

        return musculos;

    }

    public void sqliteExcelMusculos(List<Musculo> musculos) {

        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        File pasta = new File(root.getAbsolutePath());
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        File arquivoXLS = new File(pasta, "MusculosDoTreinoCSV.csv");

        FileOutputStream streamDeSaida = null;

        try {
            streamDeSaida = new FileOutputStream(arquivoXLS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*

         try {
                streamDeSaida.write(colunas.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

          */
        for (Musculo musculo : musculos) {
            //String dadosDeItens = "\"" + item.getNome() + "\",\"" + item.getPreco() + "\",\"" + item.getQuant() + "\""+"\n";
            // String dadosDeItens = item.getNome() + "," + item.getPreco() + "," + item.getQuant() + "\n";
            String dadosDoMusculo = musculo.getMUSCULO() + "," + musculo.getQUANTMUSCULO() + "," + musculo.getPOSICAO() + "," + musculo.getDIADASEMANA() +"\n";


            try {
                streamDeSaida.write(dadosDoMusculo.getBytes());

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
            //File root = new File(Environment.getExternalStorageDirectory() + "/ListSomaCompartCompras");
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            //File root = new File(context.getExternalFilesDir("/"), "/Download");
            File pasta = new File(root.getAbsolutePath());
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            //File  csvFileAddress = new File(pasta, "intens_da_lista_" + nomeListaCompras(Integer.parseInt(edtComp.getText().toString())) + ".csv");
            File csvFileAddress = new File(pasta, "MusculosDoTreinoCSV.csv");
            //File  xlsxFileAddress = new File(pasta, "intens_da_lista_" + nomeListaCompras(Integer.parseInt(edtComp.getText().toString())) +".xlsx");
            //File xlsxFileAddress = new File(pasta, "ItensListCompras.xlsx");
            File xlsxFileAddressItemConvert = new File(pasta, "MusculosDoTreino.xlsx");

            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("ListaDeMusculos");
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
            FileOutputStream fileOutputStream = new FileOutputStream(xlsxFileAddressItemConvert);
            workBook.write(fileOutputStream);
            fileOutputStream.close();

            csvFileAddress.delete();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void converterCsvParaXLSXJaCriado() {
        try {

            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            File pasta = new File(root.getAbsolutePath());
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            File csvFileAddress = new File(pasta, "MusculosDoTreinoCSV.csv");

            File xlsxFileAddressItemConvert = new File(pasta, "NomeUserTreino.xlsx");

            XSSFWorkbook workBook = new XSSFWorkbook(Environment.DIRECTORY_DOWNLOADS);
            XSSFSheet sheet = workBook.createSheet("ListaDeMusculos");
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

            FileOutputStream fileOutputStream = new FileOutputStream(xlsxFileAddressItemConvert);
            workBook.write(fileOutputStream);
            fileOutputStream.close();

            csvFileAddress.delete();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
