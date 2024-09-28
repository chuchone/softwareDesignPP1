<%-- 
    Document   : confirmarCuenta
    Created on : 24 sep. 2024, 23:33:15
    Author     : Nelson
--%>

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
    </style>
</head>
<body>
    <header>
        <h1>Cuenta Creada</h1>
    </header>

    <div class="container">
        <p><strong>Número de Cuenta:</strong> CTA12345</p> <!-- Puedes generar el número de cuenta dinámicamente -->
        <p><strong>Estatus de la Cuenta:</strong> Activa</p>
        <p><strong>Saldo Actual:</strong> <%= request.getParameter("montoInicial") %>.00</p>
        <p><strong>Tipo de Cliente:</strong> Jurídico/Físico (Este dato se puede recuperar dinámicamente)</p>
        <p><strong>Nombre del Dueño o Apoderado Generalísimo:</strong> Juan Pérez</p> <!-- Este dato también puede venir de la lógica backend -->
    </div>
</body>
</html>
