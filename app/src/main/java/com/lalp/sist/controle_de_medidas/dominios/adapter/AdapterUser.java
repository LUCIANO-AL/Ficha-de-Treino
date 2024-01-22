package com.lalp.sist.controle_de_medidas.dominios.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.lalp.sist.controle_de_medidas.ListaDeMedidas;
import com.lalp.sist.controle_de_medidas.ListaDeUsuarios;
import com.lalp.sist.controle_de_medidas.R;
import com.lalp.sist.controle_de_medidas.database.DataBase;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Medidas;
import com.lalp.sist.controle_de_medidas.dominios.entidades.Usuario;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolderAdapterUser> {

    private List<Usuario> listusuario;
    //private ArrayList<RG_Med> rela;
    private Context context;
    private Usuario usuario;

    public AdapterUser(List<Usuario> listusuario) {
        this.listusuario = listusuario;
    }

    // public AdapterMedidas(ArrayList<Medidas> rela){this.listmedidas = listmedidas;}

    public void adicionarCliente(Usuario usuario) {
        listusuario.add(usuario);
        notifyItemInserted(getItemCount());
    }

    public void removerCliente(Usuario usuario) {
        int position = listusuario.indexOf(usuario);
        listusuario.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public AdapterUser.ViewHolderAdapterUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_user, parent, false);
        final ViewHolderAdapterUser viewHolderAdapterUser = new ViewHolderAdapterUser(view, parent.getContext());

        return viewHolderAdapterUser;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterUser.ViewHolderAdapterUser holder, @SuppressLint("RecyclerView") int position) {

        if ((listusuario != null) && (listusuario.size() > 0)) {

            final Usuario user = listusuario.get(position);

            holder.txtId.setText(String.valueOf(user.IDUSER));
            holder.txtNomeUser.setText(user.NOMEUSER);
            holder.txtNomeDoTreino.setText(user.TIPODETREINO);
            holder.edtDataInicial.setText(user.DATAINICIO);
            holder.edtDataFinal.setText(user.DATAFIM);

            holder.btnDeleteUser.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final View view = v;
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.title_adapterlistas_dellista)
                            .setMessage(R.string.msg_adapterlistas_del_user)
                            .setPositiveButton(R.string.txt_adapterlistas_dellista, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DataBase reposi = new DataBase(view.getContext());
                                    reposi.excluirUser(user.getIDUSER());
                                    removerCliente(user);
                                    Snackbar.make(view, R.string.action_adapterlistas_dellista, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }
                            })
                            .setNegativeButton(R.string.txtcancel_adapterlistas_dellista, null)
                            .create()
                            .show();
                }
            });

            holder.btnUpdateUser.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Dialog dialog = new Dialog(v.getContext());
                    dialog.setContentView(R.layout.update_usuario);

                    EditText edtIdUserUpdt = dialog.findViewById(R.id.edtIdUserUpdt);
                    EditText edtNomeUserUpdat = dialog.findViewById(R.id.edtNomeUserUpdat);
                    EditText edtTipoDeTreinoUpdat = dialog.findViewById(R.id.edtTipoDeTreinoUpdat);
                    EditText edtDataInicialUpdat = dialog.findViewById(R.id.edtDataInicialUpdat);
                    EditText edtDataFinalUpdat = dialog.findViewById(R.id.edtDataFinalUpdat);

                    Button btnUpdateUser = dialog.findViewById(R.id.btnUpdateUser);

                    Button btnVoltarUpdat = dialog.findViewById(R.id.btnVoltarUpdat);

                    edtIdUserUpdt.setText(String.valueOf(user.IDUSER));
                    edtNomeUserUpdat.setText(user.NOMEUSER);
                    edtTipoDeTreinoUpdat.setText(user.TIPODETREINO);
                    edtDataInicialUpdat.setText(user.DATAINICIO);
                    edtDataFinalUpdat.setText(user.DATAFIM);

                    btnUpdateUser.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(View v) {

                            String id = edtIdUserUpdt.getText().toString();
                            String nomeuser = edtNomeUserUpdat.getText().toString();
                            String tipotreino = edtTipoDeTreinoUpdat.getText().toString();
                            String datainicial = edtDataInicialUpdat.getText().toString();
                            String datafinal = edtDataFinalUpdat.getText().toString();

                            if (nomeuser.isEmpty()) {
                                Toast.makeText(v.getContext(), "Nome do usuário, campo obrigatório.", Toast.LENGTH_SHORT).show();
                            }  else {

                                try {
                                    DataBase database = new DataBase(v.getContext());

                                    database.updateUser(nomeuser, tipotreino, datainicial, datafinal, id);

                                    dialog.dismiss();

                                    ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                            edtNomeUserUpdat.getWindowToken(), 0);

                                    //btnRefreshAdpapter.performClick();

                                    DataBase dataBase = new DataBase(v.getContext());

                                    listusuario = dataBase.buscarUsuario();

                                    notifyItemChanged(position);
                                    //notifyItemChanged(position, getItemId(exercicio.ID));

                                    Toast.makeText(v.getContext(), "Atualizado", Toast.LENGTH_SHORT).show();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                    });

                    btnVoltarUpdat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

            });
        }

    }

    @Override
    public int getItemCount() {
        return listusuario.size();
    }

    public class ViewHolderAdapterUser extends RecyclerView.ViewHolder {

        public TextView txtId, txtNomeUser, txtNomeDoTreino, edtDataInicial, edtDataFinal;

        public CardView btnUpdateUser, btnDeleteUser, btnCompartilharTreino;

        public ViewHolderAdapterUser(View itemView, final Context context) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtNomeUser = (TextView) itemView.findViewById(R.id.txtNomeUser);
            txtNomeDoTreino = (TextView) itemView.findViewById(R.id.txtNomeDoTreino);
            edtDataInicial = (TextView) itemView.findViewById(R.id.edtDataInicial);
            edtDataFinal = (TextView) itemView.findViewById(R.id.edtDataFinal);

            btnUpdateUser = (CardView) itemView.findViewById(R.id.btnUpdateUser);
            btnDeleteUser = (CardView) itemView.findViewById(R.id.btnDeleteUser);
            btnCompartilharTreino = (CardView) itemView.findViewById(R.id.btnCompartilharTreino);

            btnCompartilharTreino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listusuario.size() > 0 ) {

                        Usuario usuario = listusuario.get(getLayoutPosition());

                        Intent it = new Intent(context, ListaDeUsuarios.class);
                        it.putExtra("COMPARTILHA_TREINO_LISTA", usuario);
                        ((AppCompatActivity) context).startActivityForResult(it, 0);

                    }
                }
            });


        }

    }
}
