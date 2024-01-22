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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.lalp.sist.controle_de_medidas.ListaDeMedidas;
import com.lalp.sist.controle_de_medidas.R;
import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;

import java.util.List;

public class AdapterComparativo extends RecyclerView.Adapter<AdapterComparativo.ViewHolderAdapterMedidas> {

    private List<Medidas> listmedidas;
    //private ArrayList<RG_Med> rela;
    private Context context;

    public AdapterComparativo(List<Medidas> listmedidas){this.listmedidas = listmedidas;}

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
    public AdapterComparativo.ViewHolderAdapterMedidas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linhas_comparativas,parent,false);
        final ViewHolderAdapterMedidas viewHolderAdapterMedidas = new ViewHolderAdapterMedidas(view,parent.getContext());

        return viewHolderAdapterMedidas;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterComparativo.ViewHolderAdapterMedidas holder, int position) {

        if ((listmedidas != null) && (listmedidas.size() > 0)) {

            final Medidas medidas2 = listmedidas.get(position);

            holder.txtId.setText(String.valueOf(medidas2.ID));
            holder.txtNome.setText(medidas2.NomeUser);
            holder.txtData1.setText(medidas2.Datarg);
            holder.textPeso1.setText(medidas2.Peso);
            holder.txtAltura1.setText(medidas2.Altura);
            holder.txtCintura1.setText(medidas2.Cintura);
            holder.txtPeito1.setText(medidas2.Peito);
            holder.txtOmbros1.setText(medidas2.Ombros);
            holder.textBicepsdir1.setText(medidas2.Bicepsdir);
            holder.textBicepsesq1.setText(medidas2.Bicepsesq);
            holder.textBicepsdirContr1.setText(medidas2.BicepsdirContr);
            holder.textBicepsesqContr1.setText(medidas2.BicepsesqContr);
            holder.textAntbracdir1.setText(medidas2.Antbracdir);
            holder.textAntbracesq1.setText(medidas2.Antbracesq);
            holder.textBumbum1.setText(medidas2.Bumbum);
            holder.textCoxadir1.setText(medidas2.Coxadir);
            holder.textCoxaesq1.setText(medidas2.Coxaesq);
            holder.textPanturdir1.setText(medidas2.Panturdir);
            holder.textPanturesq1.setText(medidas2.Panturesq);

        }

    }

    @Override
    public int getItemCount() {
        return listmedidas.size();
    }

    public class ViewHolderAdapterMedidas extends RecyclerView.ViewHolder {

        public TextView txtId;
        public TextView txtNome;
        public TextView txtData1;
        public TextView textPeso1;
        public TextView txtAltura1;
        public TextView txtCintura1;
        public TextView txtPeito1;
        public TextView txtOmbros1;
        public TextView textBicepsdir1;
        public TextView textBicepsesq1;
        public TextView textBicepsdirContr1;
        public TextView textBicepsesqContr1;
        public TextView textAntbracdir1;
        public TextView textAntbracesq1;
        public TextView textBumbum1;
        public TextView textCoxadir1;
        public TextView textCoxaesq1;
        public TextView textPanturdir1;
        public TextView textPanturesq1;

        public ViewHolderAdapterMedidas(View itemView, final Context context) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txtCompId);
            txtNome = (TextView) itemView.findViewById(R.id.txtNomeUserComp);
            txtData1 = (TextView) itemView.findViewById(R.id.txtData1);
            textPeso1 = (TextView) itemView.findViewById(R.id.txtPeso1);
            txtAltura1 = (TextView) itemView.findViewById(R.id.txtAltura1);
            txtCintura1 = (TextView) itemView.findViewById(R.id.txtCintura1);
            txtPeito1 = (TextView) itemView.findViewById(R.id.txtPeito1);
            txtOmbros1 = (TextView) itemView.findViewById(R.id.txtOmbros1);
            textBicepsdir1 = (TextView) itemView.findViewById(R.id.txtBicepsD1);
            textBicepsesq1 = (TextView) itemView.findViewById(R.id.txtBicepsE1);
            textBicepsdirContr1 = (TextView) itemView.findViewById(R.id.txtBicepsDContra1);
            textBicepsesqContr1 = (TextView) itemView.findViewById(R.id.txtBicepsEContra1);
            textAntbracdir1 = (TextView) itemView.findViewById(R.id.txtAntebracoD1);
            textAntbracesq1 = (TextView) itemView.findViewById(R.id.txtAntebracoE1);
            textBumbum1 = (TextView) itemView.findViewById(R.id.txtBumbum1);
            textCoxadir1 = (TextView) itemView.findViewById(R.id.txtCoxaD1);
            textCoxaesq1 = (TextView) itemView.findViewById(R.id.txtCoxaE1);
            textPanturdir1 = (TextView) itemView.findViewById(R.id.txtPanturrilhaD1);
            textPanturesq1 = (TextView) itemView.findViewById(R.id.txtPanturrilhaE1);

        }


    }

}
