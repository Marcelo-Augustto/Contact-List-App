package com.example.appwithdatabase;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MeuAdaptador extends RecyclerView.Adapter<MeuAdaptador.ViewHolder> {
    List<Contato> contatos;

    public MeuAdaptador(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtNome;
        final TextView txtFone;
        final TextView txtEmail;
        int position;
        Contato contato;

        public ViewHolder(View view) {
            super(view);
            txtNome = (TextView) view.findViewById(R.id.txtNome);
            txtFone = (TextView) view.findViewById(R.id.txtFone);
            txtEmail = (TextView) view.findViewById(R.id.txtEmail);

            itemView.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity2.getInstance().deletarContato(contato);
                    MainActivity2.getInstance().recreate();
                }
            });

            itemView.findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity2.getInstance().goToEditActivity(view, contato.id);
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contato, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Contato contato = contatos.get(position);
        holder.txtNome.setText(contato.nome);
        holder.txtFone.setText(contato.telefone);
        holder.txtEmail.setText(contato.email);
        holder.position = position;
        holder.contato = contato;
    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }
}