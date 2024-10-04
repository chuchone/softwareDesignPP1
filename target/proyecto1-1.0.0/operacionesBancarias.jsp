<%-- 
    Document   : operacionesBancarias
    Created on : 24 sep. 2024, 08:16:05
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Operaciones Bancarias - Sistema Bancario</title>
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
        .banking-option {
            background-color: #cce6ff;
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
        .banking-option:hover {
            transform: translateY(-10px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            background-color: #66b3ff;
        }
    </style>
</head>
<body>
    <header>
        <h1>Operaciones Bancarias</h1>
        <p>Seleccione una opción para realizar operaciones bancarias</p>
    </header>

    <div class="container">
        <a href="depositoColones.jsp" class="banking-option">Depósito en Colones</a>
        <a href="depositoCambioMoneda.jsp" class="banking-option">Depósito con Cambio de Moneda</a>
        <a href="retiroColones.jsp" class="banking-option">Retiro en Colones</a>
        <a href="retiroCompraMoneda.jsp" class="banking-option">Retiro con Compra de Moneda</a>
        <a href="TransferenciaLocalUnSoloDuenio.jsp" class="banking-option">Transferir a un mismo dueño</a>
        <a href="index.jsp" class="banking-option">Volver al Inicio</a>
    </div>
</body>
</html>
