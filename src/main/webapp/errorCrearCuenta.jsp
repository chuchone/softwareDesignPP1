<%-- 
    Document   : errorCrearCuenta
    Created on : 30 sep. 2024, 21:44:38
    Author     : Nelson
--%>


<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error - Sistema Bancario</title>
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
        .error-message {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            color: red;
            font-size: 1.2em;
        }
        .button {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 10px;
            background-color: #ff9966;
            color: white;
            border: none;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }
        .button:hover {
            background-color: #ff704d;
        }
    </style>
</head>
<body>
    <header>
        <h1>Error</h1>
    </header>
    <div class="error-message">
        <p><%= request.getAttribute("mensajeDeError") %></p>
    </div>
    <a href="crearCuenta.jsp" class="button">Volver al formulario</a>
    <a href="index.jsp" class="consult-option">Volver al Inicio</a>
</body>
</html>
