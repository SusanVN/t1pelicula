package com.examen.proyectot1.servlet;
import com.examen.proyectot1.controller.AlquilerController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/alquiler")  // Esta es la URL para acceder al formulario
public class AlquilerVistaServlet extends HttpServlet {

    private AlquilerController alquilerController = new AlquilerController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        

        // Obtiene las listas desde el controlador
        request.setAttribute("clientes", alquilerController.obtenerClientes());
        request.setAttribute("peliculas", alquilerController.obtenerPeliculas());

        // Reenvia al JSP
        request.getRequestDispatcher("/alquiler.jsp").forward(request, response);
    }
}


