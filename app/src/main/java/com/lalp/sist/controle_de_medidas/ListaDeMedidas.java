package com.lalp.sist.controle_de_medidas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.databinding.ActListaDeMedidasBinding;
import com.lalp.sist.controle_de_medidas.databinding.ContentListaDeMedidasBinding;
import com.lalp.sist.controle_de_medidas.dominios.adapter.AdapterMedidas;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.Repo_Medida;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListaDeMedidas extends MenuDeTelas {

    private Repo_Medida repositorio_medida;
    private DataBase dataBaseLista;
    private SQLiteDatabase conexaorgmed;
    private Medidas medidasUpd;
    private AdapterMedidas adapterMedidas;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private ActListaDeMedidasBinding binding;
    private ContentListaDeMedidasBinding bindingListMedidas;

    private InterstitialAd interstitialAlterarMedidas;

    SharedPreferences sharedTutorialListDeMed;
    SharedPreferences.Editor editorTutorialListDeMed;

    public int contadorturialListDeMed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActListaDeMedidasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocationActivityTitle("Lista de Medidas");

        //binding.toolbar.setTitle("Lista de Medidas");
        //binding.toolbar.setSubtitle("De Olho nas Medidas");
        setSupportActionBar(binding.toolbar);

        bindingListMedidas = ContentListaDeMedidasBinding.inflate(getLayoutInflater());
        setContentView(bindingListMedidas.getRoot());

        AdRequest adRequest = new AdRequest.Builder().build();
        bindingListMedidas.idAdViewListMed.loadAd(adRequest);
        bindingListMedidas.idAdViewUpdateMedidas.loadAd(adRequest);

        InterstitialAd.load(this,getString(R.string.interstitial_id_add_medidas), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAlterarMedidas = interstitialAd;
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        //Toast.makeText(ListaDeMedidas.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        interstitialAlterarMedidas = null;
                    }
                });

        criarConexao();

        verificaParametroList();
        verificaParametroListCopia();

        bindingListMedidas.recyclerListMedidas.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        bindingListMedidas.recyclerListMedidas.setLayoutManager(linearLayoutManager);

        repositorio_medida = new Repo_Medida(conexaorgmed);

        bindingListMedidas.btnUpdateUpdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validaCamposAdd() == false)
                    try {

                        if (medidasUpd.ID >= 0) {

                            criarConexao();
                            repositorio_medida.alterarMedida(medidasUpd);
                            BuscarAdapter();
                            fechaConexao();

                            bindingListMedidas.constraintMedidaUpdt.setVisibility(View.GONE);
                            bindingListMedidas.listagemmedidas.setVisibility(View.VISIBLE);

                        }

                    } catch (SQLException ex) {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(null);
                        dlg.setTitle("Erro");
                        dlg.setMessage(ex.getMessage());
                        dlg.setNeutralButton(R.string.text_action_ok_conexao, null);
                        dlg.show();
                    }

                if (interstitialAlterarMedidas != null) {
                    interstitialAlterarMedidas.show(ListaDeMedidas.this);
                } else {
                    //Toast.makeText(ListaDeMedidas.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bindingListMedidas.edtDataListRGMedUpdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus ) {
                if (hasFocus) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
                        bindingListMedidas.edtDataListRGMedUpdt.setShowSoftInputOnFocus(false);
                    } else { // API 11-20
                        bindingListMedidas.edtDataListRGMedUpdt.setTextIsSelectable(true);
                    }
                    calendar = Calendar.getInstance();
                    int dia = calendar.get(Calendar.DAY_OF_MONTH);
                    int mes = calendar.get(Calendar.MONTH);
                    int ano = calendar.get(Calendar.YEAR);

                    datePickerDialog = new DatePickerDialog(ListaDeMedidas.this,0, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                            //   edtDataListDiv_D.setText(mDia + "/" + (mMes+1) + "/" + mAno);
                            bindingListMedidas.edtDataListRGMedUpdt.setText(mDia + "/" + (mMes+1) + "/" + mAno);
                        }
                    }, ano, mes, dia);

                    datePickerDialog.show();

                }

                bindingListMedidas.edtPesoUpdt.requestFocus();


            }
        });

        BuscarAdapter();

        if (adapterMedidas.getItemCount() > 0){
            sharedTutorialListDeMed = getSharedPreferences("LastSettingTutoListDeMed", Context.MODE_PRIVATE);
            editorTutorialListDeMed = sharedTutorialListDeMed.edit();

            contadorturialListDeMed = sharedTutorialListDeMed.getInt("tutorialListDeMed", 0);

            contadorturialListDeMed++;

            editorTutorialListDeMed.putInt("tutorialListDeMed", contadorturialListDeMed);
            editorTutorialListDeMed.commit();
        }

        fechaConexao();

        tutorialListDeMed();

    }

    public void tutorialListDeMed(){
        if(contadorturialListDeMed == 1){
            TapTargetView.showFor(this,
                    TapTarget.forView(bindingListMedidas.recyclerListMedidas,"Lista De Medidas","Copie suas medidas adicionadas. Para alterara as medidas, click sobre um item da lista para efetuar as alterações desejadas.")
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

    public void verificaParametroListCopia() {

        Bundle bundle = getIntent().getExtras();

        medidasUpd = new Medidas();

        if ((bundle != null) && (bundle.containsKey("COPIARMED"))) {

            medidasUpd = (Medidas) bundle.getSerializable("COPIARMED");

                if (medidasUpd.ID > 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setIcon(null)
                            .setTitle(" Criar copia")
                            .setMessage("Copiar medidas?")
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    criarConexao();

                                    dataBaseLista.copiarMedidas(medidasUpd.ID);

                                    BuscarAdapter();
                                    fechaConexao();

                                }
                            })
                            .setNegativeButton("NÃO", null)
                            .create()
                            .show();
                }

        }
    }

    public void BuscarAdapter(){
        List<Medidas> listmedidas = repositorio_medida.buscarTodos_Medidas();
        adapterMedidas = new AdapterMedidas(listmedidas);
        bindingListMedidas.recyclerListMedidas.setAdapter(adapterMedidas);
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

    public void verificaParametroList() {

        Bundle bundle = getIntent().getExtras();

        medidasUpd = new Medidas();

        if ((bundle != null) && (bundle.containsKey("MEDIDAS_UPDT"))) {

            bindingListMedidas.constraintMedidaUpdt.setVisibility(View.VISIBLE);

            medidasUpd = (Medidas) bundle.getSerializable("MEDIDAS_UPDT");

            bindingListMedidas.edtIdUpd.setText(String.valueOf(medidasUpd.ID));
            bindingListMedidas.edtNomeUserUpdt.setText(medidasUpd.NomeUser);
            bindingListMedidas.edtDataListRGMedUpdt.setText(medidasUpd.Datarg);
            bindingListMedidas.edtPantuDirUpdt.setText(medidasUpd.Panturdir);
            bindingListMedidas.edtPantuEsqUpdt.setText(medidasUpd.Panturesq);
            bindingListMedidas.edtCoxaDirUpdt.setText(medidasUpd.Coxadir);
            bindingListMedidas.edtCoxaEsqUpdt.setText(medidasUpd.Coxaesq);
            bindingListMedidas.edtCinturaUpdt.setText(medidasUpd.Cintura);
            bindingListMedidas.edtBumbumUpdt.setText(medidasUpd.Bumbum);
            bindingListMedidas.edtPeitoUpdt.setText(medidasUpd.Peito);
            bindingListMedidas.edtOmbrosUpdt.setText(medidasUpd.Ombros);
            bindingListMedidas.edtBicepsDirUpdt.setText(medidasUpd.Bicepsdir);
            bindingListMedidas.edtBicepsEsqUpdt.setText(medidasUpd.Bicepsesq);
            bindingListMedidas.edtBicepsDirContrUpdt.setText(medidasUpd.BicepsdirContr);
            bindingListMedidas.edtBicepsEsqContrUpdt.setText(medidasUpd.BicepsesqContr);
            bindingListMedidas.edtAntiBraDirUpdt.setText(medidasUpd.Antbracdir);
            bindingListMedidas.edtAntiBraEsqUpdt.setText(medidasUpd.Antbracesq);
            bindingListMedidas.edtAlturaUpdt.setText(medidasUpd.Altura);
            bindingListMedidas.edtPesoUpdt.setText(medidasUpd.Peso);

        }
    }

    private boolean validaCamposAdd() {

        boolean res = false;
        //é necessario declara a clase model dentro do metodo para o valores recebidos dos editextes sejam identificados (evitar valor nulo).
        medidasUpd = new Medidas();
        int ID = Integer.parseInt(bindingListMedidas.edtIdUpd.getText().toString());
        // int ID_RG = Integer.parseInt(edtId_Upd.getText().toString());

        String Nome =  bindingListMedidas.edtNomeUserUpdt.getText().toString();
        String Data =   bindingListMedidas.edtDataListRGMedUpdt.getText().toString();
        String Peso =  bindingListMedidas.edtPesoUpdt.getText().toString();
        String Altura =  bindingListMedidas.edtAlturaUpdt.getText().toString();
        String Peito =  bindingListMedidas.edtPeitoUpdt.getText().toString();
        String Ombros =  bindingListMedidas.edtOmbrosUpdt.getText().toString();
        String Bicepsdir = bindingListMedidas.edtBicepsDirUpdt.getText().toString();
        String Bicepsesq =  bindingListMedidas.edtBicepsEsqUpdt.getText().toString();
        String BicepsdirContr = bindingListMedidas.edtBicepsDirContrUpdt.getText().toString();
        String BicepsesqContr =  bindingListMedidas.edtBicepsEsqContrUpdt.getText().toString();
        String Antbracdir =  bindingListMedidas.edtAntiBraDirUpdt.getText().toString();
        String Antbracesq =  bindingListMedidas.edtAntiBraEsqUpdt.getText().toString();
        String Sintura =  bindingListMedidas.edtCinturaUpdt.getText().toString();
        String Bumbum =  bindingListMedidas.edtBumbumUpdt.getText().toString();
        String Coxadir =  bindingListMedidas.edtCoxaDirUpdt.getText().toString();
        String Coxaesq =  bindingListMedidas.edtCoxaEsqUpdt.getText().toString();
        String Panturdir = bindingListMedidas.edtPantuDirUpdt.getText().toString();
        String Panturesq = bindingListMedidas.edtPantuEsqUpdt.getText().toString();

        medidasUpd.ID = ID;
        medidasUpd.NomeUser = Nome;
        medidasUpd.Datarg = Data;
        medidasUpd.Peso = Peso;
        medidasUpd.Altura = Altura;
        medidasUpd.Peito = Peito;
        medidasUpd.Ombros = Ombros;
        medidasUpd.Bicepsdir = Bicepsdir;
        medidasUpd.Bicepsesq = Bicepsesq;
        medidasUpd.BicepsdirContr = BicepsdirContr;
        medidasUpd.BicepsesqContr = BicepsesqContr;
        medidasUpd.Antbracdir = Antbracdir;
        medidasUpd.Antbracesq = Antbracesq;
        medidasUpd.Cintura = Sintura;
        medidasUpd.Bumbum = Bumbum;
        medidasUpd.Coxadir = Coxadir;
        medidasUpd.Coxaesq = Coxaesq;
        medidasUpd.Panturdir = Panturdir;
        medidasUpd.Panturesq = Panturesq;

        if (res = isCampoVazio(Nome)) {
            bindingListMedidas.edtNomeUserUpdt.requestFocus();
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