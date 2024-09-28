<%-- 
    Document   : gestionarClientes
    Created on : 24 sep. 2024, 07:45:16
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Clientes - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffe6f0;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #ff99cc;
            color: #4d4d4d;
            padding: 20px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin: 0;
            font-size: 2.5em;
        }
        p {
            margin: 10px 0 0;
            font-size: 1.2em;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 40px auto;
            max-width: 800px;
        }
        .client-option {
            background-color: #ffccdf;
            color: #4d4d4d;
            text-align: center;
            padding: 20px;
            margin: 15px 0;
            border-radius: 10px;
            width: 100%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-decoration: none;
            font-size: 1.2em;
        }
        .client-option:hover {
            transform: translateY(-10px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            background-color: #ff99cc;
        }
    </style>
</head>
<body>
    <header>
        <h1>Gestión de Clientes</h1>
        <p>Seleccione una opción para gestionar los clientes</p>
    </header>

    <div class="container">
        <a href="crearClienteFisico.jsp" class="client-option">Crear Cliente Físico</a>
        <a href="crearClienteJuridico.jsp" class="client-option">Crear Cliente Jurídico</a>
        <a href="cambiarTelefonoCliente.jsp" class="client-option">Cambiar Teléfono del Cliente</a>
        <a href="cambiarEmailCliente.jsp" class="client-option">Cambiar Correo Electrónico del Cliente</a>
        <a href="index.jsp" class="client-option">Volver al Inicio</a>
    </div>
</body>
</html>

