package com.example.app_turistica_android.Mensajes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_turistica_android.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdaptadorMensajes extends RecyclerView.Adapter<MensajeHolder> {

    private List<MensajeRecibir> listaMensaje = new ArrayList<>();
    private Context c;

    public AdaptadorMensajes( Context c) {
        this.listaMensaje = listaMensaje;
        this.c = c;
    }

    public void agregarMensaje(MensajeRecibir m){
        listaMensaje.add(m);
        notifyItemInserted(listaMensaje.size());
    }

    @NonNull
    @Override
    public MensajeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_mensajes, parent, false);
        return new MensajeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MensajeHolder holder, int position) {
        holder.getNombre().setText(listaMensaje.get(position).getNombre());
        holder.getMensaje().setText(listaMensaje.get(position).getMensaje());

        Long codigoHora = listaMensaje.get(position).getHora();
        Date d = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); //a diferencia entre am y pm
        holder.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return listaMensaje.size();
    }
}
