package com.lalp.sist.controle_de_medidas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.databinding.ActListaDeUsuariosBinding;
import com.lalp.sist.controle_de_medidas.databinding.ContentListaDeUsuariosBinding;
import com.lalp.sist.controle_de_medidas.dominios.adapter.AdapterMedidas;
import com.lalp.sist.controle_de_medidas.dominios.adapter.AdapterUser;
import com.lalp.sist.controle_de_medidas.dominios.arquivos.ExcelExerciciosTreino;
import com.lalp.sist.controle_de_medidas.dominios.arquivos.ExcelMusculosTreino;
import com.lalp.sist.controle_de_medidas.dominios.arquivos.ExcelNomeTreinoUser;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Usuario;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.RepoMusculo;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.Repo_Medida;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.Repo_Treino;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.Repo_User;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListaDeUsuarios extends MenuDeTelas {
    private ActListaDeUsuariosBinding binding;
    private ContentListaDeUsuariosBinding bindinglistuser;
    private DataBase dataBase;
    private SQLiteDatabase conexaoruser;
    private Repo_User repo_user;
    private RepoMusculo repoMusculo;
    private Repo_Treino repo_treino;
    private AdapterUser adapterUser;
    private Usuario usuario;

    private ExcelNomeTreinoUser excelNomeTreinoUser;
    private ExcelMusculosTreino excelMusculosTreino;
    private ExcelExerciciosTreino excelExerciciosTreino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lista_de_usuarios);

        binding = ActListaDeUsuariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        bindinglistuser = ContentListaDeUsuariosBinding.inflate(getLayoutInflater());
        setContentView(bindinglistuser.getRoot());

        AdRequest adRequest = new AdRequest.Builder().build();
        bindinglistuser.idAdViewListUser.loadAd(adRequest);

        criarConexao();

        bindinglistuser.recyclerListUser.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        bindinglistuser.recyclerListUser.setLayoutManager(linearLayoutManager);

        repo_user = new Repo_User(conexaoruser);

        BuscarAdapter();

        permissaoCompartilhar();

        fechaConexao();

    }

    //Metodos para ler e importar arquivos recebido pelo zap//////////////////////////////////////////////////////////////////////////////
    public void importarTreino(View view) {

        pedirPermissaoParaImportarTreino();

       /* if (interstitial_import_compras != null) {
            interstitial_import_compras.show(Add_RelacaoList.this);
            ReadExcelFileNomeListZero2();
            ReadExcelFileNomeList2();
            criarConexao();
            BuscarAdapter();
            fechaConexao();

        } else {*/
        lerPrimeiroArquivoExcelCriado();
        lerSegundoArquivoExcelOuMaisCriado();
        criarConexao();
        BuscarAdapter();
        fechaConexao();

        // }

    }

    public boolean pedirPermissaoParaImportarTreino() {
        // PERMISOS PARA ANDROID 6 O SUPERIOR
        if (ContextCompat.checkSelfPermission(ListaDeUsuarios.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            AlertDialog.Builder builder = new AlertDialog.Builder(ListaDeUsuarios.this);
            builder.setTitle("Aviso")
                    .setMessage("Se as permissões de acesso foram concedidas, tente importar novamente.")
                    .setNegativeButton("OK", null)
                    .show();

            ActivityCompat.requestPermissions(ListaDeUsuarios.this, new String[]
                            {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);
        }
        return false;
    }

    public void lerPrimeiroArquivoExcelCriado() {

        File root = new File(Environment.getExternalStorageDirectory() + "/WhatsApp/Media/WhatsApp Documents");
        File root2 = new File(Environment.getExternalStorageDirectory() + "/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Documents");

        File pasta = new File(root.getAbsolutePath());
        File pasta2 = new File(root2.getAbsolutePath());


        File xlsxFileAddresszeroNomeUserTreino = new File(pasta, "NomeUserTreino.xlsx");
        File xlsxFileAddreszeroMusculos = new File(pasta, "MusculosDoTreino.xlsx");
        File xlsxFileAddreszeroExercicios = new File(pasta, "ExerciciosDoTreino.xlsx");

        File xlsxFileAddresszeroNomeUserTreinoRoot2 = new File(pasta2, "NomeUserTreino.xlsx");
        File xlsxFileAddreszeroMusculosRoot2 = new File(pasta2, "MusculosDoTreino.xlsx");
        File xlsxFileAddreszeroExerciciosRoot2 = new File(pasta2, "ExerciciosDoTreino.xlsx");

        if (root.exists()) {
            if (xlsxFileAddresszeroNomeUserTreino.exists()) {

                try {
                    InputStream inStream;
                    Workbook wb = null;

                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddresszeroNomeUserTreino));
                        wb = new XSSFWorkbook(inStream);

                        inStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexao();

                    excelNomeTreinoUser.insertExcelToSqlite(dbAdapter, sheet1);

                    fechaConexao();

                    xlsxFileAddresszeroNomeUserTreino.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    InputStream inStream;
                    Workbook wb = null;


                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddreszeroMusculos));
                        wb = new XSSFWorkbook(inStream);

                        inStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexaoTabMusculos();

                    excelMusculosTreino.insertExcelToSqliteMusculo(dbAdapter, sheet1);

                    fechaConexaoTabMusculos();

                    xlsxFileAddreszeroMusculos.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    InputStream inStream;
                    Workbook wb = null;

                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddreszeroExercicios));
                        wb = new XSSFWorkbook(inStream);

                        inStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexaoTabExercicios();

                    excelExerciciosTreino.insertExcelToSqliExercicio(dbAdapter, sheet1);

                    fechaConexaoTabExercicios();

                    xlsxFileAddreszeroMusculos.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (!xlsxFileAddresszeroNomeUserTreino.exists()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("AVISO")
                        .setMessage(R.string.msg_arquivo_não_existe)
                        .setNegativeButton("VOLTA", null)
                        .show();
            }
        } else if (root2.exists()) {
            if (xlsxFileAddresszeroNomeUserTreinoRoot2.exists()) {

                try {
                    InputStream inStream;
                    Workbook wb = null;

                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddresszeroNomeUserTreinoRoot2));
                        wb = new XSSFWorkbook(inStream);

                        inStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexao();

                    excelNomeTreinoUser.insertExcelToSqlite(dbAdapter, sheet1);

                    fechaConexao();

                    xlsxFileAddresszeroNomeUserTreinoRoot2.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    InputStream inStream;
                    Workbook wb = null;


                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddreszeroMusculosRoot2));
                        wb = new XSSFWorkbook(inStream);
                        inStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexaoTabMusculos();

                    excelMusculosTreino.insertExcelToSqliteMusculo(dbAdapter, sheet1);


                    fechaConexaoTabMusculos();

                    //dbAdapter.close();

                    xlsxFileAddreszeroMusculosRoot2.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    InputStream inStream;
                    Workbook wb = null;


                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddreszeroExerciciosRoot2));
                        wb = new XSSFWorkbook(inStream);
                        inStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexaoTabExercicios();

                    excelExerciciosTreino.insertExcelToSqliExercicio(dbAdapter, sheet1);


                    fechaConexaoTabExercicios();

                    //dbAdapter.close();

                    xlsxFileAddreszeroExerciciosRoot2.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (!xlsxFileAddresszeroNomeUserTreinoRoot2.exists()) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("AVISO")
                        .setMessage(R.string.msg_arquivo_não_existe)
                        .setNegativeButton("VOLTA", null)
                        .show();
            }

        }

    }

    public void lerSegundoArquivoExcelOuMaisCriado() {

        File root = new File(Environment.getExternalStorageDirectory() + "/WhatsApp/Media/WhatsApp Documents");
        File root2 = new File(Environment.getExternalStorageDirectory() + "/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Documents");


        File pasta = new File(root.getAbsolutePath());
        // pasta.mkdirs();

        File pasta2 = new File(root2.getAbsolutePath());
        //pasta2.mkdirs();

        for (int i = 0; i <= 100; i++) {

            File xlsxFileAddresszeroNomeUserTreino = new File(pasta, "NomeUserTreino-" + i + ".xlsx");
            File xlsxFileAddreszeroMusculos = new File(pasta, "MusculosDoTreino-" + i + ".xlsx");
            File xlsxFileAddreszeroExercicios = new File(pasta, "ExerciciosDoTreino-" + i + ".xlsx");

            File xlsxFileAddresszeroNomeUserTreinoRoot2 = new File(pasta2, "NomeUserTreino-" + i + ".xlsx");
            File xlsxFileAddreszeroMusculosRoot2 = new File(pasta2, "MusculosDoTreino-" + i + ".xlsx");
            File xlsxFileAddreszeroExerciciosRoot2 = new File(pasta2, "ExerciciosDoTreino-" + i + ".xlsx");

            if (root.exists()) {
                if(xlsxFileAddresszeroNomeUserTreino.exists()){
                    try {
                        InputStream inStream;
                        Workbook wb = null;

                        try {
                            inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddresszeroNomeUserTreino));
                            wb = new XSSFWorkbook(inStream);

                            inStream.close();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        DataBase dbAdapter = new DataBase(this);
                        Sheet sheet1 = wb.getSheetAt(0);

                        criarConexao();

                        excelNomeTreinoUser.insertExcelToSqlite(dbAdapter, sheet1);

                        fechaConexao();

                        //dbAdapter.close();

                        xlsxFileAddresszeroNomeUserTreino.delete();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        InputStream inStream;
                        Workbook wb = null;

                        try {
                            inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddreszeroMusculos));

                            wb = new XSSFWorkbook(inStream);

                            inStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        DataBase dbAdapter = new DataBase(this);
                        Sheet sheet1 = wb.getSheetAt(0);

                        criarConexaoTabMusculos();

                        excelMusculosTreino.insertExcelToSqliteMusculo(dbAdapter, sheet1);

                        fechaConexaoTabMusculos();

                        //dbAdapter.close();

                        xlsxFileAddreszeroMusculos.delete();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        InputStream inStream;
                        Workbook wb = null;

                        try {
                            inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddreszeroExercicios));

                            wb = new XSSFWorkbook(inStream);

                            inStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        DataBase dbAdapter = new DataBase(this);
                        Sheet sheet1 = wb.getSheetAt(0);

                        criarConexaoTabExercicios();

                        excelExerciciosTreino.insertExcelToSqliExercicio(dbAdapter, sheet1);

                        fechaConexaoTabExercicios();

                        //dbAdapter.close();

                        xlsxFileAddreszeroExercicios.delete();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else if (root2.exists()) {
                if(xlsxFileAddresszeroNomeUserTreinoRoot2.exists()){
                try {
                    InputStream inStream;
                    Workbook wb = null;

                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddresszeroNomeUserTreinoRoot2));
                        wb = new XSSFWorkbook(inStream);

                        inStream.close();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexao();

                    excelNomeTreinoUser.insertExcelToSqlite(dbAdapter, sheet1);

                    fechaConexao();

                    xlsxFileAddresszeroNomeUserTreinoRoot2.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    InputStream inStream;
                    Workbook wb = null;

                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddreszeroMusculosRoot2));

                        wb = new XSSFWorkbook(inStream);

                        inStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexaoTabMusculos();

                    excelMusculosTreino.insertExcelToSqliteMusculo(dbAdapter, sheet1);

                    fechaConexaoTabMusculos();

                    xlsxFileAddreszeroMusculosRoot2.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    InputStream inStream;
                    Workbook wb = null;

                    try {
                        inStream = getContentResolver().openInputStream(Uri.fromFile(xlsxFileAddreszeroExerciciosRoot2));

                        wb = new XSSFWorkbook(inStream);

                        inStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataBase dbAdapter = new DataBase(this);
                    Sheet sheet1 = wb.getSheetAt(0);

                    criarConexaoTabExercicios();

                    excelExerciciosTreino.insertExcelToSqliExercicio(dbAdapter, sheet1);

                    fechaConexaoTabExercicios();

                    xlsxFileAddreszeroExerciciosRoot2.delete();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            }


        }

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Metodos para enviar treino via WhatSap//////////////////////////////////////////////////////////////////////////////
    public void permissaoCompartilhar() {

        Bundle bundle = getIntent().getExtras();

        usuario = new Usuario();

        if ((bundle != null) && (bundle.containsKey("COMPARTILHA_TREINO_LISTA"))) {

            usuario = (Usuario) bundle.getSerializable("COMPARTILHA_TREINO_LISTA");

            pedirPermissaoParaCompartilharTreino();

        }

    }

    private void compartilhaListarTreino() {

        final File pasta = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));

        criarConexaoTabMusculos();
        criarConexaoTabExercicios();

        final String nomeuser = "NomeUserTreino";

        final String musculostreino = "MusculosDoTreino";

        final String exerciciostreino = "ExerciciosDoTreino";

        repo_user.geraPlanilhaNomeUser(usuario.IDUSER);

        repoMusculo.geraPlanilaMusculos(usuario.IDUSER);

        repo_treino.geraPlanilaExercicios(usuario.IDUSER);

        fechaConexaoTabMusculos();
        fechaConexaoTabExercicios();

        AlertDialog.Builder enviaEmail = new AlertDialog.Builder(this);
        enviaEmail.setTitle("Compartilha lista de compras via WhatSapp");
        enviaEmail.setCancelable(false);

        LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        enviaEmail.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                enviarEmail(pasta, nomeuser, musculostreino, exerciciostreino);

                   /* if (lista_interstitial != null) {
                        lista_interstitial.show(Add_RelacaoList.this);
                    } else {
                        enviarEmail(pasta,nomeLista, nomeArquivo);
                    }*/

                    /*lista_interstitial.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                            enviarEmail(pasta,nomeLista, nomeArquivo);
                        }
                    });*/
            }

        });
        enviaEmail.setNegativeButton("Cancelar", null);
        enviaEmail.create().show();

    }

    public void enviarEmail(File pasta, String nomeuser, String musculostreino, String exerciciostreino) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setPackage("com.whatsapp");
        // intent.setPackage("com.whatsapp business");
        // intent.setData(Uri.parse("mailto:"));
        //intent.putExtra(Intent.EXTRA_SUBJECT, "ListSoma Lista de Compras" );
        intent.setType("text/text");

        Uri uri1 = Uri.parse("file://" + pasta + "/" + nomeuser + ".xlsx");
        Uri uri2 = Uri.parse("file://" + pasta + "/" + musculostreino + ".xlsx");
        Uri uri3 = Uri.parse("file://" + pasta + "/" + exerciciostreino + ".xlsx");

        ArrayList<Uri> arrayList = new ArrayList<Uri>();
        arrayList.add(uri1);
        arrayList.add(uri2);
        arrayList.add(uri3);

        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, arrayList);

        startActivity(intent);
    }

    public boolean pedirPermissaoParaCompartilharTreino() {
        // PERMISOS PARA ANDROID 6 O SUPERIOR
        if (ContextCompat.checkSelfPermission(ListaDeUsuarios.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            AlertDialog.Builder builder = new AlertDialog.Builder(ListaDeUsuarios.this);
            builder.setTitle("Aviso")
                    .setMessage("Se as permissões de acesso foram concedidas, tente compartilhar novamente.")
                    .setNegativeButton("OK", null)
                    .show();

            ActivityCompat.requestPermissions(ListaDeUsuarios.this, new String[]
                            {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);

        } else {

            compartilhaListarTreino();
        }

        return false;


    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void BuscarAdapter() {
        List<Usuario> listuser = repo_user.buscarTodosUser();
        adapterUser = new AdapterUser(listuser);
        bindinglistuser.recyclerListUser.setAdapter(adapterUser);
    }

    private void criarConexao() {
        try {

            dataBase = new DataBase(this);
            conexaoruser = dataBase.getWritableDatabase();
            repo_user = new Repo_User(conexaoruser);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Erro", null);
            dlg.show();
        }
    }

    private void fechaConexao() {
        try {
            dataBase = new DataBase(this);
            conexaoruser = dataBase.getWritableDatabase();
            repo_user = new Repo_User(conexaoruser);
            dataBase.close();

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.text_action_ok_conexao, null);
            dlg.show();
        }
    }

    private void criarConexaoTabMusculos() {
        try {

            dataBase = new DataBase(this);
            conexaoruser = dataBase.getWritableDatabase();
            repoMusculo = new RepoMusculo(conexaoruser);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Erro", null);
            dlg.show();
        }
    }

    private void fechaConexaoTabMusculos() {
        try {
            dataBase = new DataBase(this);
            conexaoruser = dataBase.getWritableDatabase();
            repoMusculo = new RepoMusculo(conexaoruser);
            dataBase.close();

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.text_action_ok_conexao, null);
            dlg.show();
        }
    }

    private void criarConexaoTabExercicios() {
        try {

            dataBase = new DataBase(this);
            conexaoruser = dataBase.getWritableDatabase();
            repo_treino = new Repo_Treino(conexaoruser);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Erro", null);
            dlg.show();
        }
    }

    private void fechaConexaoTabExercicios() {
        try {
            dataBase = new DataBase(this);
            conexaoruser = dataBase.getWritableDatabase();
            repo_treino = new Repo_Treino(conexaoruser);
            dataBase.close();

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.text_action_ok_conexao, null);
            dlg.show();
        }
    }

}