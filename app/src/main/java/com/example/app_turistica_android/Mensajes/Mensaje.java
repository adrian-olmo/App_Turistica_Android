package com.example.app_turistica_android.Mensajes;

public class Mensaje {

    private String mensaje;
    private String nombre;
    private String fotoPeril;
    private String type_mensaje;


    public Mensaje() {
    }

    public Mensaje(String mensaje, String nombre, String fotoPeril, String type_mensaje) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.fotoPeril = fotoPeril;
        this.type_mensaje = type_mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoPeril() {
        return fotoPeril;
    }

    public void setFotoPeril(String fotoPeril) {
        this.fotoPeril = fotoPeril;
    }

    public String getType_mensaje() {
        return type_mensaje;
    }

    public void setType_mensaje(String type_mensaje) {
        this.type_mensaje = type_mensaje;
    }

}
