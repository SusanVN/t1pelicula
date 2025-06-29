package com.examen.proyectot1.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="detalle_alquiler")
public class DetalleAlquiler {
    
    @EmbeddedId
    private DetalleAlquilerID id;

    @ManyToOne
    @MapsId("alquiler")
    @JoinColumn(name="id_alquiler")
    private Alquiler alquiler;

    @ManyToOne
    @MapsId("pelicula")
    @JoinColumn(name = "id_pelicula", insertable = false, updatable = false)
    private Pelicula pelicula;

    @Column(nullable = false)
    private int cantidad;

   
    public DetalleAlquiler() {
    }


    public DetalleAlquiler(DetalleAlquilerID id, Alquiler alquiler, Pelicula pelicula, int cantidad) {
        this.id = new DetalleAlquilerID(alquiler.getId(), pelicula.getId());      
        this.alquiler = alquiler;
        this.pelicula = pelicula;
        this.cantidad = cantidad;
    }


    public DetalleAlquilerID getId() {
        return id;
    }


    public void setId(DetalleAlquilerID id) {
        this.id = id;
    }


    public Alquiler getAlquiler() {
        return alquiler;
    }


    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }


    public Pelicula getPelicula() {
        return pelicula;
    }


    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }


    public int getCantidad() {
        return cantidad;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
}
