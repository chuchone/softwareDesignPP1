<%-- 
    Document   : confirmacionClienteJuridico
    Created on : 24 sep. 2024, 23:28:29
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmación - Cliente Jurídico Creado</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #cc99ff;
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
    </style>
</head>
<body>
    <header>
        <h1>Cliente Jurídico Creado</h1>
    </header>

    <div class="container">
        <p><strong>Código del Cliente:</strong> 654321</p> <!-- generarCodigoen el futuro -->
        <p><strong>Nombre Completo del Apoderado Generalísimo:</strong> <%= request.getParameter("nombre") %></p>
        <p><strong>Número de Teléfono:</strong> <%= request.getParameter("telefono") %></p>
        <p><strong>Dirección de Correo Electrónico:</strong> <%= request.getParameter("email") %></p>
        <p><strong>Tipo de Negocio:</strong> <%= request.getParameter("tipoNegocio") %></p>
        <p><strong>Cédula Jurídica:</strong> <%= request.getParameter("cedulaJuridica") %></p>
        <p><strong>Razón Social:</strong> <%= request.getParameter("razonSocial") %></p>
        <a href="index.jsp" class="account-option">Volver al Inicio</a>

    </div>
</body>
</html>
