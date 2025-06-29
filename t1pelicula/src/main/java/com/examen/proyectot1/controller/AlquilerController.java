package com.examen.proyectot1.controller;

import java.util.List;

import com.examen.proyectot1.model.Alquiler;
import com.examen.proyectot1.model.Cliente;
import com.examen.proyectot1.model.DetalleAlquiler;
import com.examen.proyectot1.model.EstadoAlquiler;
import com.examen.proyectot1.model.Pelicula;
import com.examen.proyectot1.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AlquilerController {
    
    public List<Cliente> obtenerClientes(){ 
        EntityManager em= JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        em.close();
        return clientes;
    }

    public List<Pelicula> obtenerPeliculas() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Pelicula> peliculas = em.createQuery("SELECT p FROM Pelicula p", Pelicula.class).getResultList();
        em.close();
        return peliculas;
    }

      //Registra el alquiler en la BD2_Vilchez 
     public boolean registrarAlquiler(Cliente cliente, List<DetalleAlquiler> detalles, double total) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager(); 
        EntityTransaction tx = em.getTransaction(); 

        try {
            tx.begin(); 

            // Crea un nuevo objeto Alquiler con estado ACTIVO
            Alquiler alquiler = new Alquiler(cliente, total, EstadoAlquiler.ACTIVO);

            // Asigna el alquiler a cada detalle y lo añade a la lista del alquiler
            for (DetalleAlquiler detalle : detalles) {
                detalle.setAlquiler(alquiler); // Establece la relación
                alquiler.getDetalles().add(detalle); // Añade a la lista del alquiler
            }

            // Persistimos el alquiler, lo que gracias a CascadeType.ALL también guarda los detalles
            em.persist(alquiler);

            tx.commit(); 
            return true; 

        } catch (Exception e) {
            e.printStackTrace(); 
            if (tx.isActive()) {
                tx.rollback(); 
            }
            return false; 

        } finally {
            em.close(); 
        }
    }

    //Buscar cliente por su ID 

    public Cliente buscarClientePorId(int idCliente){
        EntityManager em= JPAUtil.getEntityManagerFactory().createEntityManager();
        Cliente cliente=null;
        try{
            cliente = em.find(Cliente.class, idCliente);
        }finally{
            em.close();
        }
        return cliente;
    }

    //Buscar pelicula por su ID 

    public Pelicula buscarPeliculaPorId(int idPelicula){
        EntityManager em= JPAUtil.getEntityManagerFactory().createEntityManager();
        Pelicula pelicula=null;
        try{
            pelicula = em.find(Pelicula.class, idPelicula);
        }finally{
            em.close();
        }
        return pelicula;
    }
}


