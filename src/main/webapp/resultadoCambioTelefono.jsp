<%-- 
    Document   : resultadoCambioTelefono
    Created on : 5 oct. 2024, 20:44:40
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cambio de Teléfono Exitoso</title>
    <style>
        body {
            background-color: #eef2f7;
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            text-align: center;
        }
        h1 {
            font-size: 28px;
            color: #333;
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
            color: #555;
            margin-bottom: 20px;
        }
        .info {
            font-weight: bold;
            color: #333;
        }
        a {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            font-size: 16px;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Cambio de Teléfono Exitoso</h1>
        <p>Estimado cliente: <span class="info">${nombre}</span>,</p>
        <p>Usted ha cambiado el número de teléfono <span class="info">${telefonoAnterior}</span> por <span class="info">${telefono}</span>.</p>
        <a href="index.jsp">Volver al Inicio</a>
    </div>
</body>
</html>
