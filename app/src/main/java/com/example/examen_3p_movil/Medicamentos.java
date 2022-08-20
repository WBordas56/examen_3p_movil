package com.example.examen_3p_movil;

import java.io.Serializable;

public class Medicamentos implements Serializable {

    private  String key;
    private String nombre;
    private String descripcion;
    private String cantidad;
    private  String tiempo;
    private  String Periocidad;
    private  String url;
    private String foto;

    public Medicamentos(String key, String nombre, String descripcion, String cantidad, String tiempo, String periocidad, String foto, String url) {
        this.key = key;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.tiempo = tiempo;
        this.Periocidad = periocidad;
        this.foto = foto;
        this.url = url;
    }

    Medicamentos(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getPeriocidad() {
        return Periocidad;
    }

    public void setPeriocidad(String periocidad) {
        this.Periocidad = periocidad;
    }

    public String getFoto() {
        return foto;
    }
     public void setFoto(String foto){
        this.foto = foto;
     }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
