package com.example.app_turistica_android.AdaptadorUbicaciones;

import android.content.Context;
import android.util.Log;

import com.example.app_turistica_android.utils.Lugares;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class AdaptadorFirebase extends FirebaseRecyclerAdapter<Lugares, ListadoLugaresHolder> {

    Context context;

    public AdaptadorFirebase(Class<Lugares> modelClass, int modelLayout, Class<ListadoLugaresHolder> viewHolderClass, DatabaseReference ref, Context c) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        context = c;
    }


    @Override
    protected void populateViewHolder(ListadoLugaresHolder listadoLugaresHolder, final Lugares lugares, int i) {
        listadoLugaresHolder.nombre.setText(lugares.getNombre());
        listadoLugaresHolder.tipo.setText(lugares.getTipo());
        Picasso.with(context).load(lugares.getFoto()).into(listadoLugaresHolder.foto);

    }
}
