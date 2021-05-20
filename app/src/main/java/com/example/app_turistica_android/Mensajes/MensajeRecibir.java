package com.example.app_turistica_android.Mensajes;

import com.example.app_turistica_android.LogIn;

public class MensajeRecibir extends Mensaje {

    private Long hora;

    public MensajeRecibir() {
    }

    public MensajeRecibir(Long hora) {
        this.hora = hora;
    }

    public MensajeRecibir(String mensaje, String nombre, String fotoPeril, String type_mensaje, Long hora) {
        super(mensaje, nombre, fotoPeril, type_mensaje);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
