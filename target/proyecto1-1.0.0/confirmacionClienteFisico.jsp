<%-- 
    Document   : confirmarClienteFisico
    Created on : 24 sep. 2024, 23:19:21
    Author     : Nelson
--%>
co<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmación - Cliente Creado</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #ffcc80;
            color: #333;
            padding: 20px;
            text-align: center;
        }
        h1 {
            margin: 0;
            font-size: 2.5em;
        }
        .container {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        p {
            font-size: 1.2em;
            margin-bottom: 10px;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        .back-button {
            display: inline-block;
            background-color: #ffcc80;
            color: #333;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.1em;
            transition: background-color 0.3s ease;
        }
        .back-button:hover {
            background-color: #ffa726;
        }
    </style>
</head>
<body>
    <header>
        <h1>Cliente Creado</h1>
    </header>

    <div class="container">
        <p><strong>Código del Cliente:</strong> 123456</p> <!-- generarCodigoen el futuro -->
        <p><strong>Nombre Completo:</strong> <%= request.getParameter("nombre") %></p>
        <p><strong>Número de Teléfono:</strong> <%= request.getParameter("telefono") %></p>
        <p><strong>Dirección de Correo Electrónico:</strong> <%= request.getParameter("email") %></p>
        <p><strong>Identificación:</strong> <%= request.getParameter("identificacion") %></p>
        <p><strong>Cantidad Máxima de Cuentas:</strong> <%= request.getParameter("maxCuentas") %></p>
        <p><strong>Fecha de Nacimiento:</strong> <%= request.getParameter("fechaNacimiento") %></p>
        <a href="index.jsp" class="account-option">Volver al Inicio</a>

    </div>
</body>
</html>
