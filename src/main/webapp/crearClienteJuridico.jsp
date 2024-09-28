<%-- 
    Document   : crearClienteJuridico
    Created on : 24 sep. 2024, 23:25:56
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Cliente Jurídico - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #cc99ff;
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
            background-color: #9933ff;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 1.2em;
        }
        input[type="submit"]:hover {
            background-color: #7a29cc;
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
        <h1>Crear Cliente Jurídico</h1>
    </header>

    <form action="CrearClienteJuridicoServlet" method="post">
        <label for="nombre">Nombre Completo del Apoderado Generalísimo:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="categoria">Categoría del Cliente:</label>
        <select id="categoria" name="categoria" required>
            <option value="">Seleccione una opción</option>
            <option value="Juridico">Juridico</option>
        </select>

        <label for="telefono">Número de Teléfono:</label>
        <input type="text" id="telefono" name="telefono" required maxlength="8">

        <label for="email">Dirección de Correo Electrónico:</label>
        <input type="email" id="email" name="email" required>

        <label for="tipoNegocio">Tipo de Negocio:</label>
        <input type="text" id="tipoNegocio" name="tipoNegocio" required>

        <label for="cedulaJuridica">Cédula Jurídica:</label>
        <input type="text" id="cedulaJuridica" name="cedulaJuridica" required maxlength="10">

        <label for="razonSocial">Razón Social:</label>
        <input type="text" id="razonSocial" name="razonSocial" required>

        <input type="submit" value="Crear Cliente">
    </form>
</body>
</html>
