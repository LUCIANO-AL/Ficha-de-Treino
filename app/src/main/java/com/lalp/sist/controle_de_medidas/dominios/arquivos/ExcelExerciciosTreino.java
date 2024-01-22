package com.lalp.sist.controle_de_medidas.dominios.arquivos;

import android.content.ContentValues;
import android.util.Log;

import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Exercicio;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;


public class ExcelExerciciosTreino {
    public DataBase dataBaseLista;
    public static final String Tablename = "itemc2";


    public static final String MUSCULO = "MUSCULO";// 1
    public static final String EXERCICIO = "EXERCICIO";// 2
    public static final String SEQUENCIA = "SEQUENCIA";// 3
    public static final String SERIES = "SERIES";// 4
    public static final String REPETICOES = "REPETICOES";// 5 integer
    public static final String CARGA = "CARGA";// 6 integer
    public static final String CHECK1 = "CHECK1";// 7 integer
    public static final String REPETICOES2 = "REPETICOES2";// 8 integer
    public static final String CARGA2 = "CARGA2";// 9 integer
    public static final String CHECK2 = "CHECK2";// 10 integer
    public static final String REPETICOES3 = "REPETICOES3";// 11 integer
    public static final String CARGA3 = "CARGA3";// 12 integer
    public static final String CHECK3 = "CHECK3";// 13 integer
    public static final String REPETICOES4 = "REPETICOES4";// 14 integer
    public static final String CARGA4 = "CARGA4";// 15 integer
    public static final String CHECK4 = "CHECK4";// 16 integer
    public static final String REPETICOES5 = "REPETICOES5";// 17 integer
    public static final String CARGA5 = "CARGA5";// 18 integer
    public static final String CHECK5 = "CHECK5";// 19 integer
    public static final String REPETICOES6 = "REPETICOES6";// 20 integer
    public static final String CARGA6 = "CARGA6";// 21 integer
    public static final String CHECK6 = "CHECK6";// 22 integer
    public static final String REPETICOES7 = "REPETICOES7";// 23 integer
    public static final String CARGA7 = "CARGA7";// 24 integer
    public static final String CHECK7 = "CHECK7";// 25 integer
    public static final String REPETICOES8 = "REPETICOES8";// 26 integer
    public static final String CARGA8 = "CARGA8";// 27 integer
    public static final String CHECK8 = "CHECK8";// 28 integer
    public static final String REPETICOES9 = "REPETICOES9";// 29 integer
    public static final String CARGA9 = "CARGA9";// 30 integer
    public static final String CHECK9 = "CHECK9";// 31 integer
    public static final String REPETICOES10 = "REPETICOES10";// 32 integer
    public static final String CARGA10 = "CARGA10";// 33 integer
    public static final String CHECK10 = "CHECK10";// 34 integer
    public static final String DIADASEMANA = "DIADASEMANA";// 35 integer
    public static final String IDMUSC = "IDMUSC";// 36 integer
    public static final String IDUSER = "IDUSER";// 37 integer


    public  static Exercicio exercicio;

   public static void insertExcelToSqliExercicio(DataBase dataBaseLista, Sheet sheet) {

       dataBaseLista.selectUltimoIDUser();

        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            Row row = rit.next();

            ContentValues contentValues = new ContentValues();

            row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(19, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(20, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(21, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(22, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(23, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(24, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(25, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(26, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(27, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(28, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(29, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(30, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(31, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(32, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(33, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(34, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);


            contentValues.put(MUSCULO, row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(EXERCICIO, row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(SEQUENCIA, row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(SERIES, row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES, row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA, row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK1, row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES2, row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA2, row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK2, row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES3, row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA3, row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK3, row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES4, row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA4, row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK4, row.getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES5, row.getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA5, row.getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK5, row.getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES6, row.getCell(19, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA6, row.getCell(20, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK6, row.getCell(21, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES7, row.getCell(22, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA7, row.getCell(23, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK7, row.getCell(24, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES8, row.getCell(25, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA8, row.getCell(26, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK8, row.getCell(27, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES9, row.getCell(28, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA9, row.getCell(29, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK9, row.getCell(30, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(REPETICOES10, row.getCell(31, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CARGA10, row.getCell(32, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(CHECK10, row.getCell(33, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(DIADASEMANA, row.getCell(34, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(IDMUSC, dataBaseLista.buscarIDMusc(row.getCell(34, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
                    row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), dataBaseLista.selectUltimoIDUser()));
            contentValues.put(IDUSER, dataBaseLista.selectUltimoIDUser());

            try {
                if (dataBaseLista.insertExercicio("TREINO", contentValues) < 0) {
                    return;
                }
            } catch (Exception ex) {
                Log.d("Exception in importing", ex.getMessage().toString());
            }
        }
    }

}
