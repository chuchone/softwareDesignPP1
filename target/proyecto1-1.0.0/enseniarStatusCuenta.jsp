<%-- 
    Document   : enseniarStatusCuenta
    Created on : 2 oct. 2024, 12:54:30
    Author     : Nelson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Estado de la Cuenta</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        .info {
            font-size: 18px;
            color: #555;
        }
        .status-activa {
            color: green;
            font-weight: bold;
        }
        .status-inactiva {
            color: red;
            font-weight: bold;
        }
        a {
            display: block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Estado de la Cuenta</h2>
    
    <p class="info">Cliente: <strong><%= request.getAttribute("nombreCliente") %></strong></p>
    
    <p class="info">Estado de la cuenta: 
        <strong class="<%= (Boolean) request.getAttribute("status") ? "status-activa" : "status-inactiva" %>">
            <%= (Boolean) request.getAttribute("status") ? "Activa" : "Inactiva" %>
        </strong>
    </p>

    <a href="index.jsp">Volver al Inicio</a>
</div>

</body>
</html>
