package com.lalp.sist.controle_de_medidas.dominios.arquivos;

import android.content.ContentValues;
import android.util.Log;

import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Musculo;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

public class ExcelMusculosTreino {
    public DataBase dataBaseLista;
    public static final String Tablename = "MUSCULOS";


    public static final String MUSCULO = "MUSCULO";// 0 text(String)
    public static final String QUANTMUSCULO = "QUANTMUSCULO";// 1 text(String)
    public static final String POSICAO = "POSICAO";// 2 text(String)
    public static final String DIADASEMANA = "DIADASEMANA";// 3 integer
    public static final String IDUSER = "IDUSER";// 4 integer

    public  static Musculo musculo;

   public static void insertExcelToSqliteMusculo(DataBase dataBaseLista, Sheet sheet) {

        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            Row row = rit.next();

            ContentValues contentValues = new ContentValues();
            row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);

            contentValues.put(MUSCULO, row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(QUANTMUSCULO, row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(POSICAO, row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(DIADASEMANA, row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(IDUSER, dataBaseLista.selectUltimoIDUser());


            try {
                if (dataBaseLista.insertMusculos("MUSCULOS", contentValues) < 0) {
                    return;
                }
            } catch (Exception ex) {
                Log.d("Exception in importing", ex.getMessage().toString());
            }
        }
    }

}
