package com.example.app_turistica_android.Mensajes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_turistica_android.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MensajeHolder extends RecyclerView.ViewHolder {

    private TextView nombre, mensaje, hora;
    private CircleImageView fotoMensaje;

    public MensajeHolder(@NonNull View itemView) {
        super(itemView);
        instancias();
    }

    private void instancias() {
        nombre = itemView.findViewById(R.id.NombreMensaje);
        mensaje = itemView.findViewById(R.id.Mensaje);
        hora = itemView.findViewById(R.id.HoraMensaje);
        fotoMensaje = itemView.findViewById(R.id.fotoperfilMensaje);
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public CircleImageView getFotoMensaje() {
        return fotoMensaje;
    }

    public void setFotoMensaje(CircleImageView fotoMensaje) {
        this.fotoMensaje = fotoMensaje;
    }
}
