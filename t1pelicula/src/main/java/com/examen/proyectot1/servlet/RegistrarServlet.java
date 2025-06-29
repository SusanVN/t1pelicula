package com.examen.proyectot1.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.examen.proyectot1.controller.AlquilerController;
import com.examen.proyectot1.model.Cliente;
import com.examen.proyectot1.model.DetalleAlquiler;
import com.examen.proyectot1.model.Pelicula;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registrarAlquiler")
public class RegistrarServlet extends HttpServlet {

    private AlquilerController alquilerController = new AlquilerController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Obtener ID del cliente
            int idCliente = Integer.parseInt(request.getParameter("cliente"));

            // 2. Obtener las películas seleccionadas
            String[] peliculasSeleccionadas = request.getParameterValues("peliculaSeleccionada");

            if (peliculasSeleccionadas == null || peliculasSeleccionadas.length == 0) {
                response.sendRedirect("alquiler.jsp?error=Debe+seleccionar+al+menos+una+película");
                return;
            }

            // 3. Buscar cliente desde la base de datos
            Cliente cliente = alquilerController.buscarClientePorId(idCliente);

            List<DetalleAlquiler> detalles = new ArrayList<>();
            double total = 0.0;

            for (String idPeliculaStr : peliculasSeleccionadas) {
                int idPelicula = Integer.parseInt(idPeliculaStr);
                Pelicula pelicula = alquilerController.buscarPeliculaPorId(idPelicula);

                // Lee la cantidad desde el formulario: cantidad_<id>
                String cantidadStr = request.getParameter("cantidad_" + idPelicula);
                int cantidad = (cantidadStr != null && !cantidadStr.isEmpty()) ? Integer.parseInt(cantidadStr) : 1;

                // Crear detalle y calcular el total (aquí se usa precio ficticio 15)
                DetalleAlquiler detalle = new DetalleAlquiler();
                detalle.setPelicula(pelicula);
                detalle.setCantidad(cantidad);
                detalles.add(detalle);

                total += cantidad * 15.0; // Suponiendo 15 soles por unidad
            }

            // Registrar el alquiler usando el controlador
            boolean exito = alquilerController.registrarAlquiler(cliente, detalles, total);

            // 7. Redirigir con mensaje
            if (exito) {
                response.sendRedirect("alquiler.jsp?mensaje=Alquiler+registrado+correctamente");
            } else {
                response.sendRedirect("alquiler.jsp?error=Error+al+registrar+el+alquiler");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("alquiler.jsp?error=Error+inesperado:+"
                    + e.getMessage().replace(" ", "+"));
        }
    }
}