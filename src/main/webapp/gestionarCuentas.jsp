<%-- 
    Document   : gestionarCuentas
    Created on : 24 sep. 2024, 08:15:29
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Cuentas - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e6ffe6;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #66cc66;
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
        .account-option {
            background-color: #b3ffb3;
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
        .account-option:hover {
            transform: translateY(-10px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            background-color: #66cc66;
        }
    </style>
</head>
<body>
    <header>
        <h1>Gestión de Cuentas</h1>
        <p>Seleccione una opción para gestionar las cuentas</p>
    </header>

    <div class="container">
        <a href="crearCuenta.jsp" class="account-option">Crear Cuenta</a>
        <a href="cambiarPin.jsp" class="account-option">Cambiar PIN</a>
        <a href="eliminarCuenta.jsp" class="account-option">Eliminar Cuenta</a>
        <a href="index.jsp" class="account-option">Volver al Inicio</a>
    </div>
</body>
</html>
