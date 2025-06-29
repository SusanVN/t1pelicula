package com.examen.proyectot1.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DetalleAlquilerID implements Serializable{

    @Column(name="id_alquiler")
    private int alquiler;

    @Column(name="id_pelicula")
    private int pelicula;

    public DetalleAlquilerID(){
    }

    public DetalleAlquilerID(int alquiler, int pelicula) {
        this.alquiler = alquiler;
        this.pelicula = pelicula;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public int getPelicula() {
        return pelicula;
    }

    public void setPelicula(int pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleAlquilerID that = (DetalleAlquilerID) o;
        return alquiler == that.alquiler && pelicula == that.pelicula;
    }

    @Override
    public int hashCode() {
        
        return Objects.hash(alquiler, pelicula);
    }

    
}
