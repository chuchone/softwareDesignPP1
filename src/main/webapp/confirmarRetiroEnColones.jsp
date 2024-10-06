<%-- 
    Document   : confirmarRetiroEnColones
    Created on : 1 oct. 2024, 11:19:13
    Author     : Nelson
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmación de Retiro - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e6f7ff;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #66b3ff;
            color: #4d4d4d;
            padding: 20px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin: 0;
            font-size: 2.5em;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 40px auto;
            max-width: 600px;
        }
        .confirmation-box {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            text-align: center;
        }
        .confirmation-box p {
            font-size: 1.2em;
            margin: 20px 0;
        }
        .back-button {
            background-color: #cce6ff;
            color: #4d4d4d;
            text-align: center;
            padding: 15px 30px;
            margin-top: 20px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 1.2em;
            transition: background-color 0.3s ease;
        }
        .back-button:hover {
            background-color: #66b3ff;
        }
    </style>
</head>
<body>
    <header>
        <h1>Confirmación de Retiro</h1>
    </header>
    <div class="container">
        <div class="confirmation-box">
            <p>El retiro de <strong>₡<%= request.getAttribute("montoRetirado") != null ? String.format("%,.2f", (Double) request.getAttribute("montoRetirado")) : "0.00" %></strong> se hizo correctamente</p>
            <p>Suma de comisiones: <strong>₡<%= request.getAttribute("montoComision") != null ? String.format("%,.2f", (Double) request.getAttribute("montoComision")) : "0.00" %></strong></p>
            <p>Número de Cuenta: <strong><%= request.getAttribute("numeroCuenta") != null ? request.getAttribute("numeroCuenta") : "No disponible" %></strong></p>
            <a href="index.jsp" class="back-button">Ir al menú de inicio</a>
        </div>
    </div>
</body>
</html>
