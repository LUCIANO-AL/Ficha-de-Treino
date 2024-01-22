package com.lalp.sist.controle_de_medidas;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.databinding.ActComparativoMedidasBinding;
import com.lalp.sist.controle_de_medidas.databinding.ContentComparativoMedidasBinding;
import com.lalp.sist.controle_de_medidas.dominios.adapter.AdapterComparativo;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;
import com.lalp.sist.controle_de_medidas.dominios.repositorio.Repo_Medida;

import java.util.List;

public class ComparativoMedidas extends MenuDeTelas {

    private Repo_Medida repo_medida;
    private DataBase dataBaseLista;
    private SQLiteDatabase conexao_comp;
    private Medidas medidasUpd;
    private AdapterComparativo adapterComparativo;
    public static String tela;
    private String result;

    private ActComparativoMedidasBinding binding;
    private ContentComparativoMedidasBinding comparabinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActComparativoMedidasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocationActivityTitle("Comparativos de Medidas");

        setSupportActionBar(binding.toolbar);

        comparabinding = ContentComparativoMedidasBinding.inflate(getLayoutInflater());
        setContentView(comparabinding.getRoot());

        AdRequest adRequest = new AdRequest.Builder().build();
        comparabinding.idAdViewCompMed.loadAd(adRequest);

        criarConexao();

        comparabinding.recyclerListMedidasComparativos.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        comparabinding.recyclerListMedidasComparativos.setLayoutManager(linearLayoutManager);

        repo_medida = new Repo_Medida(conexao_comp);

        ArrayAdapter<String> adapter_spinner = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, dataBaseLista.buscarNomeUserSippner());
        comparabinding.spinnerUsuarios.setAdapter(adapter_spinner);

        comparabinding.spinnerUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tela = comparabinding.spinnerUsuarios.getSelectedItem().toString();
                comparabinding.txtNome.setText(tela);

                criarConexao();

                BuscarAdapter();

                fechaConexao();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //comparabinding.textViewTeste.setText(comparabinding.opcoesDeTelaComparativo.getSelectedItem().toString());BuscarAdapter();
        BuscarAdapter();
        fechaConexao();

    }

    public void BuscarAdapter(){
        if(dataBaseLista.select_Nome() != null) {
            List<Medidas> listmedidas = repo_medida.buscarTodos_Comparativos(comparabinding.spinnerUsuarios.getSelectedItem().toString());
            adapterComparativo = new AdapterComparativo(listmedidas);
            comparabinding.recyclerListMedidasComparativos.setAdapter(adapterComparativo);
        }
    }

    public String NullSpinnerUsuarios(){

        tela = comparabinding.spinnerUsuarios.getSelectedItem().toString();
        if(tela.length() > 0){
            comparabinding.spinnerUsuarios.getSelectedItem().toString();
        } else {
            result = "";
        }
        return result;
    }

    private void criarConexao() {
        try {
            dataBaseLista = new DataBase(this);
            conexao_comp = dataBaseLista.getWritableDatabase();
            repo_medida = new Repo_Medida(conexao_comp);

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
            conexao_comp = dataBaseLista.getWritableDatabase();
            repo_medida = new Repo_Medida(conexao_comp);
            dataBaseLista.close();

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.text_action_ok_conexao, null);
            dlg.show();
        }
    }

}