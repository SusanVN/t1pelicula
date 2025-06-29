package com.examen;

import com.examen.proyectot1.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        System.out.println("Probando conexión a la base de datos...");

        try {
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            System.out.println("Conexión exitosa a la base de datos.");
            em.close();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos:");
            e.printStackTrace();
        } finally {
            JPAUtil.shutdown();
        }
    }
}