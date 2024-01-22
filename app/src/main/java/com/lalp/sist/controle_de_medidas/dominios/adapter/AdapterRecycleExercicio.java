package com.lalp.sist.controle_de_medidas.dominios.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.lalp.sist.controle_de_medidas.R;
import com.lalp.sist.controle_de_medidas.Treinos;
import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Exercicio;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Musculo;

import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import static com.lalp.sist.controle_de_medidas.Treinos.btnRefreshAdpapter;


public class AdapterRecycleExercicio extends RecyclerView.Adapter<AdapterRecycleExercicio.ViewHolderAdapterMedidas> {
    public static List<Exercicio> listexercicio;
    private List<Musculo> musculoList;
    //private ArrayList<RG_Med> rela;
    public Context context;
    public int checkserie = 0;

    public Exercicio exerc;
    private SQLiteDatabase bancoDados;
    public static AdapterRecycleExercicio adapterRecycleExercicio;
    public static Activity activity;
    public static String musculospin = null;

    public DataBase database;
    public AdapterRecycleExercicio( List<Exercicio> listexercicio) {
         this.listexercicio = listexercicio;
    }

    public void removerCliente(Exercicio exercicio) {
        int position = listexercicio.indexOf(exercicio);
        listexercicio.remove(position);
        notifyItemRemoved(position);
    }
    public void update(Exercicio exercicio, int position){
        listexercicio.set(position, exercicio);
        //notifyItemChanged(position);
        notifyDataSetChanged();
    }


    private void updateItem2222(Exercicio medidas) {
        int position = listexercicio.indexOf(medidas);
        listexercicio.notify();
        notifyItemChanged(position);
    }
    /*private void updateItem3(Exercicio exercicio) {
        int position = listexercicio.indexOf(exercicio);
        exercicio.incrementAge();
        notifyItemChanged(position);
    }

    private void updateItem(int position) {
        Exercicio exercicio = listexercicio.get(position);
        exercicio.incrementAge();
        notifyItemChanged(position);
    }*/

    @Override
    public AdapterRecycleExercicio.ViewHolderAdapterMedidas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_exercicio, parent, false);
        final ViewHolderAdapterMedidas viewHolderAdapterMedidas = new ViewHolderAdapterMedidas(view, parent.getContext());

        return viewHolderAdapterMedidas;
    }


    @Override
    public void onBindViewHolder(@NonNull final AdapterRecycleExercicio.ViewHolderAdapterMedidas holder, int position) {

        if ((listexercicio != null) && (listexercicio.size() > 0)) {

            Exercicio exercicio = listexercicio.get(position);

            holder.txtIdExer.setText(String.valueOf(exercicio.ID));
            holder.txtMusculoEx.setText(exercicio.MUSCULO);
            holder.txtExercicio.setText(exercicio.EXERCICIO);
            holder.txtSequencia.setText(exercicio.SEQUENCIA);
            holder.txtSeries.setText(String.valueOf(exercicio.SERIES));
            holder.txtRep1.setText(String.valueOf(exercicio.REPETICOES));
            holder.txtCarga1.setText(String.valueOf(exercicio.CARGA));
            holder.check1.setChecked(exercicio.CHECK1);
            holder.txtRep2.setText(String.valueOf(exercicio.REPETICOES2));
            holder.txtCarga2.setText(String.valueOf(exercicio.CARGA2));
            holder.check2.setChecked(exercicio.CHECK2);
            holder.txtRep3.setText(String.valueOf(exercicio.REPETICOES3));
            holder.txtCarga3.setText(String.valueOf(exercicio.CARGA3));
            holder.check3.setChecked(exercicio.CHECK3);
            holder.txtRep4.setText(String.valueOf(exercicio.REPETICOES4));
            holder.txtCarga4.setText(String.valueOf(exercicio.CARGA4));
            holder.check4.setChecked(exercicio.CHECK4);
            holder.txtRep5.setText(String.valueOf(exercicio.REPETICOES5));
            holder.txtCarga5.setText(String.valueOf(exercicio.CARGA5));
            holder.check5.setChecked(exercicio.CHECK5);
            holder.txtRep6.setText(String.valueOf(exercicio.REPETICOES6));
            holder.txtCarga6.setText(String.valueOf(exercicio.CARGA6));
            holder.check6.setChecked(exercicio.CHECK6);
            holder.txtRep7.setText(String.valueOf(exercicio.REPETICOES7));
            holder.txtCarga7.setText(String.valueOf(exercicio.CARGA7));
            holder.check7.setChecked(exercicio.CHECK7);
            holder.txtRep8.setText(String.valueOf(exercicio.REPETICOES8));
            holder.txtCarga8.setText(String.valueOf(exercicio.CARGA8));
            holder.check8.setChecked(exercicio.CHECK8);
            holder.txtRep9.setText(String.valueOf(exercicio.REPETICOES9));
            holder.txtCarga9.setText(String.valueOf(exercicio.CARGA9));
            holder.check9.setChecked(exercicio.CHECK9);
            holder.txtRep10.setText(String.valueOf(exercicio.REPETICOES10));
            holder.txtCarga10.setText(String.valueOf(exercicio.CARGA10));
            holder.check10.setChecked(exercicio.CHECK10);
            holder.txtDiaDaSemana.setText(exercicio.DIADASEMANA);
            holder.txtIDMuscExerc.setText(exercicio.IDMUSC);
            holder.txtIDUserMuscExerc.setText(exercicio.IDUSER);

            holder.btnAtualizarLista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DataBase dataBase = new DataBase(v.getContext());
                    notifyDataSetChanged();

                    listexercicio = dataBase.buscarExercicio(holder.txtDiaDaSemana.getText().toString(), holder.txtIDMuscExerc.getText().toString(),
                            holder.txtIDUserMuscExerc.getText().toString());

                }
            });

            holder.btnPesquisar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog dialog = new Dialog(v.getContext());
                    dialog.setContentView(R.layout.update_exercicio);
                    String compareValue = null;

                    DataBase dataBase = new DataBase(v.getContext());

                    //dataBase.buscarExercicio(holder.txtDiaDaSemana.getText().toString(), holder.txtIDMuscExerc.getText().toString(),
                     //holder.txtIDUserMuscExerc.getText().toString());

                    EditText edtIdTreinoUpdt = dialog.findViewById(R.id.edtIdTreinoUpdt);
                    EditText edtMusculoExercUpdt = dialog.findViewById(R.id.edtMusculoExercUpdt);
                    EditText edtExercicioUpdt = dialog.findViewById(R.id.edtExercicioUpdt);
                    EditText edtSequenciaExercUpdt = dialog.findViewById(R.id.edtSequenciaExercUpdt);
                    EditText edtSeriesUpdt = dialog.findViewById(R.id.edtSeriesUpdt);
                    EditText edtRepeticoesUpdt = dialog.findViewById(R.id.edtRepeticoesUpdt);
                    EditText edtCargaUpdt = dialog.findViewById(R.id.edtCargaUpdt);
                    EditText edtRepeticoes2Updt = dialog.findViewById(R.id.edtRepeticoes2Updt);
                    EditText edtCarga2Updt = dialog.findViewById(R.id.edtCarga2Updt);
                    EditText edtRepeticoes3Updt = dialog.findViewById(R.id.edtRepeticoes3Updt);
                    EditText edtCarga3Updt = dialog.findViewById(R.id.edtCarga3Updt);
                    EditText edtRepeticoes4Updt = dialog.findViewById(R.id.edtRepeticoes4Updt);
                    EditText edtCarga4Updt = dialog.findViewById(R.id.edtCarga4Updt);
                    EditText edtRepeticoes5Updt = dialog.findViewById(R.id.edtRepeticoes5Updt);
                    EditText edtCarga5Updt = dialog.findViewById(R.id.edtCarga5Updt);
                    EditText edtRepeticoes6Updt = dialog.findViewById(R.id.edtRepeticoes6Updt);
                    EditText edtCarga6Updt = dialog.findViewById(R.id.edtCarga6Updt);
                    EditText edtRepeticoes7Updt = dialog.findViewById(R.id.edtRepeticoes7Updt);
                    EditText edtCarga7Updt = dialog.findViewById(R.id.edtCarga7Updt);
                    EditText edtRepeticoes8Updt = dialog.findViewById(R.id.edtRepeticoes8Updt);
                    EditText edtCarga8Updt = dialog.findViewById(R.id.edtCarga8Updt);
                    EditText edtRepeticoes9Updt = dialog.findViewById(R.id.edtRepeticoes9Updt);
                    EditText edtCarga9Updt = dialog.findViewById(R.id.edtCarga9Updt);
                    EditText edtRepeticoes10Updt = dialog.findViewById(R.id.edtRepeticoes10Updt);
                    EditText edtCarga10Updt = dialog.findViewById(R.id.edtCarga10Updt);

                    TextView txtDiaDaSemanaUdpt = dialog.findViewById(R.id.txtDiaDaSemanaUdpt);
                    TextView txtIDUserExercUpdt = dialog.findViewById(R.id.txtIDUserExercUpdt);
                    TextView txtIDMuscExercUpdt = dialog.findViewById(R.id.txtIDMuscExercUpdt);

                    Button btnAtualizarUpdate = dialog.findViewById(R.id.btnAtualizarUpdate);

                    CardView btnVoltarUpdate = dialog.findViewById(R.id.btnVoltarUpdate);

                    ConstraintLayout constrLayRepCarg2Updt = dialog.findViewById(R.id.constrLayRepCarg2Updt);
                    ConstraintLayout constrLayRepCarg3Updt = dialog.findViewById(R.id.constrLayRepCarg3Updt);
                    ConstraintLayout constrLayRepCarg4Updt = dialog.findViewById(R.id.constrLayRepCarg4Updt);
                    ConstraintLayout constrLayRepCarg5Updt = dialog.findViewById(R.id.constrLayRepCarg5Updt);
                    ConstraintLayout constrLayRepCarg6Updt = dialog.findViewById(R.id.constrLayRepCarg6Updt);
                    ConstraintLayout constrLayRepCarg7Updt = dialog.findViewById(R.id.constrLayRepCarg7Updt);
                    ConstraintLayout constrLayRepCarg8Updt = dialog.findViewById(R.id.constrLayRepCarg8Updt);
                    ConstraintLayout constrLayRepCarg9Updt = dialog.findViewById(R.id.constrLayRepCarg9Updt);
                    ConstraintLayout constrLayRepCarg10Updt = dialog.findViewById(R.id.constrLayRepCarg10Updt);

                    Spinner spinnerMusculoExercUpdt = dialog.findViewById(R.id.spinnerMusculoExercUpdt);

                    /* holder.txtIdExer.setText(String.valueOf(exercicio.ID));
                    holder.txtMusculoEx.setText(exercicio.MUSCULO);
                    holder.txtExercicio.setText(exercicio.EXERCICIO);
                    holder.txtSequencia.setText(exercicio.SEQUENCIA);
                    holder.txtSeries.setText(String.valueOf(exercicio.SERIES));
                    holder.txtRep1.setText(String.valueOf(exercicio.REPETICOES));
                    holder.txtCarga1.setText(String.valueOf(exercicio.CARGA));
                    holder.check1.setChecked(exercicio.CHECK1);
                    holder.txtRep2.setText(String.valueOf(exercicio.REPETICOES2));
                    holder.txtCarga2.setText(String.valueOf(exercicio.CARGA2));
                    holder.check2.setChecked(exercicio.CHECK2);
                    holder.txtRep3.setText(String.valueOf(exercicio.REPETICOES3));
                    holder.txtCarga3.setText(String.valueOf(exercicio.CARGA3));
                    holder.check3.setChecked(exercicio.CHECK3);
                    holder.txtRep4.setText(String.valueOf(exercicio.REPETICOES4));
                    holder.txtCarga4.setText(String.valueOf(exercicio.CARGA4));
                    holder.check4.setChecked(exercicio.CHECK4);
                    holder.txtRep5.setText(String.valueOf(exercicio.REPETICOES5));
                    holder.txtCarga5.setText(String.valueOf(exercicio.CARGA5));
                    holder.check5.setChecked(exercicio.CHECK5);
                    holder.txtRep6.setText(String.valueOf(exercicio.REPETICOES6));
                    holder.txtCarga6.setText(String.valueOf(exercicio.CARGA6));
                    holder.check6.setChecked(exercicio.CHECK6);
                    holder.txtRep7.setText(String.valueOf(exercicio.REPETICOES7));
                    holder.txtCarga7.setText(String.valueOf(exercicio.CARGA7));
                    holder.check7.setChecked(exercicio.CHECK7);
                    holder.txtRep8.setText(String.valueOf(exercicio.REPETICOES8));
                    holder.txtCarga8.setText(String.valueOf(exercicio.CARGA8));
                    holder.check8.setChecked(exercicio.CHECK8);
                    holder.txtRep9.setText(String.valueOf(exercicio.REPETICOES9));
                    holder.txtCarga9.setText(String.valueOf(exercicio.CARGA9));
                    holder.check9.setChecked(exercicio.CHECK9);
                    holder.txtRep10.setText(String.valueOf(exercicio.REPETICOES10));
                    holder.txtCarga10.setText(String.valueOf(exercicio.CARGA10));
                    holder.check10.setChecked(exercicio.CHECK10);
                    holder.txtDiaDaSemana.setText(exercicio.DIADASEMANA);
                    holder.txtIDMuscExerc.setText(exercicio.IDMUSC);
                    holder.txtIDUserMuscExerc.setText(exercicio.IDUSER);

                   edtIdTreinoUpdt.setText(holder.txtIdExer.getText().toString());
                    edtMusculoExercUpdt.setText(holder.txtMusculoEx.getText().toString());
                    edtExercicioUpdt.setText(holder.txtExercicio.getText().toString());
                    edtSequenciaExercUpdt.setText(holder.txtSequencia.getText().toString());
                    edtSeriesUpdt.setText(holder.txtSeries.getText().toString());
                    edtRepeticoesUpdt.setText(holder.txtRep1.getText().toString());
                    edtCargaUpdt.setText(holder.txtCarga1.getText().toString());
                    edtRepeticoes2Updt.setText(holder.txtRep2.getText().toString());
                    edtCarga2Updt.setText(holder.txtCarga2.getText().toString());
                    edtRepeticoes3Updt.setText(holder.txtRep3.getText().toString());
                    edtCarga3Updt.setText(holder.txtCarga3.getText().toString());
                    edtRepeticoes4Updt.setText(holder.txtRep4.getText().toString());
                    edtCarga4Updt.setText(holder.txtCarga4.getText().toString());
                    edtRepeticoes5Updt.setText(holder.txtRep5.getText().toString());
                    edtCarga5Updt.setText(holder.txtCarga5.getText().toString());
                    edtRepeticoes6Updt.setText(holder.txtRep6.getText().toString());
                    edtCarga6Updt.setText(holder.txtCarga6.getText().toString());
                    edtRepeticoes7Updt.setText(holder.txtRep7.getText().toString());
                    edtCarga7Updt.setText(holder.txtCarga7.getText().toString());
                    edtRepeticoes8Updt.setText(holder.txtRep8.getText().toString());
                    edtCarga8Updt.setText(holder.txtCarga8.getText().toString());
                    edtRepeticoes9Updt.setText(holder.txtRep9.getText().toString());
                    edtCarga9Updt.setText(holder.txtCarga9.getText().toString());
                    edtRepeticoes10Updt.setText(holder.txtRep10.getText().toString());
                    edtCarga10Updt.setText(holder.txtCarga10.getText().toString());
                    txtDiaDaSemanaUdpt.setText(holder.txtDiaDaSemana.getText().toString());
                    txtIDMuscExercUpdt.setText(holder.txtIDMuscExerc.getText().toString());
                    txtIDUserExercUpdt.setText(holder.txtIDUserMuscExerc.getText().toString());*/


                   /* edtIdTreinoUpdt.setText(String.valueOf(listexercicio.get(position).ID));
                    edtMusculoExercUpdt.setText(listexercicio.get(position).MUSCULO);
                    edtExercicioUpdt.setText(listexercicio.get(position).EXERCICIO);
                    edtSequenciaExercUpdt.setText(listexercicio.get(position).SEQUENCIA);
                    edtSeriesUpdt.setText(String.valueOf(listexercicio.get(position).SERIES));
                    edtRepeticoesUpdt.setText(String.valueOf(listexercicio.get(position).REPETICOES));
                    edtCargaUpdt.setText(String.valueOf(listexercicio.get(position).CARGA));

                    edtRepeticoes2Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES2));
                    edtCarga2Updt.setText(String.valueOf(listexercicio.get(position).CARGA2));

                    edtRepeticoes3Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES3));
                    edtCarga3Updt.setText(String.valueOf(listexercicio.get(position).CARGA3));

                    edtRepeticoes4Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES4));
                    edtCarga4Updt.setText(String.valueOf(listexercicio.get(position).CARGA4));

                    edtRepeticoes5Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES5));
                    edtCarga5Updt.setText(String.valueOf(listexercicio.get(position).CARGA5));

                    edtRepeticoes6Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES6));
                    edtCarga6Updt.setText(String.valueOf(listexercicio.get(position).CARGA6));

                    edtRepeticoes7Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES7));
                    edtCarga7Updt.setText(String.valueOf(listexercicio.get(position).CARGA7));

                    edtRepeticoes8Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES8));
                    edtCarga8Updt.setText(String.valueOf(listexercicio.get(position).CARGA8));

                    edtRepeticoes9Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES9));
                    edtCarga9Updt.setText(String.valueOf(listexercicio.get(position).CARGA9));

                    edtRepeticoes10Updt.setText(String.valueOf(listexercicio.get(position).REPETICOES10));
                    edtCarga10Updt.setText(String.valueOf(listexercicio.get(position).CARGA10));

                    txtDiaDaSemanaUdpt.setText(listexercicio.get(position).DIADASEMANA);
                    txtIDMuscExercUpdt.setText(listexercicio.get(position).IDMUSC);
                    txtIDUserExercUpdt.setText(listexercicio.get(position).IDUSER);*/


                    edtIdTreinoUpdt.setText(holder.txtIdExer.getText().toString());
                    edtMusculoExercUpdt.setText(holder.txtMusculoEx.getText().toString());
                    edtExercicioUpdt.setText(holder.txtExercicio.getText().toString());
                    edtSequenciaExercUpdt.setText(holder.txtSequencia.getText().toString());
                    edtSeriesUpdt.setText(holder.txtSeries.getText().toString());
                    edtRepeticoesUpdt.setText(holder.txtRep1.getText().toString());
                    edtCargaUpdt.setText(holder.txtCarga1.getText().toString());
                    edtRepeticoes2Updt.setText(holder.txtRep2.getText().toString());
                    edtCarga2Updt.setText(holder.txtCarga2.getText().toString());
                    edtRepeticoes3Updt.setText(holder.txtRep3.getText().toString());
                    edtCarga3Updt.setText(holder.txtCarga3.getText().toString());
                    edtRepeticoes4Updt.setText(holder.txtRep4.getText().toString());
                    edtCarga4Updt.setText(holder.txtCarga4.getText().toString());
                    edtRepeticoes5Updt.setText(holder.txtRep5.getText().toString());
                    edtCarga5Updt.setText(holder.txtCarga5.getText().toString());
                    edtRepeticoes6Updt.setText(holder.txtRep6.getText().toString());
                    edtCarga6Updt.setText(holder.txtCarga6.getText().toString());
                    edtRepeticoes7Updt.setText(holder.txtRep7.getText().toString());
                    edtCarga7Updt.setText(holder.txtCarga7.getText().toString());
                    edtRepeticoes8Updt.setText(holder.txtRep8.getText().toString());
                    edtCarga8Updt.setText(holder.txtCarga8.getText().toString());
                    edtRepeticoes9Updt.setText(holder.txtRep9.getText().toString());
                    edtCarga9Updt.setText(holder.txtCarga9.getText().toString());
                    edtRepeticoes10Updt.setText(holder.txtRep10.getText().toString());
                    edtCarga10Updt.setText(holder.txtCarga10.getText().toString());
                    txtDiaDaSemanaUdpt.setText(holder.txtDiaDaSemana.getText().toString());
                    txtIDMuscExercUpdt.setText(holder.txtIDMuscExerc.getText().toString());
                    txtIDUserExercUpdt.setText(holder.txtIDUserMuscExerc.getText().toString());

                   // notifyItemChanged(position, listexercicio.size());

                    /*edtIdTreinoUpdt.setText(holder.txtIdExer.getText().toString());
                    edtMusculoExercUpdt.setText(holder.txtMusculoEx.getText().toString());
                    edtExercicioUpdt.setText(holder.txtExercicio.getText().toString());
                    edtSequenciaExercUpdt.setText(holder.txtSequencia.getText().toString());
                    edtSeriesUpdt.setText(holder.txtSeries.getText().toString());
                    edtRepeticoesUpdt.setText(holder.txtRep1.getText().toString());
                    edtCargaUpdt.setText(holder.txtCarga1.getText().toString());
                    edtRepeticoes2Updt.setText(holder.txtRep2.getText().toString());
                    edtCarga2Updt.setText(holder.txtCarga2.getText().toString());
                    edtRepeticoes3Updt.setText(holder.txtRep3.getText().toString());
                    edtCarga3Updt.setText(holder.txtCarga3.getText().toString());
                    edtRepeticoes4Updt.setText(holder.txtRep4.getText().toString());
                    edtCarga4Updt.setText(holder.txtCarga4.getText().toString());
                    edtRepeticoes5Updt.setText(holder.txtRep5.getText().toString());
                    edtCarga5Updt.setText(holder.txtCarga5.getText().toString());
                    edtRepeticoes6Updt.setText(holder.txtRep6.getText().toString());
                    edtCarga6Updt.setText(holder.txtCarga6.getText().toString());
                    edtRepeticoes7Updt.setText(holder.txtRep7.getText().toString());
                    edtCarga7Updt.setText(holder.txtCarga7.getText().toString());
                    edtRepeticoes8Updt.setText(holder.txtRep8.getText().toString());
                    edtCarga8Updt.setText(holder.txtCarga8.getText().toString());
                    edtRepeticoes9Updt.setText(holder.txtRep9.getText().toString());
                    edtCarga9Updt.setText(holder.txtCarga9.getText().toString());
                    edtRepeticoes10Updt.setText(holder.txtRep10.getText().toString());
                    edtCarga10Updt.setText(holder.txtCarga10.getText().toString());
                    txtDiaDaSemanaUdpt.setText(holder.txtDiaDaSemana.getText().toString());
                    txtIDMuscExercUpdt.setText(holder.txtIDMuscExerc.getText().toString());
                    txtIDUserExercUpdt.setText(holder.txtIDUserMuscExerc.getText().toString());
                     */

                    database = new DataBase(v.getContext());

                    compareValue = edtMusculoExercUpdt.getText().toString();

                    ArrayAdapter<String> adapter_spinner_NomeMusculoEx = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1,
                            database.buscarNomeMusculoSippner(txtDiaDaSemanaUdpt.getText().toString(), txtIDUserExercUpdt.getText().toString()));
                    spinnerMusculoExercUpdt.setAdapter(adapter_spinner_NomeMusculoEx);

                    spinnerMusculoExercUpdt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            musculospin = parent.getItemAtPosition(position).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    spinnerMusculoExercUpdt.setAdapter(adapter_spinner_NomeMusculoEx);

                    if (!compareValue.equals(null)) {
                        int spinnerPosition = adapter_spinner_NomeMusculoEx.getPosition(compareValue);
                        spinnerMusculoExercUpdt.setSelection(spinnerPosition);
                        //spinnerPosition = 0;
                    }

                    String series = edtSeriesUpdt.getText().toString();

                    if (Integer.parseInt(series) == 0) {
                        constrLayRepCarg2Updt.setVisibility(View.GONE);
                        constrLayRepCarg3Updt.setVisibility(View.GONE);
                        constrLayRepCarg4Updt.setVisibility(View.GONE);
                        constrLayRepCarg5Updt.setVisibility(View.GONE);
                        constrLayRepCarg6Updt.setVisibility(View.GONE);
                        constrLayRepCarg7Updt.setVisibility(View.GONE);
                        constrLayRepCarg8Updt.setVisibility(View.GONE);
                        constrLayRepCarg9Updt.setVisibility(View.GONE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);


                    } else if (Integer.parseInt(series) == 2) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.GONE);
                        constrLayRepCarg4Updt.setVisibility(View.GONE);
                        constrLayRepCarg5Updt.setVisibility(View.GONE);
                        constrLayRepCarg6Updt.setVisibility(View.GONE);
                        constrLayRepCarg7Updt.setVisibility(View.GONE);
                        constrLayRepCarg8Updt.setVisibility(View.GONE);
                        constrLayRepCarg9Updt.setVisibility(View.GONE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);

                    } else if (Integer.parseInt(series) == 3) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg4Updt.setVisibility(View.GONE);
                        constrLayRepCarg5Updt.setVisibility(View.GONE);
                        constrLayRepCarg6Updt.setVisibility(View.GONE);
                        constrLayRepCarg7Updt.setVisibility(View.GONE);
                        constrLayRepCarg8Updt.setVisibility(View.GONE);
                        constrLayRepCarg9Updt.setVisibility(View.GONE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);

                    } else if (Integer.parseInt(series) == 4) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg5Updt.setVisibility(View.GONE);
                        constrLayRepCarg6Updt.setVisibility(View.GONE);
                        constrLayRepCarg7Updt.setVisibility(View.GONE);
                        constrLayRepCarg8Updt.setVisibility(View.GONE);
                        constrLayRepCarg9Updt.setVisibility(View.GONE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);

                    } else if (Integer.parseInt(series) == 5) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg6Updt.setVisibility(View.GONE);
                        constrLayRepCarg7Updt.setVisibility(View.GONE);
                        constrLayRepCarg8Updt.setVisibility(View.GONE);
                        constrLayRepCarg9Updt.setVisibility(View.GONE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);

                    } else if (Integer.parseInt(series) == 6) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg7Updt.setVisibility(View.GONE);
                        constrLayRepCarg8Updt.setVisibility(View.GONE);
                        constrLayRepCarg9Updt.setVisibility(View.GONE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);

                    } else if (Integer.parseInt(series) == 7) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg7Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg8Updt.setVisibility(View.GONE);
                        constrLayRepCarg9Updt.setVisibility(View.GONE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);

                    } else if (Integer.parseInt(series) == 8) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg7Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg8Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg9Updt.setVisibility(View.GONE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);
                    } else if (Integer.parseInt(series) == 9) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg7Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg8Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg9Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg10Updt.setVisibility(View.GONE);
                    } else if (Integer.parseInt(series) == 10) {
                        constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg7Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg8Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg9Updt.setVisibility(View.VISIBLE);
                        constrLayRepCarg10Updt.setVisibility(View.VISIBLE);
                    }

                    btnAtualizarUpdate.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(View v) {

                            String id = edtIdTreinoUpdt.getText().toString();
                            String musc = edtMusculoExercUpdt.getText().toString();
                            String Exerc = edtExercicioUpdt.getText().toString();
                            String sequencia = edtSequenciaExercUpdt.getText().toString();
                            String series = edtSeriesUpdt.getText().toString();
                            String repeticoes = edtRepeticoesUpdt.getText().toString();
                            String carga = edtCargaUpdt.getText().toString();
                            String repeticoes2 = edtRepeticoes2Updt.getText().toString();
                            String carga2 = edtCarga2Updt.getText().toString();
                            String repeticoes3 = edtRepeticoes3Updt.getText().toString();
                            String carga3 = edtCarga3Updt.getText().toString();
                            String repeticoes4 = edtRepeticoes4Updt.getText().toString();
                            String carga4 = edtCarga4Updt.getText().toString();
                            String repeticoes5 = edtRepeticoes5Updt.getText().toString();
                            String carga5 = edtCarga5Updt.getText().toString();
                            String repeticoes6 = edtRepeticoes6Updt.getText().toString();
                            String carga6 = edtCarga6Updt.getText().toString();
                            String repeticoes7 = edtRepeticoes7Updt.getText().toString();
                            String carga7 = edtCarga7Updt.getText().toString();
                            String repeticoes8 = edtRepeticoes8Updt.getText().toString();
                            String carga8 = edtCarga8Updt.getText().toString();
                            String repeticoes9 = edtRepeticoes9Updt.getText().toString();
                            String carga9 = edtCarga9Updt.getText().toString();
                            String repeticoes10 = edtRepeticoes10Updt.getText().toString();
                            String carga10 = edtCarga10Updt.getText().toString();

                            String spinnerMusculo = musculospin;
                            String iduser = txtIDUserExercUpdt.getText().toString();
                            String idmusc = database.buscarIDMusc(txtDiaDaSemanaUdpt.getText().toString(), musculospin, iduser);

                            if (musc.isEmpty()) {
                                Toast.makeText(v.getContext(), "Musculo, campo obrigatório.", Toast.LENGTH_SHORT).show();
                            } else if (Exerc.isEmpty()) {
                                Toast.makeText(v.getContext(), "Exercicio, campo obrigatório.", Toast.LENGTH_SHORT).show();
                            } else if (sequencia.isEmpty()) {
                                Toast.makeText(v.getContext(), "Ordem, campo obrigatório.", Toast.LENGTH_SHORT).show();
                            } else if (series.isEmpty()) {
                                Toast.makeText(v.getContext(), "Series, campo obrigatório.", Toast.LENGTH_SHORT).show();
                            } else if (repeticoes.isEmpty()) {
                                Toast.makeText(v.getContext(), "Repetições, campo obrigatório.", Toast.LENGTH_SHORT).show();
                            } else {

                                if (musc.equals(spinnerMusculo)) {
                                    try {
                                        //DataBase database = new DataBase(v.getContext());

                                        database.updateExercicio(spinnerMusculo, Exerc, sequencia, series, repeticoes, carga, repeticoes2, carga2, repeticoes3, carga3, repeticoes4, carga4, repeticoes5, carga5
                                                , repeticoes6, carga6, repeticoes7, carga7, repeticoes8, carga8, repeticoes9, carga9, repeticoes10, carga10, idmusc, id);

                                  /*  bancoDados = openOrCreateDatabase("FICHADETREINOS", null, null);
                                    String sql = "UPDATE TREINO SET MUSCULO=?, EXERCICIO=?, SEQUENCIA=?, SERIES=?, REPETICOES=?, CARGA=?, REPETICOES2=?, CARGA2=?, REPETICOES3=?, CARGA3=?" +
                                            ", REPETICOES4=?, CARGA4=?, REPETICOES5=?, CARGA5=?, REPETICOES6=?, CARGA6=?, REPETICOES7=?, CARGA7=?, REPETICOES8=?, CARGA8=?, REPETICOES9=?, CARGA9=? " +
                                            ", REPETICOES10=?, CARGA10=? WHERE ID=?";

                                    SQLiteStatement stmt = bancoDados.compileStatement(sql);

                                    stmt.bindString(1,musc);
                                    stmt.bindString(2,Exerc);
                                    stmt.bindString(3,sequencia);
                                    stmt.bindString(4,series);
                                    stmt.bindString(5,repeticoes);
                                    stmt.bindString(6,carga);
                                    stmt.bindString(7,repeticoes2);
                                    stmt.bindString(8,carga2);
                                    stmt.bindString(9,repeticoes3);
                                    stmt.bindString(10,carga3);
                                    stmt.bindString(11,repeticoes4);
                                    stmt.bindString(12,carga4);
                                    stmt.bindString(13,repeticoes5);
                                    stmt.bindString(14,carga5);
                                    stmt.bindString(15,repeticoes6);
                                    stmt.bindString(16,carga6);
                                    stmt.bindString(17,repeticoes7);
                                    stmt.bindString(18,carga7);
                                    stmt.bindString(19,repeticoes8);
                                    stmt.bindString(20,carga8);
                                    stmt.bindString(21,repeticoes9);
                                    stmt.bindString(22,carga9);
                                    stmt.bindString(23,repeticoes10);
                                    stmt.bindString(24,carga10);
                                    stmt.bindString(25,id);


                                    stmt.executeUpdateDelete();
                                    bancoDados.close();*/

                                        //holder.txtExercicio.setText(Exerc);

                                        dialog.dismiss();


                                        DataBase dataBase = new DataBase(v.getContext());

                                        // musculoList = dataBase.buscar_Musculo(spinnerMusculo, iduser);
                                        //notifyItemChanged(position);
                                        //notifyItemChanged(position, listexercicio.size());

                                        notifyDataSetChanged();

                                        listexercicio = dataBase.buscarExercicio(holder.txtDiaDaSemana.getText().toString(), holder.txtIDMuscExerc.getText().toString(),
                                             holder.txtIDUserMuscExerc.getText().toString());

                                        //notifyDataSetChanged();

                                        //notifyItemChanged(position, listexercicio.size());

                                        Toast.makeText(v.getContext(), "Atualizado", Toast.LENGTH_SHORT).show();


                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtExercicioUpdt.getWindowToken(), 0);
                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtRepeticoesUpdt.getWindowToken(), 0);
                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtCargaUpdt.getWindowToken(), 0);
                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtRepeticoes2Updt.getWindowToken(), 0);
                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtCarga2Updt.getWindowToken(), 0);
                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtRepeticoes3Updt.getWindowToken(), 0);
                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtCarga3Updt.getWindowToken(), 0);
                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtRepeticoes4Updt.getWindowToken(), 0);
                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtCarga4Updt.getWindowToken(), 0);


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {


                                        database.updateExercicio(spinnerMusculo, Exerc, sequencia, series, repeticoes, carga, repeticoes2, carga2, repeticoes3, carga3, repeticoes4, carga4, repeticoes5, carga5
                                                , repeticoes6, carga6, repeticoes7, carga7, repeticoes8, carga8, repeticoes9, carga9, repeticoes10, carga10, idmusc, id);


                                        dialog.dismiss();

                                        ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                edtExercicioUpdt.getWindowToken(), 0);

                                        Toast.makeText(v.getContext(), "Atualizado", Toast.LENGTH_SHORT).show();

                                        btnRefreshAdpapter.performClick();


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }


                            }
                        }
                    });

                    btnVoltarUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

            });

            holder.btnDeleteExer.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final View view = v;
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.title_adapterlistas_dellista)
                            .setMessage(R.string.msg_adapterlistas_dellista_exercicio)
                            .setPositiveButton(R.string.txt_adapterlistas_dellista, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DataBase reposi = new DataBase(view.getContext());
                                    reposi.excluirExercicio(exercicio.getID());
                                    removerCliente(exercicio);
                                    Snackbar.make(view, R.string.action_adapterlistas_dellista, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }
                            })
                            .setNegativeButton(R.string.txtcancel_adapterlistas_dellista, null)
                            .create()
                            .show();

                }
            });

            holder.check1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check1.isChecked()) {
                        reposi.Check1(exercicio.ID);

                        holder.linearSerie2.setVisibility(View.VISIBLE);

                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


                    } else {
                        reposi.noCheck1(exercicio.ID);

                        holder.linearSerie2.setVisibility(View.GONE);
                        holder.linearSerie3.setVisibility(View.GONE);
                        holder.linearSerie4.setVisibility(View.GONE);
                        holder.linearSerie5.setVisibility(View.GONE);
                        holder.linearSerie6.setVisibility(View.GONE);
                        holder.linearSerie7.setVisibility(View.GONE);
                        holder.linearSerie8.setVisibility(View.GONE);
                        holder.linearSerie9.setVisibility(View.GONE);
                        holder.linearSerie10.setVisibility(View.GONE);

                        if (holder.check2.isChecked()) {
                            holder.check2.performClick();
                        } else if (holder.check3.isChecked()) {
                            holder.check3.performClick();
                        } else if (holder.check4.isChecked()) {
                            holder.check4.performClick();
                        } else if (holder.check5.isChecked()) {
                            holder.check5.performClick();
                        } else if (holder.check6.isChecked()) {
                            holder.check6.performClick();
                        } else if (holder.check7.isChecked()) {
                            holder.check7.performClick();
                        } else if (holder.check8.isChecked()) {
                            holder.check8.performClick();
                        } else if (holder.check9.isChecked()) {
                            holder.check9.performClick();
                        } else if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }

                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check2.isChecked()) {
                        reposi.Check2(exercicio.ID);

                        holder.linearSerie3.setVisibility(View.VISIBLE);

                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        reposi.noCheck2(exercicio.ID);

                        holder.linearSerie3.setVisibility(View.GONE);
                        holder.linearSerie4.setVisibility(View.GONE);
                        holder.linearSerie5.setVisibility(View.GONE);
                        holder.linearSerie6.setVisibility(View.GONE);
                        holder.linearSerie7.setVisibility(View.GONE);
                        holder.linearSerie8.setVisibility(View.GONE);
                        holder.linearSerie9.setVisibility(View.GONE);
                        holder.linearSerie10.setVisibility(View.GONE);

                        if (holder.check3.isChecked()) {
                            holder.check3.performClick();
                        } else if (holder.check4.isChecked()) {
                            holder.check4.performClick();
                        } else if (holder.check5.isChecked()) {
                            holder.check5.performClick();
                        } else if (holder.check6.isChecked()) {
                            holder.check6.performClick();
                        } else if (holder.check7.isChecked()) {
                            holder.check7.performClick();
                        } else if (holder.check8.isChecked()) {
                            holder.check8.performClick();
                        } else if (holder.check9.isChecked()) {
                            holder.check9.performClick();
                        } else if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }


                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check3.isChecked()) {
                        reposi.Check3(exercicio.ID);
                        holder.linearSerie4.setVisibility(View.VISIBLE);
                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                    } else {
                        reposi.noCheck3(exercicio.ID);

                        holder.linearSerie4.setVisibility(View.GONE);
                        holder.linearSerie5.setVisibility(View.GONE);
                        holder.linearSerie6.setVisibility(View.GONE);
                        holder.linearSerie7.setVisibility(View.GONE);
                        holder.linearSerie8.setVisibility(View.GONE);
                        holder.linearSerie9.setVisibility(View.GONE);
                        holder.linearSerie10.setVisibility(View.GONE);


                        if (holder.check4.isChecked()) {
                            holder.check4.performClick();
                        } else if (holder.check5.isChecked()) {
                            holder.check5.performClick();
                        } else if (holder.check6.isChecked()) {
                            holder.check6.performClick();
                        } else if (holder.check7.isChecked()) {
                            holder.check7.performClick();
                        } else if (holder.check8.isChecked()) {
                            holder.check8.performClick();
                        } else if (holder.check9.isChecked()) {
                            holder.check9.performClick();
                        } else if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }
                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check4.isChecked()) {
                        reposi.Check4(exercicio.ID);
                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        holder.linearSerie5.setVisibility(View.VISIBLE);

                    } else {
                        reposi.noCheck4(exercicio.ID);

                        holder.linearSerie5.setVisibility(View.GONE);
                        holder.linearSerie6.setVisibility(View.GONE);
                        holder.linearSerie7.setVisibility(View.GONE);
                        holder.linearSerie8.setVisibility(View.GONE);
                        holder.linearSerie9.setVisibility(View.GONE);
                        holder.linearSerie10.setVisibility(View.GONE);

                        if (holder.check5.isChecked()) {
                            holder.check5.performClick();
                        } else if (holder.check6.isChecked()) {
                            holder.check6.performClick();
                        } else if (holder.check7.isChecked()) {
                            holder.check7.performClick();
                        } else if (holder.check8.isChecked()) {
                            holder.check8.performClick();
                        } else if (holder.check9.isChecked()) {
                            holder.check9.performClick();
                        } else if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }
                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check5.isChecked()) {
                        reposi.Check5(exercicio.ID);

                        holder.linearSerie6.setVisibility(View.VISIBLE);
                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


                    } else {
                        reposi.noCheck5(exercicio.ID);

                        holder.linearSerie6.setVisibility(View.GONE);
                        holder.linearSerie7.setVisibility(View.GONE);
                        holder.linearSerie8.setVisibility(View.GONE);
                        holder.linearSerie9.setVisibility(View.GONE);
                        holder.linearSerie10.setVisibility(View.GONE);

                        if (holder.check6.isChecked()) {
                            holder.check6.performClick();
                        } else if (holder.check7.isChecked()) {
                            holder.check7.performClick();
                        } else if (holder.check8.isChecked()) {
                            holder.check8.performClick();
                        } else if (holder.check9.isChecked()) {
                            holder.check9.performClick();
                        } else if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }
                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check6.isChecked()) {
                        reposi.Check6(exercicio.ID);
                        holder.linearSerie7.setVisibility(View.VISIBLE);
                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        reposi.noCheck6(exercicio.ID);

                        holder.linearSerie7.setVisibility(View.GONE);
                        holder.linearSerie8.setVisibility(View.GONE);
                        holder.linearSerie9.setVisibility(View.GONE);
                        holder.linearSerie10.setVisibility(View.GONE);

                        if (holder.check7.isChecked()) {
                            holder.check7.performClick();
                        } else if (holder.check8.isChecked()) {
                            holder.check8.performClick();
                        } else if (holder.check9.isChecked()) {
                            holder.check9.performClick();
                        } else if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }
                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check7.isChecked()) {
                        reposi.Check7(exercicio.ID);
                        holder.linearSerie8.setVisibility(View.VISIBLE);
                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        reposi.noCheck7(exercicio.ID);

                        holder.linearSerie8.setVisibility(View.GONE);
                        holder.linearSerie9.setVisibility(View.GONE);
                        holder.linearSerie10.setVisibility(View.GONE);

                        if (holder.check8.isChecked()) {
                            holder.check8.performClick();
                        } else if (holder.check9.isChecked()) {
                            holder.check9.performClick();
                        } else if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }
                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check8.isChecked()) {
                        reposi.Check8(exercicio.ID);
                        holder.linearSerie9.setVisibility(View.VISIBLE);
                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        reposi.noCheck8(exercicio.ID);

                        holder.linearSerie9.setVisibility(View.GONE);
                        holder.linearSerie10.setVisibility(View.GONE);

                        if (holder.check9.isChecked()) {
                            holder.check9.performClick();
                        } else if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }
                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check9.isChecked()) {
                        reposi.Check9(exercicio.ID);
                        holder.linearSerie10.setVisibility(View.VISIBLE);
                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        reposi.noCheck9(exercicio.ID);

                        holder.linearSerie10.setVisibility(View.GONE);

                        if (holder.check10.isChecked()) {
                            holder.check10.performClick();
                        }
                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.check10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    DataBase reposi = new DataBase(view.getContext());
                    if (holder.check10.isChecked()) {
                        reposi.Check10(exercicio.ID);
                        // holder.txtNome.setPaintFlags( holder.txtNome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        reposi.noCheck10(exercicio.ID);
                        // holder.txtNome.setPaintFlags(0);
                        // funcoesTotaisCheckNoCheck();
                    }
                }
            });

            holder.visualizarRepCarg();

            holder.visualizadorCheck();

            holder.check1.setChecked(listexercicio.get(position).isCHECK1());

            holder.check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK1) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK1(CHECK1);
                }
            });

            holder.check2.setChecked(listexercicio.get(position).isCHECK2());

            holder.check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK2) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK2(CHECK2);
                }
            });

            holder.check3.setChecked(listexercicio.get(position).isCHECK3());

            holder.check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK3) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK3(CHECK3);
                }
            });

            holder.check4.setChecked(listexercicio.get(position).isCHECK4());

            holder.check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK4) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK4(CHECK4);
                }
            });

            holder.check5.setChecked(listexercicio.get(position).isCHECK5());

            holder.check5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK5) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK5(CHECK5);
                }
            });

            holder.check6.setChecked(listexercicio.get(position).isCHECK6());

            holder.check6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK6) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK6(CHECK6);
                }
            });

            holder.check7.setChecked(listexercicio.get(position).isCHECK7());

            holder.check7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK7) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK7(CHECK7);
                }
            });

            holder.check8.setChecked(listexercicio.get(position).isCHECK8());

            holder.check8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK8) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK8(CHECK8);
                }
            });

            holder.check9.setChecked(listexercicio.get(position).isCHECK9());

            holder.check9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK9) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK9(CHECK9);
                }
            });

            holder.check10.setChecked(listexercicio.get(position).isCHECK10());

            holder.check10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean CHECK10) {

                    listexercicio.get(holder.getAdapterPosition()).setCHECK10(CHECK10);
                }
            });


          /*  holder.btnDelete.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.title_adapterlistas_dellista)
                            .setMessage(R.string.msg_adapterlistas_dellista)
                            .setPositiveButton(R.string.txt_adapterlistas_dellista, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DataBase reposi = new DataBase(view.getContext());
                                    reposi.excluir_RG_Med(medidas2.getID());
                                    removerCliente(medidas2);
                                    Snackbar.make(view, R.string.action_adapterlistas_dellista, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }
                            })
                            .setNegativeButton(R.string.txtcancel_adapterlistas_dellista, null)
                            .create()
                            .show();
                }
            });*/

        }

    }

    @Override
    public int getItemCount() {
        return listexercicio.size();
    }

    public static class ViewHolderAdapterMedidas extends RecyclerView.ViewHolder {

        public TextView txtIdExer, txtExercicio, txtSequencia, txtSeries, txtRep1, txtCarga1, txtRep2, txtCarga2, txtRep3, txtCarga3, txtRep4, txtCarga4;
        public TextView txtRep5, txtCarga5, txtRep6, txtCarga6, txtRep7, txtCarga7, txtRep8, txtCarga8, txtRep9, txtCarga9, txtRep10, txtCarga10;
        public TextView txtDiaDaSemana, txtIDMuscExerc, txtIDUserMuscExerc;
        public TextView txtContarClick, txtMusculoEx;
        public LinearLayout Linear1, Linear2, Linear3, Linear4, Linear5, Linear6, Linear7, Linear8, Linear9, Linear10;

        public LinearLayout linearSerie1, linearSerie2, linearSerie3, linearSerie4, linearSerie5;
        public LinearLayout linearSerie6, linearSerie7, linearSerie8, linearSerie9, linearSerie10;
        public static LinearLayout linearLinhaExerc;
        public  Button btnCronometro, btnAtualizarLista;
        //public int checkserie = 0;
        public CheckBox check1, check2, check3, check4, check5, check6, check7, check8, check9, check10;
        public static CardView btnDeleteExer, btnUpdateExer, btnPesquisar;

        public ViewHolderAdapterMedidas(View itemView, final Context context) {
            super(itemView);

            txtIdExer = (TextView) itemView.findViewById(R.id.txtIdExer);
            txtExercicio = (TextView) itemView.findViewById(R.id.txtExercicio);
            txtMusculoEx = (TextView) itemView.findViewById(R.id.txtMusculoEx);
            txtSequencia = (TextView) itemView.findViewById(R.id.txtSequencia);
            txtSeries = (TextView) itemView.findViewById(R.id.txtSeries);
            //txtCheckteste = (TextView) itemView.findViewById(R.id.txtCheckteste);

            txtRep1 = (TextView) itemView.findViewById(R.id.txtRep1);
            txtCarga1 = (TextView) itemView.findViewById(R.id.txtCarga1);
            txtRep2 = (TextView) itemView.findViewById(R.id.txtRep2);
            txtCarga2 = (TextView) itemView.findViewById(R.id.txtCarga2);
            txtRep3 = (TextView) itemView.findViewById(R.id.txtRep3);
            txtCarga3 = (TextView) itemView.findViewById(R.id.txtCarga3);
            txtRep4 = (TextView) itemView.findViewById(R.id.txtRep4);
            txtCarga4 = (TextView) itemView.findViewById(R.id.txtCarga4);
            txtRep5 = (TextView) itemView.findViewById(R.id.txtRep5);
            txtCarga5 = (TextView) itemView.findViewById(R.id.txtCarga5);
            txtRep6 = (TextView) itemView.findViewById(R.id.txtRep6);
            txtCarga6 = (TextView) itemView.findViewById(R.id.txtCarga6);
            txtRep7 = (TextView) itemView.findViewById(R.id.txtRep7);
            txtCarga7 = (TextView) itemView.findViewById(R.id.txtCarga7);
            txtRep8 = (TextView) itemView.findViewById(R.id.txtRep8);
            txtCarga8 = (TextView) itemView.findViewById(R.id.txtCarga8);
            txtRep9 = (TextView) itemView.findViewById(R.id.txtRep9);
            txtCarga9 = (TextView) itemView.findViewById(R.id.txtCarga9);
            txtRep10 = (TextView) itemView.findViewById(R.id.txtRep10);
            txtCarga10 = (TextView) itemView.findViewById(R.id.txtCarga10);
            txtDiaDaSemana = (TextView) itemView.findViewById(R.id.txtDiaDaSemana);
            txtContarClick = (TextView) itemView.findViewById(R.id.txtContarClick);

            txtIDMuscExerc = (TextView) itemView.findViewById(R.id.txtIDMuscExerc);
            txtIDUserMuscExerc = (TextView) itemView.findViewById(R.id.txtIDUserMuscExerc);

            Linear1 = (LinearLayout) itemView.findViewById(R.id.Linear1);
            Linear2 = (LinearLayout) itemView.findViewById(R.id.Linear2);
            Linear3 = (LinearLayout) itemView.findViewById(R.id.Linear3);
            Linear4 = (LinearLayout) itemView.findViewById(R.id.Linear4);
            Linear5 = (LinearLayout) itemView.findViewById(R.id.Linear5);
            Linear6 = (LinearLayout) itemView.findViewById(R.id.Linear6);
            Linear7 = (LinearLayout) itemView.findViewById(R.id.Linear7);
            Linear8 = (LinearLayout) itemView.findViewById(R.id.Linear8);
            Linear9 = (LinearLayout) itemView.findViewById(R.id.Linear9);
            Linear10 = (LinearLayout) itemView.findViewById(R.id.Linear10);

            linearSerie1 = (LinearLayout) itemView.findViewById(R.id.linearSerie1);
            linearSerie2 = (LinearLayout) itemView.findViewById(R.id.linearSerie2);
            linearSerie3 = (LinearLayout) itemView.findViewById(R.id.linearSerie3);
            linearSerie4 = (LinearLayout) itemView.findViewById(R.id.linearSerie4);
            linearSerie5 = (LinearLayout) itemView.findViewById(R.id.linearSerie5);
            linearSerie6 = (LinearLayout) itemView.findViewById(R.id.linearSerie6);
            linearSerie7 = (LinearLayout) itemView.findViewById(R.id.linearSerie7);
            linearSerie8 = (LinearLayout) itemView.findViewById(R.id.linearSerie8);
            linearSerie9 = (LinearLayout) itemView.findViewById(R.id.linearSerie9);
            linearSerie10 = (LinearLayout) itemView.findViewById(R.id.linearSerie10);

            linearLinhaExerc = (LinearLayout) itemView.findViewById(R.id.linearLinhaExerc);

            btnCronometro = (Button) itemView.findViewById(R.id.btnCronometro);
            btnAtualizarLista = (Button) itemView.findViewById(R.id.btnAtualizarLista);

            btnDeleteExer = (CardView) itemView.findViewById(R.id.btnDeleteExer);
            btnUpdateExer = (CardView) itemView.findViewById(R.id.btnUpdateExer);
            btnPesquisar = (CardView) itemView.findViewById(R.id.btnPesquisar);

            check1 = (CheckBox) itemView.findViewById(R.id.check1);
            check2 = (CheckBox) itemView.findViewById(R.id.check2);
            check3 = (CheckBox) itemView.findViewById(R.id.check3);
            check4 = (CheckBox) itemView.findViewById(R.id.check4);
            check5 = (CheckBox) itemView.findViewById(R.id.check5);
            check6 = (CheckBox) itemView.findViewById(R.id.check6);
            check7 = (CheckBox) itemView.findViewById(R.id.check7);
            check8 = (CheckBox) itemView.findViewById(R.id.check8);
            check9 = (CheckBox) itemView.findViewById(R.id.check9);
            check10 = (CheckBox) itemView.findViewById(R.id.check10);

            btnCronometro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //contadorSerieCheck();
                    // txtContarClick.setText(String.valueOf(checkserie));

                    if (listexercicio.size() > 0) {

                        contCheck();

                        Exercicio med = listexercicio.get(getLayoutPosition());

                        //btnCronometro.setEnabled(false);

                        /*Intent it = new Intent(context, Temporizador.class);
                        it.putExtra("EXER_TEMP", med);
                        ((AppCompatActivity) context).startActivity(it);*/

                        Treinos.btnIniciarTemp.performClick();

                      /*  new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                               //btnCronometro.setClickable(true);

                            }
                        }, 6000);*/

                    }


                }
            });

            btnUpdateExer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listexercicio.size() >= 0) {

                        Dialog dialog = new Dialog(v.getContext());
                        dialog.setContentView(R.layout.update_exercicio);
                        dialog.setCancelable(false);
                        String compareValue = null;

                        Exercicio exercicio = listexercicio.get(getLayoutPosition());

                        EditText edtIdTreinoUpdt = dialog.findViewById(R.id.edtIdTreinoUpdt);
                        EditText edtMusculoExercUpdt = dialog.findViewById(R.id.edtMusculoExercUpdt);
                        EditText edtExercicioUpdt = dialog.findViewById(R.id.edtExercicioUpdt);
                        EditText edtSequenciaExercUpdt = dialog.findViewById(R.id.edtSequenciaExercUpdt);
                        EditText edtSeriesUpdt = dialog.findViewById(R.id.edtSeriesUpdt);
                        EditText edtRepeticoesUpdt = dialog.findViewById(R.id.edtRepeticoesUpdt);
                        EditText edtCargaUpdt = dialog.findViewById(R.id.edtCargaUpdt);
                        EditText edtRepeticoes2Updt = dialog.findViewById(R.id.edtRepeticoes2Updt);
                        EditText edtCarga2Updt = dialog.findViewById(R.id.edtCarga2Updt);
                        EditText edtRepeticoes3Updt = dialog.findViewById(R.id.edtRepeticoes3Updt);
                        EditText edtCarga3Updt = dialog.findViewById(R.id.edtCarga3Updt);
                        EditText edtRepeticoes4Updt = dialog.findViewById(R.id.edtRepeticoes4Updt);
                        EditText edtCarga4Updt = dialog.findViewById(R.id.edtCarga4Updt);
                        EditText edtRepeticoes5Updt = dialog.findViewById(R.id.edtRepeticoes5Updt);
                        EditText edtCarga5Updt = dialog.findViewById(R.id.edtCarga5Updt);
                        EditText edtRepeticoes6Updt = dialog.findViewById(R.id.edtRepeticoes6Updt);
                        EditText edtCarga6Updt = dialog.findViewById(R.id.edtCarga6Updt);
                        EditText edtRepeticoes7Updt = dialog.findViewById(R.id.edtRepeticoes7Updt);
                        EditText edtCarga7Updt = dialog.findViewById(R.id.edtCarga7Updt);
                        EditText edtRepeticoes8Updt = dialog.findViewById(R.id.edtRepeticoes8Updt);
                        EditText edtCarga8Updt = dialog.findViewById(R.id.edtCarga8Updt);
                        EditText edtRepeticoes9Updt = dialog.findViewById(R.id.edtRepeticoes9Updt);
                        EditText edtCarga9Updt = dialog.findViewById(R.id.edtCarga9Updt);
                        EditText edtRepeticoes10Updt = dialog.findViewById(R.id.edtRepeticoes10Updt);
                        EditText edtCarga10Updt = dialog.findViewById(R.id.edtCarga10Updt);

                        TextView txtDiaDaSemanaUdpt = dialog.findViewById(R.id.txtDiaDaSemanaUdpt);
                        TextView txtIDUserExercUpdt = dialog.findViewById(R.id.txtIDUserExercUpdt);
                        TextView txtIDMuscExercUpdt = dialog.findViewById(R.id.txtIDMuscExercUpdt);

                        Button btnAtualizarUpdate = dialog.findViewById(R.id.btnAtualizarUpdate);

                        CardView btnVoltarUpdate = dialog.findViewById(R.id.btnVoltarUpdate);

                        ConstraintLayout constrLayRepCarg2Updt = dialog.findViewById(R.id.constrLayRepCarg2Updt);
                        ConstraintLayout constrLayRepCarg3Updt = dialog.findViewById(R.id.constrLayRepCarg3Updt);
                        ConstraintLayout constrLayRepCarg4Updt = dialog.findViewById(R.id.constrLayRepCarg4Updt);
                        ConstraintLayout constrLayRepCarg5Updt = dialog.findViewById(R.id.constrLayRepCarg5Updt);
                        ConstraintLayout constrLayRepCarg6Updt = dialog.findViewById(R.id.constrLayRepCarg6Updt);
                        ConstraintLayout constrLayRepCarg7Updt = dialog.findViewById(R.id.constrLayRepCarg7Updt);
                        ConstraintLayout constrLayRepCarg8Updt = dialog.findViewById(R.id.constrLayRepCarg8Updt);
                        ConstraintLayout constrLayRepCarg9Updt = dialog.findViewById(R.id.constrLayRepCarg9Updt);
                        ConstraintLayout constrLayRepCarg10Updt = dialog.findViewById(R.id.constrLayRepCarg10Updt);

                        Spinner spinnerMusculoExercUpdt = dialog.findViewById(R.id.spinnerMusculoExercUpdt);

                        /*edtIdTreinoUpdt.setText(txtIdExer.getText().toString());
                        edtMusculoExercUpdt.setText(txtMusculoEx.getText().toString());
                        edtExercicioUpdt.setText(txtExercicio.getText().toString());
                        edtSequenciaExercUpdt.setText(txtSequencia.getText().toString());
                        edtSeriesUpdt.setText(txtSeries.getText().toString());
                        edtRepeticoesUpdt.setText(txtRep1.getText().toString());
                        edtCargaUpdt.setText(txtCarga1.getText().toString());
                        edtRepeticoes2Updt.setText(txtRep2.getText().toString());
                        edtCarga2Updt.setText(txtCarga2.getText().toString());
                        edtRepeticoes3Updt.setText(txtRep3.getText().toString());
                        edtCarga3Updt.setText(txtCarga3.getText().toString());
                        edtRepeticoes4Updt.setText(txtRep4.getText().toString());
                        edtCarga4Updt.setText(txtCarga4.getText().toString());
                        edtRepeticoes5Updt.setText(txtRep5.getText().toString());
                        edtCarga5Updt.setText(txtCarga5.getText().toString());
                        edtRepeticoes6Updt.setText(txtRep6.getText().toString());
                        edtCarga6Updt.setText(txtCarga6.getText().toString());
                        edtRepeticoes7Updt.setText(txtRep7.getText().toString());
                        edtCarga7Updt.setText(txtCarga7.getText().toString());
                        edtRepeticoes8Updt.setText(txtRep8.getText().toString());
                        edtCarga8Updt.setText(txtCarga8.getText().toString());
                        edtRepeticoes9Updt.setText(txtRep9.getText().toString());
                        edtCarga9Updt.setText(txtCarga9.getText().toString());
                        edtRepeticoes10Updt.setText(txtRep10.getText().toString());
                        edtCarga10Updt.setText(txtCarga10.getText().toString());
                        txtDiaDaSemanaUdpt.setText(txtDiaDaSemana.getText().toString());
                        txtIDMuscExercUpdt.setText(txtIDMuscExerc.getText().toString());
                        txtIDUserExercUpdt.setText(txtIDUserMuscExerc.getText().toString());*/

                        edtIdTreinoUpdt.setText(String.valueOf(exercicio.ID));
                        edtMusculoExercUpdt.setText(exercicio.MUSCULO);
                        edtExercicioUpdt.setText(exercicio.EXERCICIO);
                        edtSequenciaExercUpdt.setText(exercicio.SEQUENCIA);
                        edtSeriesUpdt.setText(String.valueOf(exercicio.SERIES));
                        edtRepeticoesUpdt.setText(String.valueOf(exercicio.REPETICOES));
                        edtCargaUpdt.setText(String.valueOf(exercicio.CARGA));
                        edtRepeticoes2Updt.setText(String.valueOf(exercicio.REPETICOES2));
                        edtCarga2Updt.setText(String.valueOf(exercicio.CARGA2));
                        edtRepeticoes3Updt.setText(String.valueOf(exercicio.REPETICOES3));
                        edtCarga3Updt.setText(String.valueOf(exercicio.CARGA3));
                        edtRepeticoes4Updt.setText(String.valueOf(exercicio.REPETICOES4));
                        edtCarga4Updt.setText(String.valueOf(exercicio.CARGA4));
                        edtRepeticoes5Updt.setText(String.valueOf(exercicio.REPETICOES5));
                        edtCarga5Updt.setText(String.valueOf(exercicio.CARGA5));
                        edtRepeticoes6Updt.setText(String.valueOf(exercicio.REPETICOES6));
                        edtCarga6Updt.setText(String.valueOf(exercicio.CARGA6));
                        edtRepeticoes7Updt.setText(String.valueOf(exercicio.REPETICOES7));
                        edtCarga7Updt.setText(String.valueOf(exercicio.CARGA7));
                        edtRepeticoes8Updt.setText(String.valueOf(exercicio.REPETICOES8));
                        edtCarga8Updt.setText(String.valueOf(exercicio.CARGA8));
                        edtRepeticoes9Updt.setText(String.valueOf(exercicio.REPETICOES9));
                        edtCarga9Updt.setText(String.valueOf(exercicio.CARGA9));
                        edtRepeticoes10Updt.setText(String.valueOf(exercicio.REPETICOES10));
                        edtCarga10Updt.setText(String.valueOf(exercicio.CARGA10));
                        txtDiaDaSemanaUdpt.setText(exercicio.DIADASEMANA);
                        txtIDMuscExercUpdt.setText(exercicio.IDMUSC);
                        txtIDUserExercUpdt.setText(exercicio.IDUSER);

                        DataBase database = new DataBase(v.getContext());

                        compareValue = edtMusculoExercUpdt.getText().toString();

                        ArrayAdapter<String> adapter_spinner_NomeMusculoEx = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1,
                                database.buscarNomeMusculoSippner(txtDiaDaSemanaUdpt.getText().toString(), txtIDUserExercUpdt.getText().toString()));
                        spinnerMusculoExercUpdt.setAdapter(adapter_spinner_NomeMusculoEx);

                        spinnerMusculoExercUpdt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                musculospin = parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        spinnerMusculoExercUpdt.setAdapter(adapter_spinner_NomeMusculoEx);

                        if (!compareValue.equals(null)) {
                            int spinnerPosition = adapter_spinner_NomeMusculoEx.getPosition(compareValue);
                            spinnerMusculoExercUpdt.setSelection(spinnerPosition);
                            //spinnerPosition = 0;
                        }

                        String series = edtSeriesUpdt.getText().toString();

                        if (Integer.parseInt(series) == 0) {
                            constrLayRepCarg2Updt.setVisibility(View.GONE);
                            constrLayRepCarg3Updt.setVisibility(View.GONE);
                            constrLayRepCarg4Updt.setVisibility(View.GONE);
                            constrLayRepCarg5Updt.setVisibility(View.GONE);
                            constrLayRepCarg6Updt.setVisibility(View.GONE);
                            constrLayRepCarg7Updt.setVisibility(View.GONE);
                            constrLayRepCarg8Updt.setVisibility(View.GONE);
                            constrLayRepCarg9Updt.setVisibility(View.GONE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);


                        } else if (Integer.parseInt(series) == 2) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.GONE);
                            constrLayRepCarg4Updt.setVisibility(View.GONE);
                            constrLayRepCarg5Updt.setVisibility(View.GONE);
                            constrLayRepCarg6Updt.setVisibility(View.GONE);
                            constrLayRepCarg7Updt.setVisibility(View.GONE);
                            constrLayRepCarg8Updt.setVisibility(View.GONE);
                            constrLayRepCarg9Updt.setVisibility(View.GONE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);

                        } else if (Integer.parseInt(series) == 3) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg4Updt.setVisibility(View.GONE);
                            constrLayRepCarg5Updt.setVisibility(View.GONE);
                            constrLayRepCarg6Updt.setVisibility(View.GONE);
                            constrLayRepCarg7Updt.setVisibility(View.GONE);
                            constrLayRepCarg8Updt.setVisibility(View.GONE);
                            constrLayRepCarg9Updt.setVisibility(View.GONE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);

                        } else if (Integer.parseInt(series) == 4) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg5Updt.setVisibility(View.GONE);
                            constrLayRepCarg6Updt.setVisibility(View.GONE);
                            constrLayRepCarg7Updt.setVisibility(View.GONE);
                            constrLayRepCarg8Updt.setVisibility(View.GONE);
                            constrLayRepCarg9Updt.setVisibility(View.GONE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);

                        } else if (Integer.parseInt(series) == 5) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg6Updt.setVisibility(View.GONE);
                            constrLayRepCarg7Updt.setVisibility(View.GONE);
                            constrLayRepCarg8Updt.setVisibility(View.GONE);
                            constrLayRepCarg9Updt.setVisibility(View.GONE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);

                        } else if (Integer.parseInt(series) == 6) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg7Updt.setVisibility(View.GONE);
                            constrLayRepCarg8Updt.setVisibility(View.GONE);
                            constrLayRepCarg9Updt.setVisibility(View.GONE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);

                        } else if (Integer.parseInt(series) == 7) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg7Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg8Updt.setVisibility(View.GONE);
                            constrLayRepCarg9Updt.setVisibility(View.GONE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);

                        } else if (Integer.parseInt(series) == 8) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg7Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg8Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg9Updt.setVisibility(View.GONE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);
                        } else if (Integer.parseInt(series) == 9) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg7Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg8Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg9Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg10Updt.setVisibility(View.GONE);
                        } else if (Integer.parseInt(series) == 10) {
                            constrLayRepCarg2Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg3Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg4Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg5Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg6Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg7Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg8Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg9Updt.setVisibility(View.VISIBLE);
                            constrLayRepCarg10Updt.setVisibility(View.VISIBLE);
                        }

                        btnAtualizarUpdate.setOnClickListener(new View.OnClickListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onClick(View v) {

                                String id = edtIdTreinoUpdt.getText().toString();
                                String musc = edtMusculoExercUpdt.getText().toString();
                                String Exerc = edtExercicioUpdt.getText().toString();
                                String sequencia = edtSequenciaExercUpdt.getText().toString();
                                String series = edtSeriesUpdt.getText().toString();
                                String repeticoes = edtRepeticoesUpdt.getText().toString();
                                String carga = edtCargaUpdt.getText().toString();
                                String repeticoes2 = edtRepeticoes2Updt.getText().toString();
                                String carga2 = edtCarga2Updt.getText().toString();
                                String repeticoes3 = edtRepeticoes3Updt.getText().toString();
                                String carga3 = edtCarga3Updt.getText().toString();
                                String repeticoes4 = edtRepeticoes4Updt.getText().toString();
                                String carga4 = edtCarga4Updt.getText().toString();
                                String repeticoes5 = edtRepeticoes5Updt.getText().toString();
                                String carga5 = edtCarga5Updt.getText().toString();
                                String repeticoes6 = edtRepeticoes6Updt.getText().toString();
                                String carga6 = edtCarga6Updt.getText().toString();
                                String repeticoes7 = edtRepeticoes7Updt.getText().toString();
                                String carga7 = edtCarga7Updt.getText().toString();
                                String repeticoes8 = edtRepeticoes8Updt.getText().toString();
                                String carga8 = edtCarga8Updt.getText().toString();
                                String repeticoes9 = edtRepeticoes9Updt.getText().toString();
                                String carga9 = edtCarga9Updt.getText().toString();
                                String repeticoes10 = edtRepeticoes10Updt.getText().toString();
                                String carga10 = edtCarga10Updt.getText().toString();

                                String spinnerMusculo = musculospin;
                                String iduser = txtIDUserExercUpdt.getText().toString();
                                String idmusc = database.buscarIDMusc(txtDiaDaSemanaUdpt.getText().toString(), musculospin, iduser);

                                if (musc.isEmpty()) {
                                    Toast.makeText(v.getContext(), "Musculo, campo obrigatório.", Toast.LENGTH_SHORT).show();
                                } else if (Exerc.isEmpty()) {
                                    Toast.makeText(v.getContext(), "Exercicio, campo obrigatório.", Toast.LENGTH_SHORT).show();
                                } else if (sequencia.isEmpty()) {
                                    Toast.makeText(v.getContext(), "Ordem, campo obrigatório.", Toast.LENGTH_SHORT).show();
                                } else if (series.isEmpty()) {
                                    Toast.makeText(v.getContext(), "Series, campo obrigatório.", Toast.LENGTH_SHORT).show();
                                } else if (repeticoes.isEmpty()) {
                                    Toast.makeText(v.getContext(), "Repetições, campo obrigatório.", Toast.LENGTH_SHORT).show();
                                } else {

                                    if (musc.equals(spinnerMusculo)) {
                                        try {
                                            DataBase database = new DataBase(v.getContext());

                                            database.updateExercicio(spinnerMusculo, Exerc, sequencia, series, repeticoes, carga, repeticoes2, carga2, repeticoes3, carga3, repeticoes4, carga4, repeticoes5, carga5
                                                    , repeticoes6, carga6, repeticoes7, carga7, repeticoes8, carga8, repeticoes9, carga9, repeticoes10, carga10, idmusc, id);

                                  /*  bancoDados = openOrCreateDatabase("FICHADETREINOS", null, null);
                                    String sql = "UPDATE TREINO SET MUSCULO=?, EXERCICIO=?, SEQUENCIA=?, SERIES=?, REPETICOES=?, CARGA=?, REPETICOES2=?, CARGA2=?, REPETICOES3=?, CARGA3=?" +
                                            ", REPETICOES4=?, CARGA4=?, REPETICOES5=?, CARGA5=?, REPETICOES6=?, CARGA6=?, REPETICOES7=?, CARGA7=?, REPETICOES8=?, CARGA8=?, REPETICOES9=?, CARGA9=? " +
                                            ", REPETICOES10=?, CARGA10=? WHERE ID=?";

                                    SQLiteStatement stmt = bancoDados.compileStatement(sql);

                                    stmt.bindString(1,musc);
                                    stmt.bindString(2,Exerc);
                                    stmt.bindString(3,sequencia);
                                    stmt.bindString(4,series);
                                    stmt.bindString(5,repeticoes);
                                    stmt.bindString(6,carga);
                                    stmt.bindString(7,repeticoes2);
                                    stmt.bindString(8,carga2);
                                    stmt.bindString(9,repeticoes3);
                                    stmt.bindString(10,carga3);
                                    stmt.bindString(11,repeticoes4);
                                    stmt.bindString(12,carga4);
                                    stmt.bindString(13,repeticoes5);
                                    stmt.bindString(14,carga5);
                                    stmt.bindString(15,repeticoes6);
                                    stmt.bindString(16,carga6);
                                    stmt.bindString(17,repeticoes7);
                                    stmt.bindString(18,carga7);
                                    stmt.bindString(19,repeticoes8);
                                    stmt.bindString(20,carga8);
                                    stmt.bindString(21,repeticoes9);
                                    stmt.bindString(22,carga9);
                                    stmt.bindString(23,repeticoes10);
                                    stmt.bindString(24,carga10);
                                    stmt.bindString(25,id);


                                    stmt.executeUpdateDelete();
                                    bancoDados.close();*/

                                            //holder.txtExercicio.setText(Exerc);

                                            dialog.dismiss();

                                            // musculoList = dataBase.buscar_Musculo(spinnerMusculo, iduser);
                                            //notifyItemChanged(position);
                                            //notifyItemChanged(position, listexercicio.size());

                                           // adapterRecycleExercicio.notifyItemChanged(getAdapterPosition(), listexercicio.size());

                                            //adapterRecycleExercicio.notifyDataSetChanged();

                                            btnAtualizarLista.performClick();

                                            //notifyDataSetChanged();

                                            //notifyItemChanged(position, listexercicio.size());

                                            Toast.makeText(v.getContext(), "Atualizado", Toast.LENGTH_SHORT).show();


                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtExercicioUpdt.getWindowToken(), 0);
                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtRepeticoesUpdt.getWindowToken(), 0);
                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtCargaUpdt.getWindowToken(), 0);
                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtRepeticoes2Updt.getWindowToken(), 0);
                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtCarga2Updt.getWindowToken(), 0);
                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtRepeticoes3Updt.getWindowToken(), 0);
                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtCarga3Updt.getWindowToken(), 0);
                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtRepeticoes4Updt.getWindowToken(), 0);
                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtCarga4Updt.getWindowToken(), 0);


                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {


                                            database.updateExercicio(spinnerMusculo, Exerc, sequencia, series, repeticoes, carga, repeticoes2, carga2, repeticoes3, carga3, repeticoes4, carga4, repeticoes5, carga5
                                                    , repeticoes6, carga6, repeticoes7, carga7, repeticoes8, carga8, repeticoes9, carga9, repeticoes10, carga10, idmusc, id);


                                            dialog.dismiss();

                                            ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                                    edtExercicioUpdt.getWindowToken(), 0);

                                            Toast.makeText(v.getContext(), "Atualizado", Toast.LENGTH_SHORT).show();

                                            btnRefreshAdpapter.performClick();


                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }


                                }
                            }
                        });

                        btnVoltarUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();

                        // Intent it = new Intent(context, AdapterRecycleExercicio.class);
                       // it.putExtra("UPDT_EXERC", med);
                        //((AppCompatActivity) context).startActivity(it);



                    }
                }
            });

            btnPesquisar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (listexercicio.size() > 0) {
                        String exercicioLinha = txtExercicio.getText().toString();

                        if (!exercicioLinha.equals("")) {
                            try {
                                Intent it = new Intent(Intent.ACTION_WEB_SEARCH);
                                it.putExtra(SearchManager.QUERY, exercicioLinha);
                                ((AppCompatActivity) context).startActivity(it);

                            } catch (ActivityNotFoundException e) {

                                e.printStackTrace();
                                try {
                                    Uri uri = Uri.parse("http://www.google.com/#q=" + exercicioLinha);
                                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                    ((AppCompatActivity) context).startActivity(it);

                                } catch (ActivityNotFoundException e2) {
                                    e2.printStackTrace();
                                    Toast.makeText(context, "Erro!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }


                }
            });

            btnDeleteExer.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Exercicio med = listexercicio.get(getLayoutPosition());

                    final View view = v;
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.title_adapterlistas_dellista)
                            .setMessage(R.string.msg_adapterlistas_dellista_exercicio)
                            .setPositiveButton(R.string.txt_adapterlistas_dellista, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DataBase reposi = new DataBase(view.getContext());
                                    reposi.excluirExercicio(med.getID());
                                    btnAtualizarLista.performClick();
                                    //removerCliente(med);
                                    Snackbar.make(view, R.string.action_adapterlistas_dellista, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }
                            })
                            .setNegativeButton(R.string.txtcancel_adapterlistas_dellista, null)
                            .create()
                            .show();

                }
            });

            /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final View view = v;
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.title_adapterlistas_dellista)
                            .setMessage(R.string.msg_adapterlistas_dellista)
                            .setPositiveButton(R.string.txt_adapterlistas_dellista, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DataBase reposi = new DataBase(view.getContext());
                                    reposi.excluirExercicio(exercicio.getID());
                                    removerCliente(exercicio);
                                    Snackbar.make(view, R.string.action_adapterlistas_dellista, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }
                            })
                            .setNegativeButton(R.string.txtcancel_adapterlistas_dellista, null)
                            .create()
                            .show();

                    return false;
                }

            });*/

            /*btnCopiar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listmedidas.size() > 0 ) {

                        Medidas med = listmedidas.get(getLayoutPosition());

                        Intent it = new Intent(context, ListaDeMedidas.class);
                        it.putExtra("RG_MED", med);
                        ((AppCompatActivity) context).startActivityForResult(it, 0);

                    }
                }
            });

             */


        }

        public void visualizarRepCarg() {

            String series = txtSeries.getText().toString();

            if (Integer.parseInt(series) == 0) {
                Linear1.setVisibility(View.GONE);
                Linear2.setVisibility(View.GONE);
                Linear3.setVisibility(View.GONE);
                Linear4.setVisibility(View.GONE);
                Linear5.setVisibility(View.GONE);
                Linear6.setVisibility(View.GONE);
                Linear7.setVisibility(View.GONE);
                Linear8.setVisibility(View.GONE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 1) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.GONE);
                Linear3.setVisibility(View.GONE);
                Linear4.setVisibility(View.GONE);
                Linear5.setVisibility(View.GONE);
                Linear6.setVisibility(View.GONE);
                Linear7.setVisibility(View.GONE);
                Linear8.setVisibility(View.GONE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);
            } else if (Integer.parseInt(series) == 2) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.GONE);
                Linear4.setVisibility(View.GONE);
                Linear5.setVisibility(View.GONE);
                Linear6.setVisibility(View.GONE);
                Linear7.setVisibility(View.GONE);
                Linear8.setVisibility(View.GONE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 3) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.VISIBLE);
                Linear4.setVisibility(View.GONE);
                Linear5.setVisibility(View.GONE);
                Linear6.setVisibility(View.GONE);
                Linear7.setVisibility(View.GONE);
                Linear8.setVisibility(View.GONE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 4) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.VISIBLE);
                Linear4.setVisibility(View.VISIBLE);
                Linear5.setVisibility(View.GONE);
                Linear6.setVisibility(View.GONE);
                Linear7.setVisibility(View.GONE);
                Linear8.setVisibility(View.GONE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 5) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.VISIBLE);
                Linear4.setVisibility(View.VISIBLE);
                Linear5.setVisibility(View.VISIBLE);
                Linear6.setVisibility(View.GONE);
                Linear7.setVisibility(View.GONE);
                Linear8.setVisibility(View.GONE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 6) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.VISIBLE);
                Linear4.setVisibility(View.VISIBLE);
                Linear5.setVisibility(View.VISIBLE);
                Linear6.setVisibility(View.VISIBLE);
                Linear7.setVisibility(View.GONE);
                Linear8.setVisibility(View.GONE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 7) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.VISIBLE);
                Linear4.setVisibility(View.VISIBLE);
                Linear5.setVisibility(View.VISIBLE);
                Linear6.setVisibility(View.VISIBLE);
                Linear7.setVisibility(View.VISIBLE);
                Linear8.setVisibility(View.GONE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);

            } else if (Integer.parseInt(series) == 8) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.VISIBLE);
                Linear4.setVisibility(View.VISIBLE);
                Linear5.setVisibility(View.VISIBLE);
                Linear6.setVisibility(View.VISIBLE);
                Linear7.setVisibility(View.VISIBLE);
                Linear8.setVisibility(View.VISIBLE);
                Linear9.setVisibility(View.GONE);
                Linear10.setVisibility(View.GONE);
            } else if (Integer.parseInt(series) == 9) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.VISIBLE);
                Linear4.setVisibility(View.VISIBLE);
                Linear5.setVisibility(View.VISIBLE);
                Linear6.setVisibility(View.VISIBLE);
                Linear7.setVisibility(View.VISIBLE);
                Linear8.setVisibility(View.VISIBLE);
                Linear9.setVisibility(View.VISIBLE);
                Linear10.setVisibility(View.GONE);
            } else if (Integer.parseInt(series) == 10) {
                Linear1.setVisibility(View.VISIBLE);
                Linear2.setVisibility(View.VISIBLE);
                Linear3.setVisibility(View.VISIBLE);
                Linear4.setVisibility(View.VISIBLE);
                Linear5.setVisibility(View.VISIBLE);
                Linear6.setVisibility(View.VISIBLE);
                Linear7.setVisibility(View.VISIBLE);
                Linear8.setVisibility(View.VISIBLE);
                Linear9.setVisibility(View.VISIBLE);
                Linear10.setVisibility(View.VISIBLE);
            }

        }

        public void contCheck() {

            if (!check1.isChecked() & !check2.isChecked() & !check3.isChecked() & !check4.isChecked() &
                    !check5.isChecked() & !check6.isChecked() & !check7.isChecked() & !check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {

                check1.performClick();
            } else if (check1.isChecked() & !check2.isChecked() & !check3.isChecked() & !check4.isChecked() &
                    !check5.isChecked() & !check6.isChecked() & !check7.isChecked() & !check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {
                check2.performClick();
            } else if (check1.isChecked() & check2.isChecked() & !check3.isChecked() & !check4.isChecked() &
                    !check5.isChecked() & !check6.isChecked() & !check7.isChecked() & !check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {
                check3.performClick();
            } else if (check1.isChecked() & check2.isChecked() & check3.isChecked() & !check4.isChecked() &
                    !check5.isChecked() & !check6.isChecked() & !check7.isChecked() & !check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {
                check4.performClick();
            } else if (check1.isChecked() & check2.isChecked() & check3.isChecked() & check4.isChecked() &
                    !check5.isChecked() & !check6.isChecked() & !check7.isChecked() & !check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {
                check5.performClick();
            } else if (check1.isChecked() & check2.isChecked() & check3.isChecked() & check4.isChecked() &
                    check5.isChecked() & !check6.isChecked() & !check7.isChecked() & !check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {
                check6.performClick();
            } else if (check1.isChecked() & check2.isChecked() & check3.isChecked() & check4.isChecked() &
                    check5.isChecked() & check6.isChecked() & !check7.isChecked() & !check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {
                check7.performClick();
            } else if (check1.isChecked() & check2.isChecked() & check3.isChecked() & check4.isChecked() &
                    check5.isChecked() & check6.isChecked() & check7.isChecked() & !check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {
                check8.performClick();
            } else if (check1.isChecked() & check2.isChecked() & check3.isChecked() & check4.isChecked() &
                    check5.isChecked() & check6.isChecked() & check7.isChecked() & check8.isChecked() &
                    !check9.isChecked() & !check10.isChecked()) {
                check9.performClick();
            } else if (check1.isChecked() & check2.isChecked() & check3.isChecked() & check4.isChecked() &
                    check5.isChecked() & check6.isChecked() & check7.isChecked() & check8.isChecked() &
                    check9.isChecked() & !check10.isChecked()) {
                check10.performClick();
            }

        }

        public void visualizadorCheck() {

            if (check1.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.GONE);
                linearSerie4.setVisibility(View.GONE);
                linearSerie5.setVisibility(View.GONE);
                linearSerie6.setVisibility(View.GONE);
                linearSerie7.setVisibility(View.GONE);
                linearSerie8.setVisibility(View.GONE);
                linearSerie9.setVisibility(View.GONE);
                linearSerie10.setVisibility(View.GONE);
            }
            if (check2.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.VISIBLE);
                linearSerie4.setVisibility(View.GONE);
                linearSerie5.setVisibility(View.GONE);
                linearSerie6.setVisibility(View.GONE);
                linearSerie7.setVisibility(View.GONE);
                linearSerie8.setVisibility(View.GONE);
                linearSerie9.setVisibility(View.GONE);
                linearSerie10.setVisibility(View.GONE);
            }
            if (check3.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.VISIBLE);
                linearSerie4.setVisibility(View.VISIBLE);
                linearSerie5.setVisibility(View.GONE);
                linearSerie6.setVisibility(View.GONE);
                linearSerie7.setVisibility(View.GONE);
                linearSerie8.setVisibility(View.GONE);
                linearSerie9.setVisibility(View.GONE);
                linearSerie10.setVisibility(View.GONE);
            }
            if (check4.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.VISIBLE);
                linearSerie4.setVisibility(View.VISIBLE);
                linearSerie5.setVisibility(View.VISIBLE);
                linearSerie6.setVisibility(View.GONE);
                linearSerie7.setVisibility(View.GONE);
                linearSerie8.setVisibility(View.GONE);
                linearSerie9.setVisibility(View.GONE);
                linearSerie10.setVisibility(View.GONE);
            }
            if (check5.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.VISIBLE);
                linearSerie4.setVisibility(View.VISIBLE);
                linearSerie5.setVisibility(View.VISIBLE);
                linearSerie6.setVisibility(View.VISIBLE);
                linearSerie7.setVisibility(View.GONE);
                linearSerie8.setVisibility(View.GONE);
                linearSerie9.setVisibility(View.GONE);
                linearSerie10.setVisibility(View.GONE);
            }
            if (check6.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.VISIBLE);
                linearSerie4.setVisibility(View.VISIBLE);
                linearSerie5.setVisibility(View.VISIBLE);
                linearSerie6.setVisibility(View.VISIBLE);
                linearSerie7.setVisibility(View.VISIBLE);
                linearSerie8.setVisibility(View.GONE);
                linearSerie9.setVisibility(View.GONE);
                linearSerie10.setVisibility(View.GONE);
            }
            if (check7.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.VISIBLE);
                linearSerie4.setVisibility(View.VISIBLE);
                linearSerie5.setVisibility(View.VISIBLE);
                linearSerie6.setVisibility(View.VISIBLE);
                linearSerie7.setVisibility(View.VISIBLE);
                linearSerie8.setVisibility(View.VISIBLE);
                linearSerie9.setVisibility(View.GONE);
                linearSerie10.setVisibility(View.GONE);
            }
            if (check8.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.VISIBLE);
                linearSerie4.setVisibility(View.VISIBLE);
                linearSerie5.setVisibility(View.VISIBLE);
                linearSerie6.setVisibility(View.VISIBLE);
                linearSerie7.setVisibility(View.VISIBLE);
                linearSerie8.setVisibility(View.VISIBLE);
                linearSerie9.setVisibility(View.VISIBLE);
                linearSerie10.setVisibility(View.GONE);
            }
            if (check9.isChecked()) {
                linearSerie2.setVisibility(View.VISIBLE);
                linearSerie3.setVisibility(View.VISIBLE);
                linearSerie4.setVisibility(View.VISIBLE);
                linearSerie5.setVisibility(View.VISIBLE);
                linearSerie6.setVisibility(View.VISIBLE);
                linearSerie7.setVisibility(View.VISIBLE);
                linearSerie8.setVisibility(View.VISIBLE);
                linearSerie9.setVisibility(View.VISIBLE);
                linearSerie10.setVisibility(View.VISIBLE);
            }

        }

    }

}
