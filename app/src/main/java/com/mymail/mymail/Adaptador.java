package com.mymail.mymail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<viewHolder> {

    List<ListaElem> ListaOb;

    public Adaptador(List<ListaElem> listaOb) {
        ListaOb = listaOb;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent,false);
        return new viewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.TITULO.setText(ListaOb.get(position).getTitulo());

        holder.COMENTARIO.setText(ListaOb.get(position).getDescr());
        holder.ESTADO.setText(ListaOb.get(position).getComen());


    }

    @Override
    public int getItemCount() {
        return ListaOb.size();
    }
}
