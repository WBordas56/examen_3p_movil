package com.example.examen_3p_movil;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Medicamentos implements Serializable {

    private String uid;
    private String descripcion;
    private String cantidad;
    private String tiempo;
    private String periocidad;
    private String foto;

    public Medicamentos(String uid, String descripcion, String cantidad, String tiempo, String periocidad, String foto) {
        this.uid = uid;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.tiempo = tiempo;
        this.periocidad = periocidad;
        this.foto = foto;
    }

    Medicamentos(){

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
        return periocidad;
    }

    public void setPeriocidad(String periocidad) {
        this.periocidad = periocidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
