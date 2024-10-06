<%-- 
    Document   : cambiarEmailCliente
    Created on : 5 oct. 2024, 18:47:02
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cambiar Correo Electrónico</title>
    <style>
        body {
            background-color: #d9d9d9;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; 
        }
        .container {
            width: 400px; 
            padding: 30px; 
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2); 
            background-color: #6f4c3e; 
            color: white;
            text-align: center;
        }
        h1 {
            margin-bottom: 20px; 
            font-size: 24px; 
        }
        label {
            margin-bottom: 10px;
            display: block;
            font-size: 16px;
        }
        input[type="text"], input[type="email"], input[type="submit"], button {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 4px;
            font-size: 16px;
        }
        input[type="text"], input[type="email"] {
            background-color: #ffffff;
            color: #333;
        }
        input[type="submit"] {
            background-color: #4CAF50; 
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        button {
            background-color: #333333;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #555555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Cambiar Correo Electrónico</h1>
        <form action="CambiarCorreoCliente" method="post">
            <label for="identidad">Identidad del Cliente:</label>
            <input type="text" id="identidad" name="identidad" required>

            <label for="nuevoCorreo">Nuevo Correo Electrónico:</label>
            <input type="email" id="nuevoCorreo" name="nuevoCorreo" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Debe ser un correo electrónico válido.">
            <input type="submit" value="Actualizar Correo">
        </form>

        <!-- Botón para volver al inicio -->
        <form action="index.jsp" method="get">
            <button type="submit">Volver al Inicio</button>
        </form>
    </div>
</body>
</html>
