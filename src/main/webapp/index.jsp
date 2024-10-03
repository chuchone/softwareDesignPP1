<%-- 
    Document   : index
    Created on : 23 sep. 2024, 01:12:05
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fffbe6;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #ffcc00;
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
            flex-wrap: wrap;
            justify-content: center;
            margin: 40px auto;
            max-width: 1200px;
        }
        .menu-item {
            background-color: #ffeb99;
            color: #4d4d4d;
            text-align: center;
            padding: 20px;
            margin: 10px;
            border-radius: 10px;
            flex: 1 0 30%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-decoration: none;
            font-size: 1.2em;
        }
        .menu-item:hover {
            transform: translateY(-10px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            background-color: #ffe066;
        }
        .menu-item:nth-child(odd) {
            background-color: #ffdb4d;
        }
    </style>
</head>
<body>
    <header>
        <h1>Sistema Bancario</h1>
        <p>Seleccione una opción para continuar</p>
    </header>

    <div class="container">
        <a href="gestionarClientes.jsp" class="menu-item">Gestión de Clientes</a>
        <a href="gestionarCuentas.jsp" class="menu-item">Gestión de Cuentas</a>
        <a href="operacionesBancarias.jsp" class="menu-item">Operaciones Bancarias</a>
        <a href="consultas.jsp" class="menu-item">Consultas</a>
        <a href="salir.jsp" class="menu-item">Informacion del desarrollo</a>
    </div>
</body>
</html>

