<%-- 
    Document   : resultadoCambioCorreo
    Created on : 6 oct. 2024, 02:31:01
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado del Cambio de Correo</title>
    <style>
        body {
            background-color: #f4f4f9;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
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
            margin-bottom: 30px;
        }
        .info {
            font-weight: bold;
            color: #333;
        }
        a {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Cambio de Correo Exitoso</h1>
        <p>Estimado cliente: <span class="info">${nombre}</span>,</p>
        <p>Usted ha cambiado la dirección de correo <span class="info">${correoAnterior}</span> por <span class="info">${correo}</span>.</p>
        <a href="index.jsp">Volver al Inicio</a>
    </div>
</body>
</html>
