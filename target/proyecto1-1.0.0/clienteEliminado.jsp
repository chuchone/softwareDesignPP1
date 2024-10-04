<%-- 
    Document   : clienteEliminado
    Created on : 2 oct. 2024, 02:40:26
    Author     : Nelson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmación de Eliminación</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <div class="container">
        <h2>Cuenta Eliminada</h2>
        <p>La cuenta con el número <strong><%= request.getAttribute("numeroCuenta") %></strong> ha sido eliminada exitosamente.</p>
        <a href="index.jsp">Volver al inicio</a>
    </div>
</body>
</html>
