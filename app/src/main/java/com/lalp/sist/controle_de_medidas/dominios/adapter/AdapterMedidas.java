package com.lalp.sist.controle_de_medidas.dominios.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.lalp.sist.controle_de_medidas.ListaDeMedidas;
import com.lalp.sist.controle_de_medidas.R;
import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;

import java.util.List;

public class AdapterMedidas extends RecyclerView.Adapter<AdapterMedidas.ViewHolderAdapterMedidas> {

    private List<Medidas> listmedidas;
    //private ArrayList<RG_Med> rela;
    private Context context;

    public AdapterMedidas(List<Medidas> listmedidas){this.listmedidas = listmedidas;}

   // public AdapterMedidas(ArrayList<Medidas> rela){this.listmedidas = listmedidas;}

    public void adicionarCliente(Medidas medidas){
        listmedidas.add(medidas);
        notifyItemInserted(getItemCount());
    }

    public void removerCliente(Medidas medidas){
        int position = listmedidas.indexOf(medidas);
        listmedidas.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public AdapterMedidas.ViewHolderAdapterMedidas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_medida,parent,false);
        final ViewHolderAdapterMedidas viewHolderAdapterMedidas = new ViewHolderAdapterMedidas(view,parent.getContext());

        return viewHolderAdapterMedidas;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterMedidas.ViewHolderAdapterMedidas holder, int position) {

        if ((listmedidas != null) && (listmedidas.size() > 0)) {

            final Medidas medidas2 = listmedidas.get(position);

            holder.txtId.setText(String.valueOf(medidas2.ID));
            holder.txtNomeUser.setText(medidas2.NomeUser);
            holder.txtDataLista.setText(medidas2.Datarg);
            holder.textPeso.setText(medidas2.Peso);
            holder.textAltura.setText(medidas2.Altura);
            holder.textPeito.setText(medidas2.Peito);
            holder.textOmbros.setText(medidas2.Ombros);
            holder.textBicepsdir.setText(medidas2.Bicepsdir);
            holder.textBicepsesq.setText(medidas2.Bicepsesq);
            holder.textBicepsdirContr.setText(medidas2.BicepsesqContr);
            holder.textBicepsesqContr.setText(medidas2.BicepsdirContr);
            holder.textAntbracdir.setText(medidas2.Antbracdir);
            holder.textAntbracesq.setText(medidas2.Bicepsesq);
            holder.textCintura.setText(medidas2.Cintura);
            holder.textBumbum.setText(medidas2.Bumbum);
            holder.textCoxadir.setText(medidas2.Coxadir);
            holder.textCoxaesq.setText(medidas2.Coxaesq);
            holder.textPanturdir.setText(medidas2.Panturdir);
            holder.textPanturesq.setText(medidas2.Panturesq);


            holder.btnDelete.setOnClickListener(new Button.OnClickListener() {
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
            });
        }

    }

    @Override
    public int getItemCount() {
        return listmedidas.size();
    }

    public class ViewHolderAdapterMedidas extends RecyclerView.ViewHolder {

        public TextView txtId;
        public TextView txtNomeUser;
        public TextView txtDataLista;
        public TextView textPeso;
        public TextView textAltura;
        public TextView textPeito;
        public TextView textOmbros;
        public TextView textBicepsdir;
        public TextView textBicepsesq;
        public TextView textBicepsdirContr;
        public TextView textBicepsesqContr;
        public TextView textAntbracdir;
        public TextView textAntbracesq;
        public TextView textCintura;
        public TextView textBumbum;
        public TextView textCoxadir;
        public TextView textCoxaesq;
        public TextView textPanturdir;
        public TextView textPanturesq;

        public Button btnCopiar;
        public CardView btnDelete;

        public ViewHolderAdapterMedidas(View itemView, final Context context) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtNomeUser = (TextView) itemView.findViewById(R.id.txtNomeUser);
            txtDataLista = (TextView) itemView.findViewById(R.id.txtDataLista);
            textPeso = (TextView) itemView.findViewById(R.id.textPeso);
            textAltura = (TextView) itemView.findViewById(R.id.textAltura);
            textPeito = (TextView) itemView.findViewById(R.id.textPeito);
            textOmbros = (TextView) itemView.findViewById(R.id.textOmbros);
            textBicepsdir = (TextView) itemView.findViewById(R.id.textBicepsdir);
            textBicepsesq = (TextView) itemView.findViewById(R.id.textBicepsesq);
            textBicepsdirContr = (TextView) itemView.findViewById(R.id.textBicepsdirContr);
            textBicepsesqContr = (TextView) itemView.findViewById(R.id.textBicepsesqContr);
            textAntbracdir = (TextView) itemView.findViewById(R.id.textAntbracdir);
            textAntbracesq = (TextView) itemView.findViewById(R.id.textAntbracesq);
            textCintura = (TextView) itemView.findViewById(R.id.textCintura);
            textBumbum = (TextView) itemView.findViewById(R.id.textBumbum);
            textCoxadir = (TextView) itemView.findViewById(R.id.textCoxadir);
            textCoxaesq = (TextView) itemView.findViewById(R.id.textCoxaesq);
            textPanturdir = (TextView) itemView.findViewById(R.id.textPanturdir);
            textPanturesq = (TextView) itemView.findViewById(R.id.textPanturesq);

            btnCopiar = (Button) itemView.findViewById(R.id.btnCopiar);
            btnDelete = (CardView) itemView.findViewById(R.id.btnDelete);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (listmedidas.size() > 0 ) {

                        Medidas med = listmedidas.get(getLayoutPosition());

                        Intent it = new Intent(context, ListaDeMedidas.class);
                        it.putExtra("MEDIDAS_UPDT", med);
                        ((AppCompatActivity) context).startActivityForResult(it, 0);

                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if (listmedidas.size() > 0 ) {

                        Medidas med = listmedidas.get(getLayoutPosition());

                        Intent it = new Intent(context, ListaDeMedidas.class);
                        it.putExtra("RG_MED_C", med);
                        ((AppCompatActivity) context).startActivityForResult(it, 0);

                    }
                    return true;
                }
            });

            btnCopiar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listmedidas.size() > 0 ) {

                        Medidas med = listmedidas.get(getLayoutPosition());

                        Intent it = new Intent(context, ListaDeMedidas.class);
                        it.putExtra("COPIARMED", med);
                        ((AppCompatActivity) context).startActivityForResult(it, 0);

                    }
                }
            });

        }


    }
}
