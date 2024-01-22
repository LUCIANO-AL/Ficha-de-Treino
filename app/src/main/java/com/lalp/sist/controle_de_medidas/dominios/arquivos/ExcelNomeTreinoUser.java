package com.lalp.sist.controle_de_medidas.dominios.arquivos;

import android.content.ContentValues;
import android.util.Log;

import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Usuario;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

public class ExcelNomeTreinoUser {

    public static final String Tablename = "usuario";
    public static final String id = "IDUSER";// 0 integer
    public static final String NOMEUSER = "NOMEUSER";// 1 text(String)
    public static final String TIPODETREINO = "TIPODETREINO";// 2 text(String)
    public static final String DATAINICIO = "DATAINICIO";// 3 text(String)
    public static final String DATAFIM = "DATAFIM";// 4 text(String)
    public  static Usuario usuario;


   public static void insertExcelToSqlite(DataBase dataBaseLista, Sheet sheet) {

        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            Row row = rit.next();

            ContentValues contentValues = new ContentValues();
            row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);

            contentValues.put(NOMEUSER, row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(TIPODETREINO, row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(DATAINICIO, row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(DATAFIM, row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());

            try {
                if (dataBaseLista.insertUser("usuario", contentValues) < 0) {
                    return;
                }
            } catch (Exception ex) {
                Log.d("Exception in importing", ex.getMessage().toString());
            }
        }
    }

}
