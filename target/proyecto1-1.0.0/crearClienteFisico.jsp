<%-- 
    Document   : crearClienteFisico
    Created on : 24 sep. 2024, 23:18:34
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Cliente Físico - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #ffcc80;
            color: #333;
            padding: 20px;
            text-align: center;
        }
        h1 {
            margin: 0;
            font-size: 2.5em;
        }
        form {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #ff9966;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 1.2em;
        }
        input[type="submit"]:hover {
            background-color: #ff704d;
        }
        .error {
            color: red;
            font-size: 0.9em;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Crear Cliente Físico</h1>
    </header>

    <form action="CrearClienteFisicoServlet" method="post">
        <label for="nombre">Nombre Completo:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="categoria">Categoría del Cliente:</label>
        <select id="categoria" name="categoria" required>
            <option value="">Opciones</option>
            <option value="Fisico">Fisico</option>
        </select>

        <label for="telefono">Número de Teléfono:</label>
        <input type="text" id="telefono" name="telefono" required maxlength="8">

        <label for="email">Dirección de Correo Electrónico:</label>
        <input type="email" id="email" name="email" required>

        <label for="identificacion">Identificación del Cliente:</label>
        <input type="text" id="identificacion" name="identificacion" required>

        <label for="maxCuentas">Cantidad Máxima de Cuentas:</label>
        <input type="text" id="maxCuentas" name="maxCuentas" required>

        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>

        <input type="submit" value="Crear Cliente">
    </form>
</body>
</html>
