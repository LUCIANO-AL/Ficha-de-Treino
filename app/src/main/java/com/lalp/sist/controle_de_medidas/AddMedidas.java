package com.lalp.sist.controle_de_medidas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.lalp.sist.controle_de_medidas.database.DataBase;

import com.lalp.sist.controle_de_medidas.databinding.ActAddMedidasBinding;
import com.lalp.sist.controle_de_medidas.databinding.ContentAddMedidasBinding;
import com.lalp.sist.controle_de_medidas.dominios.adapter.AdapterMedidas;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.Repo_Medida;


import android.text.TextUtils;
import android.view.View;

import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMedidas extends MenuDeTelas  {

    private Repo_Medida repositorio_medida;
    private DataBase dataBaseLista;
    private SQLiteDatabase conexaorgmed;
    private Medidas medidas, medidasUpd;
    private AdapterMedidas adapterMedidas;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private ActAddMedidasBinding binding;
    private ContentAddMedidasBinding bindingcontent;

    private InterstitialAd interstitialAddMedidas;

    SharedPreferences sharedTutorialAddMed;
    SharedPreferences.Editor editorTutorialAddMed;

    public int contadorturialAddMed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_medidas);

        binding = ActAddMedidasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocationActivityTitle("Registro de Medidas");

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //binding.toolbar.setTitle("Registro de Medidas");
        //binding.toolbar.setSubtitle("De Olho nas Medidas");
        //toolbar.setLogo(R.drawable.logo_barra);
        setSupportActionBar(binding.toolbar);

        bindingcontent = ContentAddMedidasBinding.inflate(getLayoutInflater());
        setContentView(bindingcontent.getRoot());

        AdRequest adRequest = new AdRequest.Builder().build();
        bindingcontent.idAdViewAddMed.loadAd(adRequest);

        InterstitialAd.load(this,getString(R.string.interstitial_id_add_medidas), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAddMedidas = interstitialAd;
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        //Toast.makeText(AddMedidas.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        interstitialAddMedidas = null;
                    }
                });

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateTimeString = dateFormat.format(new Date());
        bindingcontent.edtDataListRGMed.setText(currentDateTimeString);

       // VinculacaoFindViewById();

        sharedTutorialAddMed = getSharedPreferences("LastSettingTutoAddMed", Context.MODE_PRIVATE);
        editorTutorialAddMed = sharedTutorialAddMed.edit();

        contadorturialAddMed = sharedTutorialAddMed.getInt("tutorialAddMed", 0);

        contadorturialAddMed++;

        editorTutorialAddMed.putInt("tutorialAddMed", contadorturialAddMed);
        editorTutorialAddMed.commit();

        criarConexao();

        repositorio_medida = new Repo_Medida(conexaorgmed);

        bindingcontent.btnSalvarMedida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (validaCamposAdd() == false) try {

                    if (medidas.ID >= 0) {

                        criarConexao();
                        repositorio_medida.inserirMedidas(medidas);
                        fechaConexao();

                        Intent it = new Intent(AddMedidas.this, AddMedidas.class);
                        startActivity(it);

                    }

                } catch (SQLException ex) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(AddMedidas.this);
                    dlg.setTitle("Erro");
                    dlg.setMessage(ex.getMessage());
                    dlg.setNeutralButton("Erro de inserção das medidas", null);
                    dlg.show();
                }

                if (interstitialAddMedidas != null) {
                    interstitialAddMedidas.show(AddMedidas.this);
                } else {
                    //Toast.makeText(AddMedidas.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bindingcontent.edtDataListRGMed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus ) {
                if (hasFocus) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
                        bindingcontent.edtDataListRGMed.setShowSoftInputOnFocus(false);
                    } else { // API 11-20
                        bindingcontent.edtDataListRGMed.setTextIsSelectable(true);
                    }
                    calendar = Calendar.getInstance();
                    int dia = calendar.get(Calendar.DAY_OF_MONTH);
                    int mes = calendar.get(Calendar.MONTH);
                    int ano = calendar.get(Calendar.YEAR);

                    datePickerDialog = new DatePickerDialog(AddMedidas.this,0, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                            //   edtDataListDiv_D.setText(mDia + "/" + (mMes+1) + "/" + mAno);
                            bindingcontent.edtDataListRGMed.setText(mDia + "/" + (mMes+1) + "/" + mAno);
                        }
                    }, ano, mes, dia);

                    datePickerDialog.show();

                }

                bindingcontent.edtPeso.requestFocus();


            }
        });

        tutorialAddMedidas();

         fechaConexao();

    }
    public void tutorialAddMedidas(){
        if(contadorturialAddMed == 1){
            TapTargetView.showFor(this,
                    TapTarget.forView(bindingcontent.btnSalvarMedida,"Adicionar Medidas","Informe suas medidas e click no botão (Add Medidas).")
                            .outerCircleColor(R.color.cinza_escuro)
                            .outerCircleAlpha(0.96f)
                            .targetCircleColor(R.color.white)
                            .titleTextSize(20)
                            .titleTextColor(R.color.white)
                            .descriptionTextSize(13)
                            .descriptionTextColor(R.color.white)
                            .textColor(R.color.white)
                            .textTypeface(Typeface.SANS_SERIF)
                            .dimColor(R.color.white)
                            .drawShadow(true)
                            .cancelable(false)
                            .tintTarget(true)
                            .transparentTarget(true)
                            .targetRadius(60),
                    new TapTargetView.Listener(){

                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);

                        }
                    });

        }
    }

    public void limparEditText(){
        bindingcontent.edtNomeUser.setText("");
        bindingcontent.edtDataListRGMed.setText("");
        bindingcontent.edtPeso.setText("");
        bindingcontent.edtAltura.setText("");
        bindingcontent.edtPeito.setText("");
        bindingcontent.edtOmbros.setText("");
        bindingcontent.edtBicepsDir.setText("");
        bindingcontent.edtBicepsEsq.setText("");
        bindingcontent.edtBicepsDirContr.setText("");
        bindingcontent.edtBicepsEsqContr.setText("");
        bindingcontent.edtAntiBraDir.setText("");
        bindingcontent.edtAntiBraEsq.setText("");
        bindingcontent.edtCintura.setText("");
        bindingcontent.edtBumbum.setText("");
        bindingcontent.edtCoxaDir.setText("");
        bindingcontent.edtCoxaEsq.setText("");
        bindingcontent.edtPantuDir.setText("");
        bindingcontent.edtPantuEsq.setText("");
    }

    private void criarConexao() {
        try {
            dataBaseLista = new DataBase(this);
            conexaorgmed = dataBaseLista.getWritableDatabase();
            repositorio_medida = new Repo_Medida(conexaorgmed);

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
            dataBaseLista = new DataBase(this);
            conexaorgmed = dataBaseLista.getWritableDatabase();
            repositorio_medida = new Repo_Medida(conexaorgmed);
            dataBaseLista.close();

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.text_action_ok_conexao, null);
            dlg.show();
        }
    }

    private boolean validaCamposAdd() {

        boolean res = false;

        //é necessario declara a clase model dentro do metodo para o valores recebidos dos editextes sejam identificados (evitar valor nulo).
        medidas = new Medidas();

        // int ID = Integer.parseInt(edtId.getText().toString());
        // int ID_RG = Integer.parseInt(edtId_Upd.getText().toString());
        String Nome =  bindingcontent.edtNomeUser.getText().toString();
        String Data =   bindingcontent.edtDataListRGMed.getText().toString();
        String Peso =  bindingcontent.edtPeso.getText().toString();
        String Altura =  bindingcontent.edtAltura.getText().toString();
        String Peito =  bindingcontent.edtPeito.getText().toString();
        String Ombros =  bindingcontent.edtOmbros.getText().toString();
        String Bicepsdir = bindingcontent.edtBicepsDir.getText().toString();
        String Bicepsesq =  bindingcontent.edtBicepsEsq.getText().toString();
        String BicepsdirContr = bindingcontent.edtBicepsDirContr.getText().toString();
        String BicepsesqContr =  bindingcontent.edtBicepsEsqContr.getText().toString();
        String Antbracdir =  bindingcontent.edtAntiBraDir.getText().toString();
        String Antbracesq =  bindingcontent.edtAntiBraEsq.getText().toString();
        String Sintura =  bindingcontent.edtCintura.getText().toString();
        String Bumbum =  bindingcontent.edtBumbum.getText().toString();
        String Coxadir =  bindingcontent.edtCoxaDir.getText().toString();
        String Coxaesq =  bindingcontent.edtCoxaEsq.getText().toString();
        String Panturdir = bindingcontent.edtPantuDir.getText().toString();
        String Panturesq = bindingcontent.edtPantuEsq.getText().toString();

        // medidas.ID_RG = ID_RG;
        medidas.NomeUser = Nome;
        medidas.Datarg = Data;
        medidas.Peso = Peso;
        medidas.Altura = Altura;
        medidas.Peito = Peito;
        medidas.Ombros = Ombros;
        medidas.Bicepsdir = Bicepsdir;
        medidas.Bicepsesq = Bicepsesq;
        medidas.BicepsdirContr = BicepsdirContr;
        medidas.BicepsesqContr = BicepsesqContr;
        medidas.Antbracdir = Antbracdir;
        medidas.Antbracesq = Antbracesq;
        medidas.Cintura = Sintura;
        medidas.Bumbum = Bumbum;
        medidas.Coxadir = Coxadir;
        medidas.Coxaesq = Coxaesq;
        medidas.Panturdir = Panturdir;
        medidas.Panturesq = Panturesq;

        if (res = isCampoVazio(Nome)) {
            bindingcontent.edtNomeUser.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.lista_message_validacampo);
            dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
            dlg.show();
        }
        return res;
    }

    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }



}











