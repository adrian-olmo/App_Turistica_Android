package com.example.app_turistica_android.AdaptadorUbicaciones;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_turistica_android.R;

public class ListadoLugaresHolder extends RecyclerView.ViewHolder {

    ImageView foto;
    TextView nombre, tipo;

    public ListadoLugaresHolder(View itemView) {
        super(itemView);
        foto = itemView.findViewById(R.id.imagen_lugar);
        nombre = itemView.findViewById(R.id.nombre);
        tipo = itemView.findViewById(R.id.tipo);
    }
}
