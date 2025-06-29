<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registrar Alquiler</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
        }
        h1 {
            color: #333;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        select, input[type="number"] {
            width: 200px;
            padding: 5px;
        }
        table {
            margin-top: 15px;
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            padding: 8px;
            border: 1px solid #ccc;
        }
        .btn {
            margin-top: 20px;
            padding: 8px 20px;
        }
    </style>
    <script>
        // Validación básica antes de enviar el formulario
        function validarFormulario() {
            const checkboxes = document.querySelectorAll('input[name="peliculaSeleccionada[]"]:checked');
            if (checkboxes.length === 0) {
                alert("Por favor selecciona al menos una película.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<h1>Registrar Alquiler de Películas</h1>

<%-- Mostrar mensajes si vienen por la URL (ej: alquiler.jsp?mensaje=...&error=...) --%>
<%
    String mensaje = request.getParameter("mensaje");
    if (mensaje != null) {
%>
    <p style="color:green;"><%= mensaje %></p>
<%
    }
    String error = request.getParameter("error");
    if (error != null) {
%>
    <p style="color:red;"><%= error %></p>
<%
    }
%>

<form action="alquiler" method="post" onsubmit="return validarFormulario();">
    <!-- Combo de Clientes -->
    <label for="cliente">Cliente:</label>
    <select name="cliente" id="cliente" required>
        <option value="">Seleccione un cliente</option>
        <c:forEach var="cli" items="${clientes}">
            <option value="${cli.id}">${cli.nombre}</option>
        </c:forEach>
    </select>

    <!-- Tabla de Películas -->
    <h3>Películas disponibles</h3>
    <table>
        
        <tr>
            <th>Seleccionar</th>
            <th>Título</th>
            <th>Género</th>
            <th>Stock</th>
            <th>Cantidad</th>
        </tr>

        <c:forEach var="pel" items="${peliculas}">
            <tr>
                <td>
                    <input type="checkbox" name="peliculaSeleccionada[]" value="${pel.id}" />
                </td>
                <td>${pel.titulo}</td>
                <td>${pel.genero}</td>
                <td>${pel.stock}</td>
                <td>
                    <input type="number" name="cantidad_${pel.id}" min="1" value="1" />
                </td>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" value="Registrar Alquiler" class="btn" />
</form>

</body>
</html>