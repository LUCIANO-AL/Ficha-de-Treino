package com.lalp.sist.controle_de_medidas.dominios.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.snackbar.Snackbar;
import com.lalp.sist.controle_de_medidas.R;
import com.lalp.sist.controle_de_medidas.Treinos;
import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Exercicio;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Musculo;

import java.util.List;

import static com.lalp.sist.controle_de_medidas.Treinos.contadorturial;
import static com.lalp.sist.controle_de_medidas.Treinos.editorTutorial;

import static com.lalp.sist.controle_de_medidas.Treinos.recycleViewMusculos;
import static com.lalp.sist.controle_de_medidas.Treinos.spinnerDiaSemana;
import static com.lalp.sist.controle_de_medidas.Treinos.spinnerMuscDoTreinoRecycle;
import static com.lalp.sist.controle_de_medidas.Treinos.spinnerMusculoExerc;
import static com.lalp.sist.controle_de_medidas.Treinos.txtArrastaProLado;
import static com.lalp.sist.controle_de_medidas.Treinos.txtIdUserExerc;

public class AdapterRecycleMusculo extends RecyclerView.Adapter<AdapterRecycleMusculo.ViewHolderAdapterRecycleMusculo> {

    public Activity activity;
    public List<Musculo> listmusculo;
    public List<Exercicio> listexercicio;
    public DataBase dataBase;
    public SQLiteDatabase conexao;
    public AdapterRecycleExercicio adapterRecycleExercicio;
    public Context context;

    public AdapterRecycleMusculo(Activity activity, List<Musculo> listmusculo, List<Exercicio> listexercicio, DataBase dataBase) {
        this.activity = activity;
        this.listmusculo = listmusculo;
        this.listexercicio = listexercicio;
        this.dataBase = dataBase;
    }

    public void removerMusculo(Musculo musculo) {
        int position = listmusculo.indexOf(musculo);
        listmusculo.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolderAdapterRecycleMusculo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_musculo, parent, false);
        final ViewHolderAdapterRecycleMusculo viewHolderAdapterRecycleMusculo = new ViewHolderAdapterRecycleMusculo(view, parent.getContext());

        return viewHolderAdapterRecycleMusculo;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapterRecycleMusculo holder, int position) {

        if ((listmusculo != null) && (listmusculo.size() > 0)) {

            final Musculo musculo = listmusculo.get(position);

            holder.txtIDMusc.setText(String.valueOf(musculo.ID));
            holder.txtMusculo.setText(musculo.MUSCULO);
            holder.txtPosicaoMusc.setText(String.valueOf(musculo.POSICAO));
            holder.txtDiaSemana.setText(musculo.DIADASEMANA);
            holder.txtIDUserMusc.setText(String.valueOf(musculo.IDUSER));


           // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            holder.recycleExercicio.setLayoutManager(linearLayoutManager);
            holder.recycleExercicio.setHasFixedSize(true);

            listexercicio = dataBase.buscarExercicio(holder.txtDiaSemana.getText().toString(), holder.txtIDMusc.getText().toString(),
                    holder.txtIDUserMusc.getText().toString());

            adapterRecycleExercicio = new AdapterRecycleExercicio(listexercicio);
            holder.recycleExercicio.setAdapter(adapterRecycleExercicio);



            holder.btnExcluirMusc.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final View view = v;
                    android.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.title_adapterlistas_dellista)
                            .setMessage(R.string.msg_adapterlistas_dellista_musculo)
                            .setPositiveButton(R.string.txt_adapterlistas_dellista, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    DataBase reposi = new DataBase(view.getContext());

                                    reposi.excluirMusculo(musculo.getID());

                                    removerMusculo(musculo);

                                    reposi.excluirExercicioComMusc(musculo.getID());

                                    ArrayAdapter<String> adapter_spinner_NomeMusculoRecycle = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,
                                            reposi.buscarNomeMusculoSippnerRecycle(spinnerDiaSemana.getSelectedItem().toString(), txtIdUserExerc.getText().toString()));
                                    spinnerMuscDoTreinoRecycle.setAdapter(adapter_spinner_NomeMusculoRecycle);

                                    ArrayAdapter<String> adapter_spinner_NomeMusculoEx = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,
                                            reposi.buscarNomeMusculoSippner(spinnerDiaSemana.getSelectedItem().toString(), txtIdUserExerc.getText().toString()));
                                    spinnerMusculoExerc.setAdapter(adapter_spinner_NomeMusculoEx);

                                    Snackbar.make(view, R.string.action_adapterlistas_dellista, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }
                            })
                            .setNegativeButton(R.string.txtcancel_adapterlistas_dellista, null)
                            .create()
                            .show();

                }
            });

            holder.tutorialRecycleView();

            // dataBase.close();
        }


    }

    @Override
    public int getItemCount() {
        return listmusculo.size();

    }

    public class ViewHolderAdapterRecycleMusculo extends RecyclerView.ViewHolder {
        public TextView txtMusculo, txtDiaSemana, txtIDUserMusc, txtIDMusc, txtPosicaoMusc;
        public RecyclerView recycleExercicio;
        public ImageView btnUpdateMusculo, btnExcluirMusc;

        public ViewHolderAdapterRecycleMusculo(View itemView, final Context context) {
            super(itemView);

            txtMusculo = (TextView) itemView.findViewById(R.id.txtMusculo);
            txtDiaSemana = (TextView) itemView.findViewById(R.id.txtDiaSemana);
            txtPosicaoMusc = (TextView) itemView.findViewById(R.id.txtPosicaoMusc);
            txtIDUserMusc = (TextView) itemView.findViewById(R.id.txtIDUserMusc);
            txtIDMusc = (TextView) itemView.findViewById(R.id.txtIDMusc);
            btnUpdateMusculo = (ImageView) itemView.findViewById(R.id.btnUpdateMusculo);
            btnExcluirMusc = (ImageView) itemView.findViewById(R.id.btnExcluirMusc);
            recycleExercicio = (RecyclerView) itemView.findViewById(R.id.recycleExercicio);

            btnUpdateMusculo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (listmusculo.size() > 0) {

                        Musculo musc = listmusculo.get(getLayoutPosition());

                        Intent it = new Intent(context, Treinos.class);
                        it.putExtra("MUSCULO", musc);
                        ((AppCompatActivity) context).startActivity(it);

                    }
                }
            });

        }

        public void tutorialRecycleView() {

            if (contadorturial == 5) {

                TapTargetView.showFor(activity,
                        TapTarget.forView(btnUpdateMusculo, "Alterar informações do músculo alvo", "Click neste botão para alterar a nome do músculo, o dia da semana ou a ordem. Click no círculo para desativa o informativo.")
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
                                .targetRadius(30),
                        new TapTargetView.Listener() {

                            @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);

                                contadorturial++;
                                editorTutorial.putInt("tutorial", contadorturial);
                                editorTutorial.commit();

                                tutorialRecycleViewExerc();

                            }
                        });

            }
        }

        public void tutorialRecycleViewExerc() {

            if (contadorturial == 6) {

                //  ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                //        edtExercicio.getWindowToken(), 0);

                // constraintLayoutAddExercicio.setVisibility(View.GONE);
                //btnExibirForm.setVisibility(View.VISIBLE);

                TapTargetView.showFor(activity,
                        TapTarget.forView(AdapterRecycleExercicio.ViewHolderAdapterMedidas.btnUpdateExer, "Alterar informações do exercício", "Click neste botão para alterar o exercício, o músculo alvo do exercício, a carga, as repetições ou a ordem. Click no círculo para desativa o informativo.")
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
                                .targetRadius(30),
                        new TapTargetView.Listener() {

                            @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);

                                contadorturial++;

                                editorTutorial.putInt("tutorial", contadorturial);
                                editorTutorial.commit();

                                tutorialRecycleViewExercPesquisa();

                            }
                        });

            }
        }

        public void tutorialRecycleViewExercPesquisa() {

            if (contadorturial == 7) {

                //  ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                //        edtExercicio.getWindowToken(), 0);

                // constraintLayoutAddExercicio.setVisibility(View.GONE);
                //btnExibirForm.setVisibility(View.VISIBLE);

                TapTargetView.showFor(activity,
                        TapTarget.forView(AdapterRecycleExercicio.ViewHolderAdapterMedidas.btnPesquisar, "Pesquisar exercício", "Click neste botão para pesquisar na internet o modo de fazer o exercício. Click no círculo para desativa o informativo.")
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
                                .targetRadius(30),
                        new TapTargetView.Listener() {

                            @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);

                                contadorturial++;

                                editorTutorial.putInt("tutorial", contadorturial);
                                editorTutorial.commit();

                                tutorialRecycleViewExercArrastaProLado();

                            }
                        });

            }
        }

        public void tutorialRecycleViewExercArrastaProLado() {

            if (contadorturial == 8) {

                //  ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                //        edtExercicio.getWindowToken(), 0);

                // constraintLayoutAddExercicio.setVisibility(View.GONE);
                //btnExibirForm.setVisibility(View.VISIBLE);

                TapTargetView.showFor(activity,
                        TapTarget.forView(AdapterRecycleExercicio.ViewHolderAdapterMedidas.linearLinhaExerc, "Arraste pro Lado.", "A lista dos exercícios esta organizada na horizontal então para acessar os demais exercícios arraste pro lado. Click no círculo para desativa o informativo.")
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
                                .targetRadius(100),
                        new TapTargetView.Listener() {

                            @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);

                                contadorturial++;

                                editorTutorial.putInt("tutorial", contadorturial);
                                editorTutorial.commit();

                                tutorialMudaMuscSpinner();


                            }
                        });

            }
        }

        public void tutorialMudaMuscSpinner() {
            if (contadorturial == 9) {

                //contadorturial++;

                //editorTutorial.putInt("tutorial", contadorturial);
                //editorTutorial.commit();

                TapTargetView.showFor(activity,
                        TapTarget.forView(spinnerMuscDoTreinoRecycle, "Mudar o músculo do treino com seus exercícios", "Ao clicar em outro músculo que estar nesta guia o musuculo sera alterado na visuluzação e os exercícios, ou seja nos dias que você treinar mais de um músculo ao termina os exercícios de um musculo você clicar nessa guia para mudar para o proxímo músculo." +
                                        "Click no círculo para desativa o informativo")
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
                                .targetRadius(120),
                        new TapTargetView.Listener() {
                            @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);
                                contadorturial++;

                                editorTutorial.putInt("tutorial", contadorturial);
                                editorTutorial.commit();

                                tutorialArrasteProLado();
                            }
                        });

            }
        }
        public void tutorialArrasteProLado() {
            if (contadorturial == 10) {

                TapTargetView.showFor(activity,
                        TapTarget.forView(txtArrastaProLado, "Menu de Telas", "Arraste da esquerda para direita para acessar o menu. Click no círculo para desativa o informativo")
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
                                .targetRadius(100),
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



    }

}

