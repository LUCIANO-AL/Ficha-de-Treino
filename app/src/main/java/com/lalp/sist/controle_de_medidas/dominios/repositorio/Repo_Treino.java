package com.lalp.sist.controle_de_medidas.dominios.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Exercicio;
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

public class Repo_Treino {

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

    //SELECT DISTINCT MUSCULO FROM TREINO

    //SELECT * FROM TREINO WHERE MUSCULO = "Quadriceps"
    //SELECT * FROM TREINO WHERE MUSCULO = ?

    public Context context;

    private static DataBase dataBaseLista;
    private List<Exercicio> dados;

    public Exercicio exercicio;
    public Musculo musculo;
    public Usuario usuario;

    private String result, resultZero;

    private SQLiteDatabase conexaotreino;
    public static  SQLiteDatabase conexaoexerciciostatic;
    public static String nomeexerciciotreino;

    public Repo_Treino(SQLiteDatabase conexaotreino){this.conexaotreino = conexaotreino;}

    public Repo_Treino(Context context) {
        this.context = context;
    }

    public void repo_inserirExercicio(Exercicio exercicio){

        ContentValues contentValues = new ContentValues();

        contentValues.put("MUSCULO", exercicio.MUSCULO);
        contentValues.put("EXERCICIO", exercicio.EXERCICIO);
        contentValues.put("SEQUENCIA", exercicio.SEQUENCIA);
        contentValues.put("SERIES", exercicio.SERIES);
        contentValues.put("REPETICOES", exercicio.REPETICOES);
        contentValues.put("CARGA", exercicio.CARGA);
        contentValues.put("CHECK1",exercicio.CHECK1 ? 1 : 0);
        contentValues.put("REPETICOES2", exercicio.REPETICOES2);
        contentValues.put("CARGA2", exercicio.CARGA2);
        contentValues.put("CHECK2",exercicio.CHECK2 ? 1 : 0);
        contentValues.put("REPETICOES3", exercicio.REPETICOES3);
        contentValues.put("CARGA3", exercicio.CARGA3);
        contentValues.put("CHECK3",exercicio.CHECK3 ? 1 : 0);
        contentValues.put("REPETICOES4", exercicio.REPETICOES4);
        contentValues.put("CARGA4", exercicio.CARGA4);
        contentValues.put("CHECK4",exercicio.CHECK4 ? 1 : 0);
        contentValues.put("REPETICOES5", exercicio.REPETICOES5);
        contentValues.put("CARGA5", exercicio.CARGA5);
        contentValues.put("CHECK5",exercicio.CHECK5 ? 1 : 0);
        contentValues.put("REPETICOES6", exercicio.REPETICOES6);
        contentValues.put("CARGA6", exercicio.CARGA6);
        contentValues.put("CHECK6",exercicio.CHECK6 ? 1 : 0);
        contentValues.put("REPETICOES7", exercicio.REPETICOES7);
        contentValues.put("CARGA7", exercicio.CARGA7);
        contentValues.put("CHECK7",exercicio.CHECK7 ? 1 : 0);
        contentValues.put("REPETICOES8", exercicio.REPETICOES8);
        contentValues.put("CARGA8", exercicio.CARGA8);
        contentValues.put("CHECK8",exercicio.CHECK8 ? 1 : 0);
        contentValues.put("REPETICOES9", exercicio.REPETICOES9);
        contentValues.put("CARGA9", exercicio.CARGA9);
        contentValues.put("CHECK9",exercicio.CHECK9 ? 1 : 0);
        contentValues.put("REPETICOES10", exercicio.REPETICOES10);
        contentValues.put("CARGA10", exercicio.CARGA10);
        contentValues.put("CHECK10",exercicio.CHECK10 ? 1 : 0);
        contentValues.put("DIADASEMANA", exercicio.DIADASEMANA);
        contentValues.put("IDMUSC", exercicio.IDMUSC);
        contentValues.put("IDUSER", exercicio.IDUSER);

        conexaotreino.insertOrThrow("TREINO", null, contentValues );
    }

    public void inserirMusculo(Musculo musculo){

        ContentValues contentValues = new ContentValues();

        contentValues.put("MUSCULO", musculo.MUSCULO);
        contentValues.put("QUANTMUSCULO", musculo.QUANTMUSCULO);
        contentValues.put("POSICAO", musculo.POSICAO);
        contentValues.put("DIADASEMANA", musculo.DIADASEMANA);
        contentValues.put("IDUSER", musculo.IDUSER);

        conexaotreino.insertOrThrow("MUSCULOS", null, contentValues );
    }

    public void inserirUsuario(Usuario usuario){

        ContentValues contentValues = new ContentValues();

        contentValues.put("NOMEUSER", usuario.NOMEUSER);
        contentValues.put("TIPODETREINO", usuario.TIPODETREINO);
        contentValues.put("DATAINICIO", usuario.DATAINICIO);
        contentValues.put("DATAFIM", usuario.DATAFIM);

        conexaotreino.insertOrThrow("usuario", null, contentValues );
    }

    public void updateMusculo(Musculo musculo){

        ContentValues contentValues = new ContentValues();

        contentValues.put("MUSCULO", musculo.MUSCULO);
        contentValues.put("POSICAO", musculo.POSICAO);
        contentValues.put("DIADASEMANA", musculo.DIADASEMANA);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(musculo.ID);

        conexaotreino.update("MUSCULOS", contentValues,"ID = ? ", parametros);

    }

    public void repo_updateExercicio(Exercicio exercicio){

        ContentValues contentValues = new ContentValues();

        contentValues.put("MUSCULO", exercicio.MUSCULO);
        contentValues.put("EXERCICIO", exercicio.EXERCICIO);
        contentValues.put("SEQUENCIA", exercicio.SEQUENCIA);
        contentValues.put("SERIES", exercicio.SERIES);
        contentValues.put("REPETICOES", exercicio.REPETICOES);
        contentValues.put("CARGA", exercicio.CARGA);
        //contentValues.put("CHECK1",exercicio.CHECK1 ? 1 : 0);
        contentValues.put("REPETICOES2", exercicio.REPETICOES2);
        contentValues.put("CARGA2", exercicio.CARGA2);
        //contentValues.put("CHECK2",exercicio.CHECK2 ? 1 : 0);
        contentValues.put("REPETICOES3", exercicio.REPETICOES3);
        contentValues.put("CARGA3", exercicio.CARGA3);
        //contentValues.put("CHECK3",exercicio.CHECK3 ? 1 : 0);
        contentValues.put("REPETICOES4", exercicio.REPETICOES4);
        contentValues.put("CARGA4", exercicio.CARGA4);
        //contentValues.put("CHECK4",exercicio.CHECK4 ? 1 : 0);
        contentValues.put("REPETICOES5", exercicio.REPETICOES5);
        contentValues.put("CARGA5", exercicio.CARGA5);
        //contentValues.put("CHECK5",exercicio.CHECK5 ? 1 : 0);
        contentValues.put("REPETICOES6", exercicio.REPETICOES6);
        contentValues.put("CARGA6", exercicio.CARGA6);
        //contentValues.put("CHECK6",exercicio.CHECK6 ? 1 : 0);
        contentValues.put("REPETICOES7", exercicio.REPETICOES7);
        contentValues.put("CARGA7", exercicio.CARGA7);
        //contentValues.put("CHECK7",exercicio.CHECK7 ? 1 : 0);
        contentValues.put("REPETICOES8", exercicio.REPETICOES8);
        contentValues.put("CARGA8", exercicio.CARGA8);
        //contentValues.put("CHECK8",exercicio.CHECK8 ? 1 : 0);
        contentValues.put("REPETICOES9", exercicio.REPETICOES9);
        contentValues.put("CARGA9", exercicio.CARGA9);
        //contentValues.put("CHECK9",exercicio.CHECK9 ? 1 : 0);
        contentValues.put("REPETICOES10", exercicio.REPETICOES10);
        contentValues.put("CARGA10", exercicio.CARGA10);
        //contentValues.put("CHECK10",exercicio.CHECK10 ? 1 : 0);
        contentValues.put("DIADASEMANA", exercicio.DIADASEMANA);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(exercicio.ID);

        conexaotreino.update("TREINO", contentValues,"ID = ? ", parametros);


    }

    public List<Musculo> buscar_Musculo(String diadasemana, String IDUSER, String MUSCULO) {

        List<Musculo> musculos = new ArrayList<Musculo>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM MUSCULOS WHERE DIADASEMANA = ? AND IDUSER = ? AND MUSCULO = ?");

        String[] parametros = {diadasemana, IDUSER, MUSCULO};

        Cursor curso = conexaotreino.rawQuery(sql.toString(), parametros);

        if(curso.getCount() > 0 ) {

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

    //Gerar planilha
    public static String nomeUserTreino(int IDUSER) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT NOMEUSER FROM usuario WHERE IDUSER = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(IDUSER);

        Cursor resultadoNomeUser = conexaoexerciciostatic.rawQuery(sql.toString(), parametros);

        if (resultadoNomeUser.moveToNext()) {

            nomeexerciciotreino = resultadoNomeUser.getString(0);
        }

        return nomeexerciciotreino;

    }
    public List<Exercicio> geraPlanilaExercicios(int ID){

        exercicio = new Exercicio();

        List<Exercicio> exercicios = new ArrayList<Exercicio>();

        StringBuilder sql = new StringBuilder();

        sql.append(" select * from TREINO where IDUSER = ? order by ID");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(ID);

        Cursor resultado = conexaotreino.rawQuery(sql.toString(),parametros);

        if(resultado.getCount() > 0) {

            resultado.moveToFirst();
            do {
                Exercicio itemlista = new Exercicio();

                itemlista.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                itemlista.MUSCULO = resultado.getString(resultado.getColumnIndexOrThrow("MUSCULO"));
                itemlista.EXERCICIO = resultado.getString(resultado.getColumnIndexOrThrow("EXERCICIO"));
                itemlista.SEQUENCIA = resultado.getString(resultado.getColumnIndexOrThrow("SEQUENCIA"));
                itemlista.SERIES = resultado.getString(resultado.getColumnIndexOrThrow("SERIES"));
                itemlista.REPETICOES = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK1 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.REPETICOES2 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES2"));
                itemlista.CARGA2 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA2"));
                itemlista.CHECK2 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK2")) >= 1;
                itemlista.REPETICOES3 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA3 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK3 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.REPETICOES4 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA4 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK4 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.REPETICOES5 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA5 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK5 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.REPETICOES6 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA6 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK6 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.REPETICOES7 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA7 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK7 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.REPETICOES8 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA8 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK8 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.REPETICOES9 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA9 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK9 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.REPETICOES10 = resultado.getString(resultado.getColumnIndexOrThrow("REPETICOES"));
                itemlista.CARGA10 = resultado.getString(resultado.getColumnIndexOrThrow("CARGA"));
                itemlista.CHECK10 = resultado.getInt(resultado.getColumnIndexOrThrow("CHECK1")) >= 1;
                itemlista.DIADASEMANA = resultado.getString(resultado.getColumnIndexOrThrow("DIADASEMANA"));
                itemlista.IDMUSC = resultado.getString(resultado.getColumnIndexOrThrow("IDMUSC"));
                itemlista.IDUSER = resultado.getString(resultado.getColumnIndexOrThrow("IDUSER"));


                exercicios.add(itemlista);


            } while (resultado.moveToNext());

            sqliteExcelExercicios(exercicios);
        }

        return exercicios;

    }
    public void sqliteExcelExercicios(List<Exercicio> exercicios){
        // public void sqliteParaExcel(int ID){

        //String colunas = "\" NOME DO ITEM\" , \"PRECO\" , \"QUANTIDADE\"";

        // File root = context.getApplicationContext().getFilesDir();
        //File root = Environment.getExternalStorageDirectory();
        //File root = new File(Environment.getExternalStorageDirectory() + "/ListSomaCompartCompras");
        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //File root = new File(context.getExternalFilesDir("/"), "/Download");

        File pasta = new File(root.getAbsolutePath());
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        File arquivoXLS = new File(pasta, "ExerciciosDoTreinoCSV.csv");
        //File arquivoXLS = new File(pasta, "intens_da_lista_" + nomeListaCompras(Integer.parseInt(edtComp.getText().toString())) + ".csv");


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
        for (Exercicio exercicio: exercicios) {
            //String dadosDeItens = "\"" + item.getNome() + "\",\"" + item.getPreco() + "\",\"" + item.getQuant() + "\""+"\n";
            // String dadosDeItens = item.getNome() + "," + item.getPreco() + "," + item.getQuant() + "\n";
            String dadosDoExercicio =  exercicio.getMUSCULO() + "," + exercicio.getEXERCICIO() + "," + exercicio.getSEQUENCIA() + "," + exercicio.getSERIES() + ","
                    + exercicio.getREPETICOES() + "," + exercicio.getCARGA() + "," + (exercicio.getChecked1() ? 1 : 0) + ","
                    + exercicio.getREPETICOES2() + "," + exercicio.getCARGA2() + "," + (exercicio.getChecked2() ? 1 : 0) + ","
                    + exercicio.getREPETICOES3() + "," + exercicio.getCARGA3() + "," + (exercicio.getChecked3() ? 1 : 0) + ","
                    + exercicio.getREPETICOES4() + "," + exercicio.getCARGA4() + "," + (exercicio.getChecked4() ? 1 : 0) + ","
                    + exercicio.getREPETICOES5() + "," + exercicio.getCARGA5() + "," + (exercicio.getChecked5() ? 1 : 0) + ","
                    + exercicio.getREPETICOES6() + "," + exercicio.getCARGA6() + "," + (exercicio.getChecked6() ? 1 : 0) + ","
                    + exercicio.getREPETICOES7() + "," + exercicio.getCARGA7() + "," + (exercicio.getChecked7() ? 1 : 0) + ","
                    + exercicio.getREPETICOES8() + "," + exercicio.getCARGA8() + "," + (exercicio.getChecked8() ? 1 : 0) + ","
                    + exercicio.getREPETICOES9() + "," + exercicio.getCARGA9() + "," + (exercicio.getChecked9() ? 1 : 0) + ","
                    + exercicio.getREPETICOES10() + "," + exercicio.getCARGA10() + "," + (exercicio.getChecked10() ? 1 : 0) + ","
                    + exercicio.getDIADASEMANA() +"\n";

            try {
                streamDeSaida.write(dadosDoExercicio.getBytes());

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


            File csvFileAddress = new File(pasta, "ExerciciosDoTreinoCSV.csv");

            File xlsxFileAddressItemConvert = new File(pasta, "ExerciciosDoTreino.xlsx");

            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("ListaDeExercicios");
            String currentLine= null;
            int RowNum=0;
            BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(",");
                RowNum++;
                XSSFRow currentRow=sheet.createRow(RowNum);
                for(int i=0;i<str.length;i++){
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }
            FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddressItemConvert);
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

            File csvFileAddress = new File(pasta, "ExerciciosDoTreinoCSV.csv");

            File xlsxFileAddressItemConvert = new File(pasta, "NomeUserTreino.xlsx");

            XSSFWorkbook workBook = new XSSFWorkbook(Environment.DIRECTORY_DOWNLOADS);
            XSSFSheet sheet = workBook.createSheet("ListaDeExercicios");
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
