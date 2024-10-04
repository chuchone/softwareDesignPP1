<%-- 
    Document   : confirmacionDepositoColones
    Created on : 24 sep. 2024, 23:42:20
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmación de Depósito de dolares</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
        }
        p {
            font-size: 1.1em;
            margin: 10px 0;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Confirmación de Depósito</h1>
        <p>El monto real depositado a su cuenta aplicando el cambio de divisa a colones es: <strong><%= request.getAttribute("numeroCuenta") %></strong> es de <strong><%= String.format("%.2f", request.getAttribute("montoDepositado")) %></strong> colones.</p>
        <p>Las comisiones suman un monto total de: <strong><%= String.format("%.2f", request.getAttribute("montoComision")) %></strong> colones, que se han ido rebajado de su sueldo actual.</p>
        <a href="index.jsp">Volver a la página principal</a>
    </div>
</body>
</html>
