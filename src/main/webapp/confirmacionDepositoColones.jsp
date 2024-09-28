<%-- 
    Document   : confirmacionDepositoColones
    Created on : 24 sep. 2024, 23:42:20
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmación - Depósito Realizado</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #4caf50;
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
        <h1>Depósito Realizado</h1>
    </header>

    <div class="container">
        <p>Estimado cliente: <strong>Juan Pérez</strong>, se han depositado correctamente <strong>50,000.00 colones</strong>.</p>
        <p>El monto real depositado a su cuenta <strong>CTA12345</strong> es de <strong>50,000.00 colones</strong>.</p>
        <p>El monto cobrado por concepto de comisión fue de <strong>500.00 colones</strong>, que fueron rebajados automáticamente de su saldo actual.</p>
    </div>
</body>
</html>
