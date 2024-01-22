package com.lalp.sist.controle_de_medidas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.lalp.sist.controle_de_medidas.database.DataBase;

import com.lalp.sist.controle_de_medidas.databinding.ActAddMedidasBinding;

import com.lalp.sist.controle_de_medidas.databinding.ActTreinosBinding;

import com.lalp.sist.controle_de_medidas.databinding.ContentTreinosBinding;
import com.lalp.sist.controle_de_medidas.dominios.adapter.AdapterRecycleExercicio;
import com.lalp.sist.controle_de_medidas.dominios.adapter.AdapterRecycleMusculo;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Exercicio;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Musculo;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Usuario;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.RepoMusculo;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.Repo_Treino;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.Repo_User;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.lalp.sist.controle_de_medidas.R.id.btnVoltar;
import static com.lalp.sist.controle_de_medidas.R.id.idAddNovoTreino;
import static com.lalp.sist.controle_de_medidas.R.id.idUpdateMusc;

public class Treinos extends MenuDeTelas {
    private static Repo_Treino repo_treino;
    private static DataBase dataBaseLista;
    private static SQLiteDatabase conexaotreino;
    private Repo_User repo_user;
    private Exercicio exercicio;
    private Musculo musculo;
    private Usuario usuario;
    private List<Exercicio> exercicioList;
    private List<Musculo> musculoList;
    private static AdapterRecycleMusculo adapterRecycleMusculo;
    public AdapterRecycleExercicio adapterRecycleExercicio;
    String[] dias_da_semana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
    String[] ordemmusc = {"1", "2", "3", "4", "5", "6"};
    SharedPreferences LastSelect, LastSelectSpinnerUser, sharedTutorial, sharedTutorial2;
    public static SharedPreferences.Editor editorspinner, editorspinnerUser, editorTutorial, editorTutorial2;
    public static int contadorturial = 0, contadorturial2 = 0;
    public int sequencia = 1;
    private InterstitialAd interstitialDesmarcar;
    public static Spinner spinnerDiaSemana, spinnerMusculoExerc, spinnerUser, spinnerMuscDoTreinoRecycle;
    public static Spinner spinnerDiaSemanaMuscUpdt, spinnerPosicaoMuscUdpt, spinnerMusculoExercUpdt;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    public EditText edtDataInicial, edtDataFinal, edtNomeUser, edtTipoDeTreino, edtSequencia;
    private EditText edtPosicao, edtMusculo, edtRepeticoes, edtMusculoUpdt;
    private EditText edtRepeticoes2, edtRepeticoes3, edtRepeticoes4, edtRepeticoes5, edtRepeticoes6, edtRepeticoes7,
            edtRepeticoes8, edtRepeticoes9, edtRepeticoes10;
    private EditText edtCarga, edtCarga2, edtCarga3, edtCarga4, edtCarga5, edtCarga6, edtCarga7, edtCarga8, edtCarga9, edtCarga10;
    private EditText edtExercicio, edtSeries, editDiaDaSemanaMusc;
    private ImageView btnDataInicial, btnDataFinal;
    private AdView idAdViewTreino;
    public static TextView txtDiaDaSemanaExerc, txtMaiorPosicao, txtArrastaProLado, txtIdMuscExerc, txtIdUserExerc;
    public TextView txtQtdMusculo1, txtIDMuscUpdt, txtIDUserUpdt, txtPosicaoUpdt, txtDiaDaSemnaUpdt;
    private CardView btnAddMusc, btnAddExercicio, btnDesmarcarChecks;
    private ConstraintLayout constraintLayout333, constraintLayoutAddExercicio;
    private ConstraintLayout constraintLayoutRecycle1, ContrLaytEditCompr;
    private ConstraintLayout constrLayRepCarg2, constrLayRepCarg3, constrLayRepCarg4, constrLayRepCarg5,
            constrLayRepCarg6, constrLayRepCarg7, constrLayRepCarg8, constrLayRepCarg9, constrLayRepCarg10;
    private Button btnExibirForm, btnMostrarAddTreino;
    private CardView btnExibirTemp;
    public static Button btnRefreshAdpapter;
    private CheckBox checkCargProg;
    private LinearLayout linearLayout2, linearLayout3, linearLayout4, linearNovoTreino;
    public static RecyclerView recycleViewMusculos;
    String musc;
    String musc_recycle;
    String compareValue, compareValueDiaSemUpdt;
    private ActTreinosBinding binding;
    public static Activity activity;

    //Temporizaodor
    private CountDownTimer timer;
    int startTime;
    int hoursLeft, minutesLeft, secondsLeft;
    int totalSecondsLeft;
    boolean isPaused = false;
    SharedPreferences sharedpreferences, sharedPropaganda;
    SharedPreferences.Editor editorPropaganda;
    public static final String mypreference = "mypref";
    public static final String SegundoPref = "segundoprefKey";
    public static final String MinPref = "minprefKey";
    public static final String HorasPref = "horasprefKey";

    public MediaPlayer player;
    public int contadorpropaganda = 0;
    InterstitialAd interstitialTemporizador, interstitialTemporizador2, interstitialTemporizador3, interstitialTemporizador4, interstitialTemporizador5;

    private EditText seconds, minutes, hours;
    private Button startButton, pauseButton, btnCancelarTemp, resetButton;
    public static Button btnIniciarTemp;
    private ConstraintLayout idConstraintLayoutExecTempo, idConstraintLayoutEditarTempo;
    private TextView countDownText, hoursLeftText, minutesLeftText, secondsLeftText;
    private CardView btnVoltarTemp;

    int ativarbtntempor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_treinos);

        binding = ActTreinosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        findViewById(idAddNovoTreino).setVisibility(View.GONE);

        referenciaXML();

        //Propagandas inicialização
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        idAdViewTreino.loadAd(adRequest);

        InterstitialAd.load(this, getString(R.string.intersticial_desmarcartodos), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialDesmarcar = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        //Toast.makeText(Treinos.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        interstitialDesmarcar = null;
                    }
                });

        //Recupera e salva arquivos salvos no sharedPreferences

        LastSelect = getSharedPreferences("LastSetting", Context.MODE_PRIVATE);
        editorspinner = LastSelect.edit();

        final int LastClick = LastSelect.getInt("LastClick", 0);

        LastSelectSpinnerUser= getSharedPreferences("LastSettingSpinnerUser", Context.MODE_PRIVATE);
        editorspinnerUser = LastSelectSpinnerUser.edit();

        final int LastClickUser = LastSelectSpinnerUser.getInt("LastClickUser", 0);

        sharedTutorial = getSharedPreferences("LastSettingTuto", Context.MODE_PRIVATE);
        editorTutorial = sharedTutorial.edit();

        contadorturial = sharedTutorial.getInt("tutorial", 0);

        contadorturial++;

        editorTutorial.putInt("tutorial", contadorturial);
        editorTutorial.commit();

        //Data da tela add treino

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateTimeString = dateFormat.format(new Date());
        edtDataInicial.setText(currentDateTimeString);
        btnDataInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int ano = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(Treinos.this, 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                        edtDataInicial.setText(mDia + "/" + (mMes + 1) + "/" + mAno);
                        //edtDataList.setText((mMes + 1) + "/" + mAno);
                    }
                }, ano, mes, dia);

                datePickerDialog.show();

            }
        });

        btnDataFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int ano = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(Treinos.this, 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                        edtDataFinal.setText(mDia + "/" + (mMes + 1) + "/" + mAno);
                        //edtDataList.setText((mMes + 1) + "/" + mAno);
                    }
                }, ano, mes, dia);

                datePickerDialog.show();

            }
        });

        edtDataInicial.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    calendar = Calendar.getInstance();
                    int dia = calendar.get(Calendar.DAY_OF_MONTH);
                    int mes = calendar.get(Calendar.MONTH);
                    int ano = calendar.get(Calendar.YEAR);

                    datePickerDialog = new DatePickerDialog(Treinos.this, 0, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                            edtDataInicial.setText(mDia + "/" + (mMes + 1) + "/" + mAno);
                            //edtDataList.setText((mMes + 1) + "/" + mAno);
                        }
                    }, ano, mes, dia);

                    datePickerDialog.show();
                }
            }
        });

        edtDataFinal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    calendar = Calendar.getInstance();
                    int dia = calendar.get(Calendar.DAY_OF_MONTH);
                    int mes = calendar.get(Calendar.MONTH);
                    int ano = calendar.get(Calendar.YEAR);

                    datePickerDialog = new DatePickerDialog(Treinos.this, 0, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                            edtDataFinal.setText(mDia + "/" + (mMes + 1) + "/" + mAno);
                            //edtDataList.setText((mMes + 1) + "/" + mAno);
                        }
                    }, ano, mes, dia);

                    datePickerDialog.show();
                }
            }
        });

        criarConexao();

        repo_treino = new Repo_Treino(conexaotreino);

        //Carregar os recycles
        recycleViewMusculos.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerMusc = new LinearLayoutManager(this);
        recycleViewMusculos.setLayoutManager(linearLayoutManagerMusc);

        //Spiner para apresentar os usuarios e os treinos cadastrados
        ArrayAdapter<String> adapter_spinner_TreinoUser = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                dataBaseLista.buscarNomeUserSippnerTreino());
        spinnerUser.setAdapter(adapter_spinner_TreinoUser);
        spinnerUser.setSelection(LastClickUser);

        spinnerUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                criarConexao();
                setarTxtIds();
                popularSpinnerMuscRecycle();
                BuscarAdapterMusculo();
                popularSpinnerMusc();

                fechaConexao();

                editorspinnerUser.putInt("LastClickUser", position).commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner dos dias da semana
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, dias_da_semana);
        spinnerDiaSemana.setAdapter(adapter);
        spinnerDiaSemana.setSelection(LastClick);

        spinnerDiaSemana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editorspinner.putInt("LastClick", position).commit();

                String tela = spinnerDiaSemana.getSelectedItem().toString();

                String tela2 = parent.getItemAtPosition(position).toString();

                txtDiaDaSemanaExerc.setText(tela);

                switch (tela) {

                    case "Segunda":
                        criarConexao();
                        setarTxtIds();
                        popularSpinnerMuscRecycle();
                        BuscarAdapterMusculo();
                        popularSpinnerMusc();
                        fechaConexao();
                        break;
                    case "Terça":
                        criarConexao();
                        setarTxtIds();
                        popularSpinnerMuscRecycle();
                        BuscarAdapterMusculo();
                        popularSpinnerMusc();
                        fechaConexao();
                        break;
                    case "Quarta":
                        criarConexao();
                        setarTxtIds();
                        popularSpinnerMuscRecycle();
                        BuscarAdapterMusculo();
                        popularSpinnerMusc();
                        fechaConexao();
                        break;
                    case "Quinta":
                        criarConexao();
                        setarTxtIds();
                        popularSpinnerMuscRecycle();
                        BuscarAdapterMusculo();
                        popularSpinnerMusc();
                        fechaConexao();
                        break;
                    case "Sexta":
                        criarConexao();
                        setarTxtIds();
                        popularSpinnerMuscRecycle();
                        BuscarAdapterMusculo();
                        popularSpinnerMusc();
                        fechaConexao();
                        break;
                    case "Sábado":
                        criarConexao();
                        setarTxtIds();
                        popularSpinnerMuscRecycle();
                        BuscarAdapterMusculo();
                        popularSpinnerMusc();
                        fechaConexao();
                        break;
                    case "Domingo":
                        criarConexao();
                        setarTxtIds();
                        popularSpinnerMuscRecycle();
                        BuscarAdapterMusculo();
                        popularSpinnerMusc();
                        fechaConexao();
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        popularSpinnerMuscRecycle();

        spinnerMuscDoTreinoRecycle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                musc_recycle = parent.getItemAtPosition(position).toString();

                criarConexao();
                setarTxtIds();
                BuscarAdapterMusculo();
                fechaConexao();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner para mostrar os musculos
        popularSpinnerMusc();

        spinnerMusculoExerc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                musc = parent.getItemAtPosition(position).toString();

                criarConexao();
                setarTxtIds();
                popularSpinnerMuscRecycle();
                BuscarAdapterMusculo();
                fechaConexao();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setarInfoParaTelaUpdtMusc();

        //Add Musculo alvo do Treino
        btnAddMusc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                int posicao = Integer.parseInt(dataBaseLista.selectNumLinhasMusc(spinnerDiaSemana.getSelectedItem().toString(),
                        txtIdUserExerc.getText().toString()));
                posicao++;

                edtPosicao.setText(String.valueOf(posicao));

                if (validaCamposAdd_Musculo() == false) try {

                    if (musculo.ID >= 0) {

                        criarConexao();

                        repo_treino.inserirMusculo(musculo);

                        popularSpinnerMuscRecycle();
                        BuscarAdapterMusculo();
                        popularSpinnerMusc();

                        setarTxtIds();
                        fechaConexao();

                        edtMusculo.setText("");

                        tutorialAddExercicio();

                    }

                } catch (SQLException ex) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Treinos.this);
                    dlg.setTitle("Erro");
                    dlg.setMessage(ex.getMessage());
                    dlg.setNeutralButton("Erro de inserção das medidas", null);
                    dlg.show();
                }


            }
        });

        //Caso a quantidade de series não seja informada o campo edtRepeticoes quanha foco
        edtRepeticoes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    if (validaCampoSerie() == false) {

                        visualizarRepCarg();

                    }

                   /* String series = bindingtreino.edtSeries.getText().toString();

                    if(series == "0") {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(Treinos.this);
                        dlg.setTitle(R.string.title_aviso);
                        dlg.setMessage(R.string.lista_message_validacampo_nomeserie);
                        dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
                        dlg.show();

                        bindingtreino.edtSeries.requestFocus();
                    }
                    else {

                        visualizarRepCarg();

                    }
                   */

                }

            }
        });

        //Add exercicio
        btnAddExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                int ordem = Integer.parseInt(dataBaseLista.selectNumLinhasExerc(spinnerDiaSemana.getSelectedItem().toString(),
                        txtIdUserExerc.getText().toString(), txtIdMuscExerc.getText().toString()));
                ordem++;

                edtSequencia.setText(String.valueOf(ordem));

                if (validaCamposAddExercicio() == false) try {

                    if (exercicio.ID >= 0) {
                        criarConexao();
                        repo_treino.repo_inserirExercicio(exercicio);
                        BuscarAdapterMusculo();
                        fechaConexao();

                        edtExercicio.setText("");
                        edtSeries.setText("");

                        edtRepeticoes.setText("");
                        edtRepeticoes2.setText("");
                        edtRepeticoes3.setText("");
                        edtRepeticoes4.setText("");
                        edtRepeticoes5.setText("");
                        edtRepeticoes6.setText("");
                        edtRepeticoes7.setText("");
                        edtRepeticoes8.setText("");
                        edtRepeticoes9.setText("");
                        edtRepeticoes10.setText("");

                        edtCarga.setText("");
                        edtCarga2.setText("");
                        edtCarga3.setText("");
                        edtCarga4.setText("");
                        edtCarga5.setText("");
                        edtCarga6.setText("");
                        edtCarga7.setText("");
                        edtCarga8.setText("");
                        edtCarga9.setText("");
                        edtCarga10.setText("");

                        edtExercicio.requestFocus();

                        //tutorialUpdateMusculo();
                        if (contadorturial == 4) {

                            ((InputMethodManager) Treinos.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                    edtExercicio.getWindowToken(), 0);

                            constraintLayoutAddExercicio.setVisibility(View.GONE);
                            btnExibirForm.setVisibility(View.VISIBLE);
                            btnDesmarcarChecks.setVisibility(View.VISIBLE);

                            contadorturial++;

                            editorTutorial.putInt("tutorial", contadorturial);
                            editorTutorial.commit();

                        }

                    }

                } catch (SQLException ex) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Treinos.this);
                    dlg.setTitle("Erro");
                    dlg.setMessage(ex.getMessage());
                    dlg.setNeutralButton("Erro de inserção das medidas", null);
                    dlg.show();
                }

            }
        });

        btnRefreshAdpapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Treinos.this, Treinos.class));
            }
        });

        btnIniciarTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Seg = Integer.parseInt(seconds.getText().toString());
                int Min = Integer.parseInt(minutes.getText().toString());
                int Hor = Integer.parseInt(hours.getText().toString());

                if (Seg > 0 || Min > 0 || Hor > 0) {
                    startButton.performClick();
                } else {
                    idConstraintLayoutExecTempo.setVisibility(View.GONE);
                    //idConstraintLayoutEditarTempo.setVisibility(View.VISIBLE);
                    Toast.makeText(Treinos.this, "Informe o seu tempo de descanso.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        BuscarAdapterMusculo();
        //tutorialArrasteProLado();
        tutorialAddTreino();

        fechaConexao();

        //Temporizador

        InterstitialAd.load(this, getString(R.string.intersticial_temporizador), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialTemporizador = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        //Toast.makeText(Treinos.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        interstitialTemporizador = null;
                    }
                });

        InterstitialAd.load(this, getString(R.string.intersticial_temporizador2), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialTemporizador2 = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        //Toast.makeText(Treinos.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        interstitialTemporizador2 = null;
                    }
                });
        InterstitialAd.load(this, getString(R.string.intersticial_temporizador3), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialTemporizador3 = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                       // Toast.makeText(Treinos.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        interstitialTemporizador3 = null;
                    }
                });
        InterstitialAd.load(this, getString(R.string.intersticial_temporizador4), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialTemporizador4 = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        //Toast.makeText(Treinos.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        interstitialTemporizador4 = null;
                    }
                });
        InterstitialAd.load(this, getString(R.string.intersticial_temporizador5), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialTemporizador5 = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        //Toast.makeText(Treinos.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        interstitialTemporizador5 = null;
                    }
                });


        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(SegundoPref)) {
            seconds.setText(sharedpreferences.getString(SegundoPref, ""));
        }
        if (sharedpreferences.contains(MinPref)) {
            minutes.setText(sharedpreferences.getString(MinPref, ""));

        }
        if (sharedpreferences.contains(HorasPref)) {
            hours.setText(sharedpreferences.getString(HorasPref, ""));

        }

        setupButtons();
        //ExecutarTemporizador();
        //funcaoContarPropaganda();

        //////////////////////////////////////////////////


    }

    public void referenciaXML() {

        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        linearNovoTreino = (LinearLayout) findViewById(R.id.linearNovoTreino);

        checkCargProg = (CheckBox) findViewById(R.id.checkCargProg);

        constraintLayout333 = (ConstraintLayout) findViewById(R.id.constraintLayout333);
        constraintLayoutAddExercicio = (ConstraintLayout) findViewById(R.id.constraintLayoutAddExercicio);

        constraintLayoutRecycle1 = (ConstraintLayout) findViewById(R.id.constraintLayoutRecycle1);
        constrLayRepCarg2 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg2);
        constrLayRepCarg3 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg3);
        constrLayRepCarg4 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg4);
        constrLayRepCarg5 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg5);
        constrLayRepCarg6 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg6);
        constrLayRepCarg7 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg7);
        constrLayRepCarg8 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg8);
        constrLayRepCarg9 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg9);
        constrLayRepCarg10 = (ConstraintLayout) findViewById(R.id.constrLayRepCarg10);
        ContrLaytEditCompr = (ConstraintLayout) findViewById(R.id.ContrLaytEditCompr);
        //Temporizador
        idConstraintLayoutExecTempo = (ConstraintLayout) findViewById(R.id.idConstraintLayoutExecTempo);
        idConstraintLayoutEditarTempo = (ConstraintLayout) findViewById(R.id.idConstraintLayoutEditarTempo);

        btnAddMusc = (CardView) findViewById(R.id.btnAddMusc);
        btnAddExercicio = (CardView) findViewById(R.id.btnAddExercicio);
        btnDesmarcarChecks = (CardView) findViewById(R.id.btnDesmarcarChecks);
        //temporizador
        btnVoltarTemp = (CardView) findViewById(R.id.btnVoltarTemp);

        spinnerDiaSemana = (Spinner) findViewById(R.id.spinnerDiaSemana);
        spinnerMusculoExerc = (Spinner) findViewById(R.id.spinnerMusculoExerc);
        spinnerUser = (Spinner) findViewById(R.id.spinnerUser);
        spinnerDiaSemanaMuscUpdt = (Spinner) findViewById(R.id.spinnerDiaSemanaMuscUpdt);
        spinnerPosicaoMuscUdpt = (Spinner) findViewById(R.id.spinnerPosicaoMuscUdpt);
        spinnerMusculoExercUpdt = (Spinner) findViewById(R.id.spinnerMusculoExercUpdt);
        spinnerMuscDoTreinoRecycle = (Spinner) findViewById(R.id.spinnerMuscDoTreinoRecycle);

        btnExibirForm = (Button) findViewById(R.id.btnExibirForm);
        btnMostrarAddTreino = (Button) findViewById(R.id.btnMostrarAddTreino);
        btnRefreshAdpapter = (Button) findViewById(R.id.btnRefreshAdpapter);
        //Temporizador
        startButton = (Button) findViewById(R.id.start_button);
        pauseButton = (Button) findViewById(R.id.pause_button);
        btnCancelarTemp = (Button) findViewById(R.id.btnCancelarTemp);
        resetButton = (Button) findViewById(R.id.reset_button);
        btnIniciarTemp = (Button) findViewById(R.id.btnIniciarTemp);
        btnExibirTemp = (CardView) findViewById(R.id.btnExibirTemp);

        edtDataInicial = (EditText) findViewById(R.id.edtDataInicial);
        edtDataFinal = (EditText) findViewById(R.id.edtDataFinal);
        edtNomeUser = (EditText) findViewById(R.id.edtNomeUser);
        edtTipoDeTreino = (EditText) findViewById(R.id.edtTipoDeTreino);
        edtSequencia = (EditText) findViewById(R.id.edtSequencia);
        edtPosicao = (EditText) findViewById(R.id.edtPosicao);
        edtMusculo = (EditText) findViewById(R.id.edtMusculo);
        edtRepeticoes = (EditText) findViewById(R.id.edtRepeticoes);
        edtRepeticoes2 = (EditText) findViewById(R.id.edtRepeticoes2);
        edtRepeticoes3 = (EditText) findViewById(R.id.edtRepeticoes3);
        edtRepeticoes4 = (EditText) findViewById(R.id.edtRepeticoes4);
        edtRepeticoes5 = (EditText) findViewById(R.id.edtRepeticoes5);
        edtRepeticoes6 = (EditText) findViewById(R.id.edtRepeticoes6);
        edtRepeticoes7 = (EditText) findViewById(R.id.edtRepeticoes7);
        edtRepeticoes8 = (EditText) findViewById(R.id.edtRepeticoes8);
        edtRepeticoes9 = (EditText) findViewById(R.id.edtRepeticoes9);
        edtRepeticoes10 = (EditText) findViewById(R.id.edtRepeticoes10);
        edtExercicio = (EditText) findViewById(R.id.edtExercicio);
        edtSeries = (EditText) findViewById(R.id.edtSeries);
        edtCarga = (EditText) findViewById(R.id.edtCarga);
        edtCarga2 = (EditText) findViewById(R.id.edtCarga2);
        edtCarga3 = (EditText) findViewById(R.id.edtCarga3);
        edtCarga4 = (EditText) findViewById(R.id.edtCarga4);
        edtCarga5 = (EditText) findViewById(R.id.edtCarga5);
        edtCarga6 = (EditText) findViewById(R.id.edtCarga6);
        edtCarga7 = (EditText) findViewById(R.id.edtCarga7);
        edtCarga8 = (EditText) findViewById(R.id.edtCarga8);
        edtCarga9 = (EditText) findViewById(R.id.edtCarga9);
        edtCarga10 = (EditText) findViewById(R.id.edtCarga10);
        editDiaDaSemanaMusc = (EditText) findViewById(R.id.editDiaDaSemanaMusc);
        edtMusculoUpdt = (EditText) findViewById(R.id.edtMusculoUpdt);
        //Temnporizador
        seconds = (EditText) findViewById(R.id.seconds);
        minutes = (EditText) findViewById(R.id.minutes);
        hours = (EditText) findViewById(R.id.hours);

        btnDataInicial = (ImageView) findViewById(R.id.btnDataInicial);
        btnDataFinal = (ImageView) findViewById(R.id.btnDataFinal);

        idAdViewTreino = (AdView) findViewById(R.id.idAdViewTreino);

        recycleViewMusculos = (RecyclerView) findViewById(R.id.recycleViewMusculos);

        txtDiaDaSemanaExerc = (TextView) findViewById(R.id.txtDiaDaSemanaExerc);
        txtQtdMusculo1 = (TextView) findViewById(R.id.txtQtdMusculo1);
        txtMaiorPosicao = (TextView) findViewById(R.id.txtMaiorPosicao);
        txtArrastaProLado = (TextView) findViewById(R.id.txtArrastaProLado);
        txtIdMuscExerc = (TextView) findViewById(R.id.txtIdMuscExerc);
        txtIdUserExerc = (TextView) findViewById(R.id.txtIdUserExerc);
        txtIDMuscUpdt = (TextView) findViewById(R.id.txtIDMuscUpdt);
        txtIDUserUpdt = (TextView) findViewById(R.id.txtIDUserUpdt);
        txtPosicaoUpdt = (TextView) findViewById(R.id.txtPosicaoUpdt);
        txtDiaDaSemnaUpdt = (TextView) findViewById(R.id.txtDiaDaSemnaUpdt);
        //Temporizador
        countDownText = (TextView) findViewById(R.id.countDownText);
        hoursLeftText = (TextView) findViewById(R.id.hoursLeftText);
        minutesLeftText = (TextView) findViewById(R.id.minutesLeftText);
        secondsLeftText = (TextView) findViewById(R.id.secondsLeftText);


    }

    //Tutoriais de uso para uso inicial
    public void tutorialAddTreino() {
        if (contadorturial == 1) {

            TapTargetView.showFor(this,
                    TapTarget.forView(btnMostrarAddTreino, "Adicionar Novo Treino", "Informe seu nome como usuário e o tipo de treino, e as datas e click no botão adicionar. Obs.: Os campos tipo de treino e as datas, não são campos obrigatorios." +
                                    "Click no círculo para desativa o informativo")
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
                            .targetRadius(30),
                    new TapTargetView.Listener() {
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);

                            contadorturial++;

                            editorTutorial.putInt("tutorial", contadorturial);
                            editorTutorial.commit();

                        }
                    });

        }
    }

    public void tutorialAddMusculo() {
        if (contadorturial == 2) {

            TapTargetView.showFor(this,
                    TapTarget.forView(constraintLayout333, "Adicionar Músculo Alvo", "Informe o músculo alvo do treino, com seu respectivo dia da semana, e click no botão adicionar (+)." +
                                    "Click no círculo para desativa o informativo")
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
                    new TapTargetView.Listener() {

                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);
                            contadorturial++;

                            editorTutorial.putInt("tutorial", contadorturial);
                            editorTutorial.commit();

                            popularSpinnerUser();

                        }
                    });

        }
    }

    public void tutorialAddExercicio() {
        if (contadorturial == 3) {

            edtMusculo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (false == hasFocus) {
                        ((InputMethodManager) Treinos.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                edtMusculo.getWindowToken(), 0);
                    }
                }
            });

            TapTargetView.showFor(this,
                    TapTarget.forView(btnExibirForm, "Adicionar Exercício", "Exibir a tela para adicionar os execícios. Click no círculo para desativa o informativo")
                            .outerCircleColor(R.color.red_medio)
                            .outerCircleAlpha(0.96f)
                            .targetCircleColor(R.color.white)
                            .titleTextSize(20)
                            .titleTextColor(R.color.white)
                            .descriptionTextSize(15)
                            .descriptionTextColor(R.color.white)
                            .textColor(R.color.white)
                            .textTypeface(Typeface.SANS_SERIF)
                            .dimColor(R.color.white)
                            .drawShadow(true)
                            .cancelable(false)
                            .tintTarget(true)
                            .transparentTarget(true)
                            .targetRadius(60),
                    new TapTargetView.Listener() {

                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);

                            constraintLayoutAddExercicio.setVisibility(view.VISIBLE);
                            btnExibirForm.setVisibility(view.GONE);
                            btnDesmarcarChecks.setVisibility(view.GONE);

                            contadorturial++;

                            editorTutorial.putInt("tutorial", contadorturial);
                            editorTutorial.commit();


                            tutorialAddExercicio2();

                        }
                    });

        }
    }

    public void tutorialAddExercicio2() {
        if (contadorturial == 4) {

            //contadorturial++;

            //editorTutorial.putInt("tutorial", contadorturial);
            //editorTutorial.commit();

            TapTargetView.showFor(this,
                    TapTarget.forView(constraintLayoutAddExercicio, "Adicionar Exercício", "Informe o nome do exercício, a quantidade de series, marque a opção carga progressiva, se for trabalhar com progressão de carga," +
                                    " caso não, deixe esta opção desmarcada e informe as repetições de cada serie, a carga e click em adicionar (+). Click no círculo para desativa o informativo")
                            .outerCircleColor(R.color.red_medio)
                            .outerCircleAlpha(0.96f)
                            .targetCircleColor(R.color.white)
                            .titleTextSize(20)
                            .titleTextColor(R.color.white)
                            .descriptionTextSize(15)
                            .descriptionTextColor(R.color.white)
                            .textColor(R.color.white)
                            .textTypeface(Typeface.SANS_SERIF)
                            .dimColor(R.color.white)
                            .drawShadow(true)
                            .cancelable(false)
                            .tintTarget(true)
                            .transparentTarget(true)
                            .targetRadius(215),
                    new TapTargetView.Listener() {
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);

                        }
                    });

        }
    }

    public void setarInfoParaTelaUpdtMusc() {

        Bundle bundle = getIntent().getExtras();

        musculo = new Musculo();

        if ((bundle != null) && (bundle.containsKey("MUSCULO"))) {

            findViewById(idUpdateMusc).setVisibility(View.VISIBLE);

            musculo = (Musculo) bundle.getSerializable("MUSCULO");

            txtIDMuscUpdt.setText(String.valueOf(musculo.ID));
            edtMusculoUpdt.setText(musculo.MUSCULO);
            txtPosicaoUpdt.setText(String.valueOf(musculo.POSICAO));
            txtDiaDaSemnaUpdt.setText(musculo.DIADASEMANA);
            txtIDUserUpdt.setText(String.valueOf(musculo.IDUSER));

            compareValue = txtPosicaoUpdt.getText().toString();
            compareValueDiaSemUpdt = txtDiaDaSemnaUpdt.getText().toString();

            verificarPosicaoSpinnerPosMusc();

            verificarPosicaoSpinnerDiaSemMusc();

        }
    }

    public void verificarPosicaoSpinnerPosMusc() {

       /* ArrayAdapter<String> adapter_spinner_posicaomusc = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                dataBaseLista.buscarPosicaoMusculoSippner(spinnerDiaSemana.getSelectedItem().toString(), txtIDUserUpdt.getText().toString()));*/

        ArrayAdapter<String> adapter_spinner_posicaomusc = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, ordemmusc);
        spinnerPosicaoMuscUdpt.setAdapter(adapter_spinner_posicaomusc);

        spinnerPosicaoMuscUdpt.setAdapter(adapter_spinner_posicaomusc);

        if (!compareValue.equals(null)) {
            int spinnerPosition = adapter_spinner_posicaomusc.getPosition(compareValue);
            spinnerPosicaoMuscUdpt.setSelection(spinnerPosition);
            //spinnerPosition = 0;
        }
    }

    public void verificarPosicaoSpinnerDiaSemMusc() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, dias_da_semana);

        spinnerDiaSemanaMuscUpdt.setAdapter(adapter);

        if (!compareValue.equals(null)) {
            int spinnerPosition = adapter.getPosition(compareValueDiaSemUpdt);
            spinnerDiaSemanaMuscUpdt.setSelection(spinnerPosition);
            //spinnerPosition = 0;
        }
    }

    public void popularSpinnerUser() {
        ArrayAdapter<String> adapter_spinner_TreinoUser = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                dataBaseLista.buscarNomeUserSippnerTreino());
        spinnerUser.setAdapter(adapter_spinner_TreinoUser);
    }

    public void popularSpinnerDiaSemana() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, dias_da_semana);
        spinnerDiaSemana.setAdapter(adapter);
    }

    public void popularSpinnerMusc() {
        ArrayAdapter<String> adapter_spinner_NomeMusculoEx = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                dataBaseLista.buscarNomeMusculoSippner(spinnerDiaSemana.getSelectedItem().toString(), txtIdUserExerc.getText().toString()));
        spinnerMusculoExerc.setAdapter(adapter_spinner_NomeMusculoEx);

    }

    public void popularSpinnerMuscRecycle() {
        ArrayAdapter<String> adapter_spinner_NomeMusculoRecycle = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                dataBaseLista.buscarNomeMusculoSippnerRecycle(spinnerDiaSemana.getSelectedItem().toString(), txtIdUserExerc.getText().toString()));
        spinnerMuscDoTreinoRecycle.setAdapter(adapter_spinner_NomeMusculoRecycle);

    }

    public void BuscarAdapterMusculo() {
        musculoList = repo_treino.buscar_Musculo(spinnerDiaSemana.getSelectedItem().toString(), txtIdUserExerc.getText().toString(),
                String.valueOf(musc_recycle));
        adapterRecycleMusculo = new AdapterRecycleMusculo(this, musculoList, exercicioList, dataBaseLista);
        recycleViewMusculos.setAdapter(adapterRecycleMusculo);
    }

    public void setarTxtIds() {
        String iduser = dataBaseLista.buscarIDUser(String.valueOf(spinnerUser.getSelectedItem()));
        String idmusc = dataBaseLista.buscarIDMusc(spinnerDiaSemana.getSelectedItem().toString(), String.valueOf(musc), iduser);

        if (iduser.equals("0")) {
            findViewById(idAddNovoTreino).setVisibility(View.VISIBLE);
            findViewById(btnVoltar).setVisibility(View.GONE);

        } else if (idmusc.equals("0")) {
            txtIdUserExerc.setText(iduser);

        } else {
            txtIdUserExerc.setText(iduser);
            txtIdMuscExerc.setText(idmusc);

        }

    }

    public void addNovoTreino(View V) {

        if (validaCamposTelaAddTreino() == false) try {

            if (usuario.IDUSER >= 0) {

                criarConexao();

                repo_treino.inserirUsuario(usuario);

                ArrayAdapter<String> adapter_spinner_TreinoUser = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                        dataBaseLista.buscarNomeUserSippnerTreino());
                spinnerUser.setAdapter(adapter_spinner_TreinoUser);

                setarTxtIds();
                popularSpinnerMuscRecycle();
                BuscarAdapterMusculo();

                findViewById(idAddNovoTreino).setVisibility(View.GONE);

                fechaConexao();

                int iduser = Integer.parseInt(dataBaseLista.buscarIDUser(String.valueOf(spinnerUser.getSelectedItem())));

                if (iduser > 1) {

                    //startActivity(new Intent(Treinos.this, Treinos.class));

                    popularSpinnerUser();

                }

                tutorialAddMusculo();


            }

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(Treinos.this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Erro de inserção das medidas", null);
            dlg.show();
        }
    }

    public void mostrarTelaAddTreino(View v) {
        findViewById(idAddNovoTreino).setVisibility(View.VISIBLE);
    }

    public void voltarTelaAddTreino(View v) {
        findViewById(idAddNovoTreino).setVisibility(View.GONE);
    }

    public void updateMusculo(View V) {

        if (validaCamposUpdate_Musculo() == false) try {

            if (musculo.ID >= 0) {

                criarConexao();

                repo_treino.updateMusculo(musculo);
                dataBaseLista.updateExercicioDiaSemana(spinnerDiaSemanaMuscUpdt.getSelectedItem().toString(),txtIDMuscUpdt.getText().toString(),
                        txtIDUserUpdt.getText().toString());

                popularSpinnerMuscRecycle();
                BuscarAdapterMusculo();
                popularSpinnerMusc();

                setarTxtIds();
                fechaConexao();

                findViewById(idUpdateMusc).setVisibility(View.GONE);

                edtMusculoUpdt.setText("");

                ((InputMethodManager) Treinos.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        edtMusculoUpdt.getWindowToken(), 0);

            }

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(Treinos.this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Erro de inserção das medidas", null);
            dlg.show();
        }
    }

    public void voltarUpdateMusc(View v) {
        findViewById(idUpdateMusc).setVisibility(View.GONE);
    }

    //Função para desmarcar todos os checks das series
    public void funcaoDesmarcarCheck(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(R.string.title_desmarcartodos)
                .setMessage(R.string.msg_desmarcartodos)
                .setPositiveButton(R.string.alert_btn_sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dataBaseLista.desmarcarTodosPorSemana(spinnerDiaSemana.getSelectedItem().toString());

                        criarConexao();

                        BuscarAdapterMusculo();

                        fechaConexao();

                        if (interstitialDesmarcar != null) {
                            interstitialDesmarcar.show(Treinos.this);


                        } else {
                           // Toast.makeText(Treinos.this, "Falha na apresentação da propaganda.", Toast.LENGTH_SHORT).show();
                        }

                        Snackbar.make(view, R.string.desmarcao_concluida, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                })
                .setNegativeButton(R.string.alert_btn_não, null)
                .create()
                .show();

    }

    //Inserir zero de forma altomatica os campos cargas que não foram inseridos
    public void zeroRepCarg() {
        String series = edtSeries.getText().toString();
        if (checkCargProg.isChecked()) {
            if (series == "") {

                edtRepeticoes2.setText("0");
                edtRepeticoes3.setText("0");
                edtRepeticoes4.setText("0");
                edtRepeticoes5.setText("0");
                edtRepeticoes6.setText("0");
                edtRepeticoes7.setText("0");
                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga2.setText("0");
                edtCarga3.setText("0");
                edtCarga4.setText("0");
                edtCarga5.setText("0");
                edtCarga6.setText("0");
                edtCarga7.setText("0");
                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "0") {

                edtRepeticoes2.setText("0");
                edtRepeticoes3.setText("0");
                edtRepeticoes4.setText("0");
                edtRepeticoes5.setText("0");
                edtRepeticoes6.setText("0");
                edtRepeticoes7.setText("0");
                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga2.setText("0");
                edtCarga3.setText("0");
                edtCarga4.setText("0");
                edtCarga5.setText("0");
                edtCarga6.setText("0");
                edtCarga7.setText("0");
                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "1") {
                edtRepeticoes2.setText("0");
                edtRepeticoes3.setText("0");
                edtRepeticoes4.setText("0");
                edtRepeticoes5.setText("0");
                edtRepeticoes6.setText("0");
                edtRepeticoes7.setText("0");
                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga2.setText("0");
                edtCarga3.setText("0");
                edtCarga4.setText("0");
                edtCarga5.setText("0");
                edtCarga6.setText("0");
                edtCarga7.setText("0");
                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "2") {
                edtRepeticoes3.setText("0");
                edtRepeticoes4.setText("0");
                edtRepeticoes5.setText("0");
                edtRepeticoes6.setText("0");
                edtRepeticoes7.setText("0");
                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga3.setText("0");
                edtCarga4.setText("0");
                edtCarga5.setText("0");
                edtCarga6.setText("0");
                edtCarga7.setText("0");
                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "3") {

                edtRepeticoes4.setText("0");
                edtRepeticoes5.setText("0");
                edtRepeticoes6.setText("0");
                edtRepeticoes7.setText("0");
                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga4.setText("0");
                edtCarga5.setText("0");
                edtCarga6.setText("0");
                edtCarga7.setText("0");
                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "4") {

                edtRepeticoes5.setText("0");
                edtRepeticoes6.setText("0");
                edtRepeticoes7.setText("0");
                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga5.setText("0");
                edtCarga6.setText("0");
                edtCarga7.setText("0");
                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "5") {

                edtRepeticoes6.setText("0");
                edtRepeticoes7.setText("0");
                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga6.setText("0");
                edtCarga7.setText("0");
                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "6") {

                edtRepeticoes7.setText("0");
                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga7.setText("0");
                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "7") {

                edtRepeticoes8.setText("0");
                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga8.setText("0");
                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "8") {

                edtRepeticoes9.setText("0");
                edtRepeticoes10.setText("0");

                edtCarga9.setText("0");
                edtCarga10.setText("0");

            } else if (series == "9") {
                edtRepeticoes10.setText("0");

                edtCarga10.setText("0");
            }

        } else {
            edtRepeticoes2.setText("0");
            edtRepeticoes3.setText("0");
            edtRepeticoes4.setText("0");
            edtRepeticoes5.setText("0");
            edtRepeticoes6.setText("0");
            edtRepeticoes7.setText("0");
            edtRepeticoes8.setText("0");
            edtRepeticoes9.setText("0");
            edtRepeticoes10.setText("0");

            edtCarga2.setText("0");
            edtCarga3.setText("0");
            edtCarga4.setText("0");
            edtCarga5.setText("0");
            edtCarga6.setText("0");
            edtCarga7.setText("0");
            edtCarga8.setText("0");
            edtCarga9.setText("0");
            edtCarga10.setText("0");
        }
    }

    //Mostras os demais campor de repeticoes e carga caso a progressã seja acionada
    public void visualizarRepCarg() {

        String series = edtSeries.getText().toString();
        if (checkCargProg.isChecked()) {
            if (Integer.parseInt(series) == 2) {
                constrLayRepCarg2.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);

                constrLayRepCarg3.setVisibility(View.GONE);
                constrLayRepCarg4.setVisibility(View.GONE);
                constrLayRepCarg5.setVisibility(View.GONE);
                constrLayRepCarg6.setVisibility(View.GONE);
                constrLayRepCarg7.setVisibility(View.GONE);
                constrLayRepCarg8.setVisibility(View.GONE);
                constrLayRepCarg9.setVisibility(View.GONE);
                constrLayRepCarg10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 3) {
                linearLayout2.setVisibility(View.VISIBLE);
                constrLayRepCarg2.setVisibility(View.VISIBLE);
                constrLayRepCarg3.setVisibility(View.VISIBLE);

                constrLayRepCarg4.setVisibility(View.GONE);
                constrLayRepCarg5.setVisibility(View.GONE);
                constrLayRepCarg6.setVisibility(View.GONE);
                constrLayRepCarg7.setVisibility(View.GONE);
                constrLayRepCarg8.setVisibility(View.GONE);
                constrLayRepCarg9.setVisibility(View.GONE);
                constrLayRepCarg10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 4) {
                linearLayout2.setVisibility(View.VISIBLE);
                constrLayRepCarg2.setVisibility(View.VISIBLE);
                constrLayRepCarg3.setVisibility(View.VISIBLE);
                constrLayRepCarg4.setVisibility(View.VISIBLE);

                constrLayRepCarg5.setVisibility(View.GONE);
                constrLayRepCarg6.setVisibility(View.GONE);
                constrLayRepCarg7.setVisibility(View.GONE);
                constrLayRepCarg8.setVisibility(View.GONE);
                constrLayRepCarg9.setVisibility(View.GONE);
                constrLayRepCarg10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 5) {
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                constrLayRepCarg2.setVisibility(View.VISIBLE);
                constrLayRepCarg3.setVisibility(View.VISIBLE);
                constrLayRepCarg4.setVisibility(View.VISIBLE);
                constrLayRepCarg5.setVisibility(View.VISIBLE);

                constrLayRepCarg6.setVisibility(View.GONE);
                constrLayRepCarg7.setVisibility(View.GONE);
                constrLayRepCarg8.setVisibility(View.GONE);
                constrLayRepCarg9.setVisibility(View.GONE);
                constrLayRepCarg10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 6) {
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);

                constrLayRepCarg2.setVisibility(View.VISIBLE);
                constrLayRepCarg3.setVisibility(View.VISIBLE);
                constrLayRepCarg4.setVisibility(View.VISIBLE);
                constrLayRepCarg5.setVisibility(View.VISIBLE);
                constrLayRepCarg6.setVisibility(View.VISIBLE);

                constrLayRepCarg7.setVisibility(View.GONE);
                constrLayRepCarg8.setVisibility(View.GONE);
                constrLayRepCarg9.setVisibility(View.GONE);
                constrLayRepCarg10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 7) {
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);

                constrLayRepCarg2.setVisibility(View.VISIBLE);
                constrLayRepCarg3.setVisibility(View.VISIBLE);
                constrLayRepCarg4.setVisibility(View.VISIBLE);
                constrLayRepCarg5.setVisibility(View.VISIBLE);
                constrLayRepCarg6.setVisibility(View.VISIBLE);
                constrLayRepCarg7.setVisibility(View.VISIBLE);

                constrLayRepCarg8.setVisibility(View.GONE);
                constrLayRepCarg9.setVisibility(View.GONE);
                constrLayRepCarg10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 8) {

                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);

                constrLayRepCarg2.setVisibility(View.VISIBLE);
                constrLayRepCarg3.setVisibility(View.VISIBLE);
                constrLayRepCarg4.setVisibility(View.VISIBLE);
                constrLayRepCarg5.setVisibility(View.VISIBLE);
                constrLayRepCarg6.setVisibility(View.VISIBLE);
                constrLayRepCarg7.setVisibility(View.VISIBLE);
                constrLayRepCarg8.setVisibility(View.VISIBLE);

                constrLayRepCarg9.setVisibility(View.GONE);
                constrLayRepCarg10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 9) {
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);

                constrLayRepCarg2.setVisibility(View.VISIBLE);
                constrLayRepCarg3.setVisibility(View.VISIBLE);
                constrLayRepCarg4.setVisibility(View.VISIBLE);
                constrLayRepCarg5.setVisibility(View.VISIBLE);
                constrLayRepCarg6.setVisibility(View.VISIBLE);
                constrLayRepCarg7.setVisibility(View.VISIBLE);
                constrLayRepCarg8.setVisibility(View.VISIBLE);
                constrLayRepCarg9.setVisibility(View.VISIBLE);

                constrLayRepCarg10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 10) {
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.VISIBLE);

                constrLayRepCarg2.setVisibility(View.VISIBLE);
                constrLayRepCarg3.setVisibility(View.VISIBLE);
                constrLayRepCarg4.setVisibility(View.VISIBLE);
                constrLayRepCarg5.setVisibility(View.VISIBLE);
                constrLayRepCarg6.setVisibility(View.VISIBLE);
                constrLayRepCarg7.setVisibility(View.VISIBLE);
                constrLayRepCarg8.setVisibility(View.VISIBLE);
                constrLayRepCarg9.setVisibility(View.VISIBLE);
                constrLayRepCarg10.setVisibility(View.VISIBLE);
            }

        } else {
            constrLayRepCarg2.setVisibility(View.GONE);
            constrLayRepCarg3.setVisibility(View.GONE);
            constrLayRepCarg4.setVisibility(View.GONE);
            constrLayRepCarg5.setVisibility(View.GONE);
            constrLayRepCarg6.setVisibility(View.GONE);
            constrLayRepCarg7.setVisibility(View.GONE);
            constrLayRepCarg8.setVisibility(View.GONE);
            constrLayRepCarg9.setVisibility(View.GONE);
            constrLayRepCarg10.setVisibility(View.GONE);
        }


    }

    //Da foco ao campo edtRepeticoes para obrigar o usuario inserir as repeticoes
    public void clickCheckBoxCargProg(View view) {

        if (checkCargProg.isChecked()) {
            edtRepeticoes.requestFocus();
        }

        /*if(bindingtreino.checkCargProg.isChecked()){
            bindingtreino.linearLayout2.setVisibility(view.VISIBLE);
            bindingtreino.linearLayout3.setVisibility(view.VISIBLE);
            bindingtreino.linearLayout4.setVisibility(view.VISIBLE);
        } else {
            bindingtreino.linearLayout2.setVisibility(view.GONE);
            bindingtreino.linearLayout3.setVisibility(view.GONE);
            bindingtreino.linearLayout4.setVisibility(view.GONE);
        }*/
    }

    //Oculta o campo para add exercicio
    public void clickbtnOcultaFormu(View view) {
        constraintLayoutAddExercicio.setVisibility(view.GONE);
        btnExibirForm.setVisibility(view.VISIBLE);
        btnDesmarcarChecks.setVisibility(view.VISIBLE);

    }

    //Exibir o campo para add exercicio
    public void clickbtnExibirForm(View view) {
        constraintLayoutAddExercicio.setVisibility(view.VISIBLE);
        btnExibirForm.setVisibility(view.GONE);
        btnDesmarcarChecks.setVisibility(view.GONE);

    }

    //Criar e fecha conexão
    public void criarConexao() {
        try {
            dataBaseLista = new DataBase(this);
            conexaotreino = dataBaseLista.getWritableDatabase();
            repo_treino = new Repo_Treino(conexaotreino);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Erro", null);
            dlg.show();
        }
    }

    public void fechaConexao() {
        try {
            dataBaseLista = new DataBase(this);
            conexaotreino = dataBaseLista.getWritableDatabase();
            repo_treino = new Repo_Treino(conexaotreino);
            dataBaseLista.close();

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.text_action_ok_conexao, null);
            dlg.show();
        }
    }

    private boolean validaCamposTelaAddTreino() {

        boolean res = false;

        //é necessario declara a clase model dentro do metodo para o valores recebidos dos editextes sejam identificados (evitar valor nulo).
        usuario = new Usuario();

        int proximotreino = Integer.parseInt(dataBaseLista.selectNumLinhasUser());

        proximotreino++;

        String user = edtNomeUser.getText().toString() + " TR" + proximotreino;

        // int ID = Integer.parseInt(bindingtreino.edtIdTreino.getText().toString());
        String NOMEUSER = user;
        String TIPODETREINO = edtTipoDeTreino.getText().toString();
        String DATAINICIO = edtDataInicial.getText().toString();
        String DATAFIM = edtDataFinal.getText().toString();

        //exercicio.ID = ID;
        usuario.NOMEUSER = NOMEUSER;
        usuario.TIPODETREINO = TIPODETREINO;
        usuario.DATAINICIO = DATAINICIO;
        usuario.DATAFIM = DATAFIM;

        if (res = isCampoVazio(NOMEUSER)) {
            edtNomeUser.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.lista_message_validacampo_nomemusculo);
            dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
            dlg.show();
        }
        return res;
    }

    //Validar campos para add de musculo alvo
    private boolean validaCamposAdd_Musculo() {

        boolean res = false;

        //é necessario declara a clase model dentro do metodo para o valores recebidos dos editextes sejam identificados (evitar valor nulo).
        musculo = new Musculo();

        editDiaDaSemanaMusc.setText(spinnerDiaSemana.getSelectedItem().toString());

        // int ID = Integer.parseInt(bindingtreino.edtIdTreino.getText().toString());
        String Musculo = edtMusculo.getText().toString();
        int Posicao = Integer.parseInt(edtPosicao.getText().toString());
        String DiaDaSemana = editDiaDaSemanaMusc.getText().toString();
        int IDUSER = Integer.parseInt(txtIdUserExerc.getText().toString());


        //exercicio.ID = ID;
        musculo.MUSCULO = Musculo;
        musculo.POSICAO = Posicao;
        musculo.DIADASEMANA = DiaDaSemana;
        musculo.IDUSER = IDUSER;

        if (res = isCampoVazio(Musculo)) {
            edtMusculo.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.lista_message_validacampo_nomemusculo);
            dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
            dlg.show();
        }
        return res;
    }

    //Validar campos para update de musculo alvo
    private boolean validaCamposUpdate_Musculo() {

        boolean res = false;

        //é necessario declara a clase model dentro do metodo para o valores recebidos dos editextes sejam identificados (evitar valor nulo).
        musculo = new Musculo();

        editDiaDaSemanaMusc.setText(spinnerDiaSemana.getSelectedItem().toString());

        int ID = Integer.parseInt(txtIDMuscUpdt.getText().toString());
        String Musculo = edtMusculoUpdt.getText().toString();
        int Posicao = Integer.parseInt(spinnerPosicaoMuscUdpt.getSelectedItem().toString());
        String DiaDaSemana = spinnerDiaSemanaMuscUpdt.getSelectedItem().toString();
        //int IDUSER = Integer.parseInt(txtIdUserExerc.getText().toString());

        musculo.ID = ID;
        musculo.MUSCULO = Musculo;
        musculo.POSICAO = Posicao;
        musculo.DIADASEMANA = DiaDaSemana;
        //musculo.IDUSER = IDUSER;

        if (res = isCampoVazio(Musculo)) {
            edtMusculo.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.lista_message_validacampo_nomemusculo);
            dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
            dlg.show();
        }
        return res;
    }

    //Validar campos para add exercicio
    private boolean validaCamposAddExercicio() {

        boolean res = false, res2 = false, res3 = false, res4 = false;

        //é necessario declara a clase model dentro do metodo para o valores recebidos dos editextes sejam identificados (evitar valor nulo).
        exercicio = new Exercicio();

        // int ID = Integer.parseInt(bindingtreino.edtIdTreino.getText().toString());
        String Musculo = spinnerMusculoExerc.getSelectedItem().toString();
        String Exercicio = edtExercicio.getText().toString();
        String Sequencia = edtSequencia.getText().toString();
        String Series = edtSeries.getText().toString();
        String DiaDaSemana = txtDiaDaSemanaExerc.getText().toString();
        String Repeticoes = edtRepeticoes.getText().toString();
        String Carga = edtCarga.getText().toString();
        String Repeticoes2 = edtRepeticoes2.getText().toString();
        String Carga2 = edtCarga2.getText().toString();
        String Repeticoes3 = edtRepeticoes3.getText().toString();
        String Carga3 = edtCarga3.getText().toString();
        String Repeticoes4 = edtRepeticoes4.getText().toString();
        String Carga4 = edtCarga4.getText().toString();
        String Repeticoes5 = edtRepeticoes5.getText().toString();
        String Carga5 = edtCarga5.getText().toString();
        String Repeticoes6 = edtRepeticoes6.getText().toString();
        String Carga6 = edtCarga6.getText().toString();
        String Repeticoes7 = edtRepeticoes7.getText().toString();
        String Carga7 = edtCarga7.getText().toString();
        String Repeticoes8 = edtRepeticoes8.getText().toString();
        String Carga8 = edtCarga8.getText().toString();
        String Repeticoes9 = edtRepeticoes9.getText().toString();
        String Carga9 = edtCarga9.getText().toString();
        String Repeticoes10 = edtRepeticoes10.getText().toString();
        String Carga10 = edtCarga10.getText().toString();
        String IDMUSC = txtIdMuscExerc.getText().toString();
        String IDUSER = txtIdUserExerc.getText().toString();

        //exercicio.ID = ID;
        exercicio.MUSCULO = Musculo;
        exercicio.EXERCICIO = Exercicio;
        exercicio.SEQUENCIA = Sequencia;
        exercicio.SERIES = Series;
        exercicio.REPETICOES = Repeticoes;

        exercicio.CARGA = Carga;
        exercicio.DIADASEMANA = DiaDaSemana;
        exercicio.REPETICOES2 = Repeticoes2;
        exercicio.CARGA2 = Carga2;
        exercicio.REPETICOES3 = Repeticoes3;
        exercicio.CARGA3 = Carga3;
        exercicio.REPETICOES4 = Repeticoes4;
        exercicio.CARGA4 = Carga4;
        exercicio.REPETICOES5 = Repeticoes5;
        exercicio.CARGA5 = Carga5;
        exercicio.REPETICOES6 = Repeticoes6;
        exercicio.CARGA6 = Carga6;
        exercicio.REPETICOES7 = Repeticoes7;
        exercicio.CARGA7 = Carga7;
        exercicio.REPETICOES8 = Repeticoes8;
        exercicio.CARGA8 = Carga8;
        exercicio.REPETICOES9 = Repeticoes9;
        exercicio.CARGA9 = Carga9;
        exercicio.REPETICOES10 = Repeticoes10;
        exercicio.CARGA10 = Carga10;
        exercicio.IDMUSC = IDMUSC;
        exercicio.IDUSER = IDUSER;

        if (res = isCampoVazio(Exercicio)) {
            edtExercicio.requestFocus();
        } else if (res2 = isCampoVazio(Series)) {
            edtSeries.requestFocus();
        } else if (res4 = isCampoVazio(Repeticoes)) {
            edtRepeticoes.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.lista_message_validacampo_nomeexercicio);
            dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
            dlg.show();
        }
        if (res2) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.lista_message_validacampo_nomeserie);
            dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
            dlg.show();
        }
        if (res4) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.lista_message_validacampo_nomecarga);
            dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
            dlg.show();
        }

        zeroRepCarg();

        return res || res2 || res || res4;


    }

    //Valida campo serie para obrigar usuario informa o numero de series
    private boolean validaCampoSerie() {

        boolean res = false;

        //é necessario declara a clase model dentro do metodo para o valores recebidos dos editextes sejam identificados (evitar valor nulo).
        exercicio = new Exercicio();

        // int ID = Integer.parseInt(bindingtreino.edtIdTreino.getText().toString());
        String Series = edtSeries.getText().toString();

        exercicio.SERIES = Series;

        if (res = isCampoVazio(Series)) {
            edtSeries.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.lista_message_validacampo_nomeserie);
            dlg.setNeutralButton(R.string.lbl_validacao_ok, null);
            dlg.show();
        }

        return res;

    }

    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;

    }

    //Temporizador
    public void funcaoContarPropaganda() {
        sharedPropaganda = getSharedPreferences("LastSettingPropaganda", Context.MODE_PRIVATE);
        editorPropaganda = sharedPropaganda.edit();

        contadorpropaganda = sharedPropaganda.getInt("propaganda", 0);

        contadorpropaganda++;

        if (contadorpropaganda == 41) {
            contadorpropaganda = 0;
        }

        editorPropaganda.putInt("propaganda", contadorpropaganda);
        editorPropaganda.commit();
    }

    public void mostraPropaganda() {
        if (contadorpropaganda == 8) {
            if (interstitialTemporizador != null) {
                interstitialTemporizador.show(Treinos.this);
            }
        } else if(contadorpropaganda == 16){
            if (interstitialTemporizador2 != null) {
                interstitialTemporizador2.show(Treinos.this);
            }
        }else if(contadorpropaganda == 24){
            if (interstitialTemporizador3 != null) {
                interstitialTemporizador4.show(Treinos.this);
            }
        }else if(contadorpropaganda == 32){
            if (interstitialTemporizador4 != null) {
                interstitialTemporizador4.show(Treinos.this);
            }
        }else if(contadorpropaganda == 40){
            if (interstitialTemporizador5 != null) {
                interstitialTemporizador5.show(Treinos.this);
            }
        }
    }

    public void ExecutarTemporizador(View view) {
        int Seg = Integer.parseInt(seconds.getText().toString());
        int Min = Integer.parseInt(minutes.getText().toString());
        int Hor = Integer.parseInt(hours.getText().toString());

        if (Seg > 0 || Min > 0 || Hor > 0) {
            startButton.performClick();
        } else {
            idConstraintLayoutExecTempo.setVisibility(View.GONE);
            idConstraintLayoutEditarTempo.setVisibility(View.VISIBLE);
            Toast.makeText(Treinos.this, "Informe o seu tempo de descanso.", Toast.LENGTH_SHORT).show();

        }
    }

    public void EditarTemporizador(View view) {
        timer.cancel();
        idConstraintLayoutExecTempo.setVisibility(View.GONE);
        idConstraintLayoutEditarTempo.setVisibility(View.VISIBLE);

        ativarbtntempor--;
    }

    public void Save() {
        String s = seconds.getText().toString();
        String min = minutes.getText().toString();
        String h = hours.getText().toString();


        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SegundoPref, s);
        editor.putString(MinPref, min);
        editor.putString(HorasPref, h);

        editor.commit();
    }

    public void Get() {

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(SegundoPref)) {
            seconds.setText(sharedpreferences.getString(SegundoPref, ""));
        }
        if (sharedpreferences.contains(MinPref)) {
            minutes.setText(sharedpreferences.getString(MinPref, ""));

        }
        if (sharedpreferences.contains(HorasPref)) {
            hours.setText(sharedpreferences.getString(HorasPref, ""));

        }

    }

    private void finishTimer(String message) {
        countDownText.setText(message);
        player.start();
        //temporizadorBinding.startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        btnCancelarTemp.setEnabled(false);
        btnExibirTemp.setVisibility(View.VISIBLE);
    }

    private void finishTimerSemTok(String message) {
        countDownText.setText(message);
        //temporizadorBinding.startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        btnCancelarTemp.setEnabled(false);
    }

    private void updateTimeRemaining(long millisUntilFinished) {
        totalSecondsLeft = (int) millisUntilFinished / 1000;
        hoursLeft = totalSecondsLeft / 3600;
        minutesLeft = (totalSecondsLeft % 3600) / 60;
        secondsLeft = totalSecondsLeft % 60;
        hoursLeftText.setText(String.format("%02d", hoursLeft));
        minutesLeftText.setText(String.format("%02d", minutesLeft));
        secondsLeftText.setText(String.format("%02d", secondsLeft));
        countDownText.setText("Contagem regressiva em andamento");

        /*SharedPreferences preferencias = getSharedPreferences("preferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putLong("iniciarTempo", iniciarTempoMilisegundos);
        editor.putLong("TempoRestante", tempoRestanteMilesegundos);
        editor.putBoolean("TempoCorrendo", relogioCorrendo);
        editor.putLong("TempoFinalizado", tempoFinalizado);*/

        //int progress = (int) (millisUntilFinished/1000);
        // bindingtreino.progresTemp.setProgress(totalSecondsLeft);
        //int progress = (int) (millisUntilFinished/1000);
        //bindingtreino.progresTemp.setProgress(totalSecondsLeft);
    }

    private void setupButtons() {

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ativarbtntempor == 0){

                    funcaoContarPropaganda();

                    System.out.println(contadorpropaganda);

                    ativarbtntempor++;

                    player = MediaPlayer.create(Treinos.this, R.raw.toketemp1);

                    ((InputMethodManager) Treinos.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            seconds.getWindowToken(), 0);
                    ((InputMethodManager) Treinos.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            minutes.getWindowToken(), 0);
                    ((InputMethodManager) Treinos.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            hours.getWindowToken(), 0);

                    idConstraintLayoutEditarTempo.setVisibility(View.GONE);
                    idConstraintLayoutExecTempo.setVisibility(View.VISIBLE);
                    btnExibirTemp.setVisibility(View.GONE);

                    //AdapterRecycleExercicio.ViewHolderAdapterMedidas.btnCronometro.setEnabled(false);

                    //bindingtreino.progresTemp.setVisibility(View.VISIBLE);

                    startTime = 0;
                    startTime += Integer.parseInt(seconds.getText().toString()) * 1000;
                    startTime += Integer.parseInt(minutes.getText().toString()) * 60 * 1000;
                    startTime += Integer.parseInt(hours.getText().toString()) * 60 * 60 * 1000;

                    //temporizadorBinding.startButton.setEnabled(false);
                    resetButton.setEnabled(true);
                    pauseButton.setEnabled(true);
                    btnCancelarTemp.setEnabled(true);

                    Save();

                    // bindingtreino.progresTemp.setProgress(startTime);

                    timer = new CountDownTimer(startTime, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            updateTimeRemaining(millisUntilFinished);

                            // double progress = 1.0 - (millisUntilFinished / 1000);

                            //bindingtreino.progresTemp.setProgress((int) progress);

                        }

                        @Override
                        public void onFinish() {
                            finishTimer("Contagem regressiva completa.");
                            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Treinos.this);

                            builder.setTitle(R.string.title_adapterlistas_dellista)
                                    .setMessage(R.string.msg_adapterlistas_stoptempo)
                                    .setCancelable(false)
                                    .setPositiveButton("PARA ALARME", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            player.stop();
                                            mostraPropaganda();
                                            Get();
                                            //idConstraintLayoutEditarTempo.setVisibility(View.VISIBLE);
                                            idConstraintLayoutExecTempo.setVisibility(View.GONE);

                                            ativarbtntempor--;

                                            // onBackPressed();
                                            //Intent it = new Intent(Temporizador.this, Treinos.class);
                                            //((AppCompatActivity) Temporizador.this).startActivity(it);

                                        }
                                    })
                                    .setNegativeButton(null, null)
                                    .create()
                                    .show();

                        }
                    }.start();
                } else {
                    Toast.makeText(Treinos.this, "Contagem regressiva em andamento.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                resetButton.setEnabled(true);
                pauseButton.setEnabled(true);
                pauseButton.setText("Pausar");
                isPaused = false;
                btnCancelarTemp.setEnabled(true);

                timer.cancel();
                timer = new CountDownTimer(startTime, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        updateTimeRemaining(millisUntilFinished);

                    }

                    @Override
                    public void onFinish() {
                        finishTimer("Contagem regressiva completa.");
                        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Treinos.this);
                        builder.setTitle(R.string.title_adapterlistas_dellista)
                                .setMessage(R.string.msg_adapterlistas_dellista_exercicio)
                                .setCancelable(false)
                                .setPositiveButton("PARA ALARME", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        player.stop();
                                        mostraPropaganda();
                                        Get();
                                        //idConstraintLayoutEditarTempo.setVisibility(View.VISIBLE);
                                        idConstraintLayoutExecTempo.setVisibility(View.GONE);

                                        ativarbtntempor--;
                                        //onBackPressed();
                                        //Intent it = new Intent(Temporizador.this, Treinos.class);
                                        //((AppCompatActivity) Temporizador.this).startActivity(it);

                                    }
                                })
                                .setNegativeButton(null, null)
                                .create()
                                .show();
                    }
                }.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaused = !isPaused;
                if (isPaused) {
                    pauseButton.setText("Retomar");
                    timer.cancel();
                    countDownText.setText("Contagem regressiva pausada.");
                } else {
                    pauseButton.setText("Pause");
                    timer = new CountDownTimer(totalSecondsLeft * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            updateTimeRemaining(millisUntilFinished);

                        }

                        @Override
                        public void onFinish() {
                            finishTimer("Contagem regressiva completa.");
                            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Treinos.this);
                            builder.setTitle(R.string.title_adapterlistas_dellista)
                                    .setMessage(R.string.msg_adapterlistas_stoptempo)
                                    .setPositiveButton("PARA ALARME", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            player.stop();
                                            mostraPropaganda();
                                            Get();
                                            //idConstraintLayoutEditarTempo.setVisibility(View.VISIBLE);
                                            idConstraintLayoutExecTempo.setVisibility(View.GONE);

                                            ativarbtntempor--;
                                            //onBackPressed();
                                            //Intent it = new Intent(Temporizador.this, Treinos.class);
                                            //((AppCompatActivity) Temporizador.this).startActivity(it);

                                        }
                                    })
                                    .setNegativeButton(null, null)
                                    .create()
                                    .show();
                        }
                    }.start();
                }

            }
        });

        btnVoltarTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idConstraintLayoutEditarTempo.setVisibility(View.GONE);
                idConstraintLayoutExecTempo.setVisibility(View.VISIBLE);
                resetButton.performClick();
                ativarbtntempor++;


                //Intent it = new Intent(Temporizador.this, Treinos.class);
                //((AppCompatActivity) Temporizador.this).startActivity(it);
            }
        });

        btnCancelarTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                finishTimerSemTok("Contagem regressiva cancelada.");
                mostraPropaganda();
                Get();
                //idConstraintLayoutEditarTempo.setVisibility(View.VISIBLE);
                idConstraintLayoutExecTempo.setVisibility(View.GONE);
                btnExibirTemp.setVisibility(View.VISIBLE);

                ativarbtntempor--;

                //onBackPressed();
                //Intent it = new Intent(Temporizador.this, Treinos.class);
                //((AppCompatActivity) Temporizador.this).startActivity(it);
            }
        });

    }

    public void ocultarTemporizador(View view){
        idConstraintLayoutExecTempo.setVisibility(View.GONE);
        btnExibirTemp.setVisibility(View.VISIBLE);
    }

    public void exibirTemporizador(View view){
        idConstraintLayoutExecTempo.setVisibility(View.VISIBLE);
        btnExibirTemp.setVisibility(View.GONE);
    }

}