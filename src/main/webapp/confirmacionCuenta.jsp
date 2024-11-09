<%-- 
    Document   : confirmarCuenta
    Created on : 24 sep. 2024, 23:33:15
    Author     : Nelson
--%>

<%@page import="disenioProyecto1.modelo.gestorBanco.CuentaBancaria"%>
<%@page import="disenioProyecto1.modelo.gestorBanco.CuentaBancaria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmación - Cuenta Creada</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #3399ff;
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
        .client-option {
            display: inline-block;
            background-color: #66b3ff;
            color: white;
            padding: 15px 30px;
            margin-top: 20px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 1.2em;
            transition: background-color 0.3s ease;
        }
        .client-option:hover {
            background-color: #0066cc;
        }
    </style>
</head>
<body>
    <header>
        <h1>Cuenta Creada</h1>
    </header>

    <div class="container">
        <%
            CuentaBancaria cuenta = (CuentaBancaria) request.getAttribute("cuentaBancaria");
            if (cuenta != null) {
        %>
            <p><strong>Número de Cuenta:</strong> <%= cuenta.numeroCuenta %></p>
            <p><strong>Estatus de la Cuenta:</strong> <%= cuenta.statusCuentaActiva ? "Activa" : "Inactiva" %></p>
            <p><strong>Saldo Actual:</strong> <%= cuenta.dineroEnLaCuenta %>.00</p>
            <p><strong>Fecha de creación:</strong> <%= cuenta.fechaCreacion %></p>
            <p><strong>Nombre del Dueño o Apoderado Generalísimo:</strong> <%= cuenta.nombreDuenio %></p>
            <a href="index.jsp" class="client-option">Volver al Inicio</a>
        <%
            } else {
        %>
            <p>No se encontró información de la cuenta.</p>
        <%
            }
        %>
    </div>
</body>
</html>
