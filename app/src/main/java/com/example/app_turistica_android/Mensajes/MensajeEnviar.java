package com.example.app_turistica_android.Mensajes;

import java.util.Map;

public class MensajeEnviar extends Mensaje {

    private Map hora;

    public MensajeEnviar() {
    }

    public MensajeEnviar(Map hora) {
        this.hora = hora;
    }

    public MensajeEnviar(String mensaje, String nombre, String fotoPeril, String type_mensaje, Map hora) {
        super(mensaje, nombre, fotoPeril, type_mensaje);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
