package com.example.app_turistica_android.utils;

import java.io.Serializable;

public class Lugares implements Serializable {

    private String foto;
    private double latitud, longitud;
    private String nombre, tipo;

    public Lugares() {
    }

    public Lugares(String foto, double latitud, double longitud, String nombre, String tipo) {
        this.foto = foto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Lugares{" +
                "foto='" + foto + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}