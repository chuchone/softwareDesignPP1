<%-- 
    Document   : confirmacionCambiarPin
    Created on : 24 sep. 2024, 23:37:32
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmación - PIN Cambiado</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #ff9966;
            color: white;
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
        <h1>PIN Cambiado</h1>
    </header>

    <div class="container">
        <p>Estimado cliente, le informamos que se ha cambiado satisfactoriamente el PIN de su cuenta.</p>
        <a href="index.jsp" class="account-option">Volver al Inicio</a>
    </div>
</body>
</html>
