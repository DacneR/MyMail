package com.mymail.mymail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolder extends RecyclerView.ViewHolder {

    ImageView IMAGEN;

    TextView TITULO, COMENTARIO, ESTADO;
    public viewHolder(@NonNull View itemView) {
        super(itemView);
        IMAGEN = (ImageView) itemView.findViewById(R.id.iconoImageView);

        TITULO = (TextView) itemView.findViewById(R.id.nameTextView);
        COMENTARIO = (TextView) itemView.findViewById(R.id.comenTextView);
        ESTADO = (TextView) itemView.findViewById(R.id.statusTextView);

    }
}
