package com.examen.proyectot1.model;

public enum EstadoAlquiler {
    ACTIVO("Activo"),
    DEVUELTO("Devuelto"),
    RETRASADO("Retrasado");

    private final String descripcion;

    private EstadoAlquiler(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;        
    }
    
    
}
